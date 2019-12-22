package org.hydosky.send.data;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hydosky.send.vo.CategoryVO;
import org.hydosky.send.entity.*;
import org.hydosky.send.service.*;
import org.hydosky.send.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 *
 * <h3>hydosky-send</h3>
 * <p>
 *     生成订单及统计数据
 * </p>
 * Created by yang on 19-11-28 下午12:48
 * updated by yang on 19-11-28 下午12:48
 */
@Slf4j
@Component
@Async
public class DataProcessRunner implements CommandLineRunner {

    private static final Random random = new Random();

    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IDateHotTypeService dateHotTypeService;
    @Autowired
    private IDateStatisticsService dateStatisticsService;
    @Autowired
    private IHistoryStatisticsService historyStatisticsService;
    @Autowired
    private ICityPriceGoodsCountService cityPriceGoodsCountService;
    @Autowired
    private IWeekPriceTrendService weekPriceTrendService;
    @Autowired
    private IHotCategoryService hotCategoryService;
    @Autowired
    private IBaseDataService baseDataService;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            long sleepTime = 0L;
            BaseData baseData = baseDataService.getOne(Wrappers.<BaseData>lambdaQuery().last("limit 1"));
            // 开始营业时间，例如9:00
            String startTime = baseData.getStartTime();
            // 结束营业时间，例如18:00
            String endTime = baseData.getEndTime();
            // 判断当前时间是否是营业时间段
            if (DateUtils.isHourBetween(startTime, endTime)) {
                // 当前日期和时间
                LocalDateTime localDateTime = LocalDateTime.now();

                // 查询城市列表
                List<City> cityList = cityService.list();

                // 获取所有城市的系数之和
                double weights = cityList.stream()
                        .mapToDouble(city -> city.getWeight().doubleValue())
                        .sum();

                // 获取程序睡眠时间
                sleepTime = getSleepTime(weights);
                // 根据 城市系数 非均匀随机 选择城市
                String cityName = getCityName(cityList, weights);

                if (!StringUtils.isBlank(cityName)) {
                    Order order = generateOrder(localDateTime, cityName,
                            baseData.getGoodsCountScope(), baseData.getPriceScope());
                    // 将订单插入数据库
                    orderService.save(order);
                    log.info("【生成订单】 order: {}", order.toString());
                    // 统计数据并保存
                    statistics(localDateTime, order);
                }

            }
            // 时间随机区间
            log.info("【休眠时间】 {}ms", sleepTime);
            Thread.sleep(600000L);
        }
    }

    /**
     * 根据 城市系数 非均匀随机 选择城市
     *
     * 城市系数大的属于热门城市，
     * 当天生成的订单数量多于城市系数小的
     * @param cityList 城市列表
     * @param weights 城市系数之和
     * @return cityName 城市名称
     */
    private String getCityName(List<City> cityList, double weights) {
        String weightsStr = String.valueOf(weights);
        String weightsIntStr = weightsStr.substring(0, weightsStr.indexOf("."))
                + weightsStr.substring(weightsStr.indexOf(".") + 1, weightsStr.indexOf(".") + 4);
        int weightsInt = Integer.parseInt(weightsIntStr);
        int randomInt = random.nextInt(weightsInt);//randomInt in [0, weightsInt]
        int m = 0;
        String cityName = null;
        // 比较randomInt和城市系数，定位城市
        for (City city : cityList) {
            String weightStr = city.getWeight().toString();
            String weightIntStr = weightStr.substring(0, weightStr.indexOf("."))
                    + weightStr.substring(weightStr.indexOf(".") + 1 , weightStr.indexOf(".") + 4);
            int weightInt = Integer.parseInt(weightIntStr);
            if (m <= randomInt && randomInt < m + weightInt) {
                cityName = city.getName();
                break;
            }
            m += weightInt;
        }
        return cityName;
    }

    /**
     * 根据赣州的日订单量、城市系数，以及所有城市系数之和 得到当日应当生成的订单数量，
     * 然后使用 日营业时间总毫秒数 除以 订单数量 ，得到订单生成程序睡眠时间。
     *
     * 当日应当生成的订单数量 = (赣州的日订单量 / 赣州的城市系数) * 城市系数之和
     * 睡眠时间 = 当日营业时间总毫秒数 / 当日应当生成的订单数量
     *
     * @param weights 所有城市系数之和
     * @return 程序睡眠时间
     */
    private long getSleepTime(double weights) {
        long sleepTime;
        // 查询赣州基准数据
        BaseData baseData = baseDataService.getOne(Wrappers.<BaseData>lambdaQuery().last("limit 1"));
        // 获取赣州城市数据
        City cityBase = cityService.getOne(Wrappers.lambdaQuery(City.of(baseData.getCityName())));
        Double cityBaseWeight = cityBase.getWeight().doubleValue();
        // 使用赣州日订单量除以赣州的城市系数，得到一个正整数
        int intBase = (int) (baseData.getDayOrderCount() / cityBaseWeight);

        // 得到当日应当生成的总订单数量
        int dayOrderCount = (int) (intBase * weights);
        // 得到营业时间总毫秒数
        long mills = DateUtils.millsBetween(baseData.getStartTime(), baseData.getEndTime());
        //  当日营业总毫秒数 除以 当日总订单量 得到生成订单间隔时间
        sleepTime = mills / dayOrderCount ;
        return sleepTime;
    }

    /**
     * 生成订单
     */
    private Order generateOrder(LocalDateTime localDateTime, String cityName, String goodsCountScope, String priceScope) {
        // 查找到货物分类
        List<CategoryVO> categoryVOList = categoryService.treeList();
        int categorySize = categoryVOList.size();
        CategoryVO categoryVO = categoryVOList.get(random.nextInt(categorySize));
        // 随机得到订单一级分类
        String categoryName = categoryVO.getName();

        List<Category> typeList = categoryVO.getChildren();
        int typeSize = typeList.size();
        Category type = typeList.get(random.nextInt(typeSize));
        // 随机得到订单二级分类
        String typeName = type.getName();

        // 随机得到货物数量 2:4
        String[] goodsCountScopeArr = goodsCountScope.split(":");
        int minGoodsCount = Integer.parseInt(goodsCountScopeArr[0]);
        int maxGoodsCount = Integer.parseInt(goodsCountScopeArr[1]);
        Integer number = random.nextInt(maxGoodsCount - minGoodsCount) + minGoodsCount;
        // 随机得到订单运费 18:22
        String[] priceScopeArr = priceScope.split(":");
        int minPrice = Integer.parseInt(priceScopeArr[0]);
        int maxPrice = Integer.parseInt(priceScopeArr[1]);
        Integer price = random.nextInt(maxPrice - minPrice) + minPrice;

        Order order = Order.of()
                .setCategory(categoryName)
                .setType(typeName)
                .setCity(cityName)
                .setNumber(number)
                .setPrice(price)
                .setCreateTime(localDateTime);
        return order;
    }

    /**
     * 统计数据并保存
     */
    private void statistics(LocalDateTime localDateTime, Order order) {
        // 当前日期
        LocalDate date = localDateTime.toLocalDate();
        // 获取这个日期所在周的星期一.plusDays(1L)
        LocalDate monDate = date.with(DayOfWeek.MONDAY);
        // 当前年份
        int year = date.getYear();

        // 1、当日实时热门货物品类 date_hot_type
        dateHotTypeService.insertOrUpdate(
                DateHotType.of()
                        .setDate(date)
                        .setType(order.getType())
                        .setOrderCount(1)
        );

        // 2、实时热门货运城市
        cityService.insertOrUpdate(
                City.of(order.getCity())
                        .setOrderCount(1L)
        );

        // 3、今日订单总量 4、今日订单总额 5、今日货物总量
        dateStatisticsService.insertOrUpdate(
                DateStatistics.of()
                        .setDate(date)
                        .setOrderCount(1)
                        .setOrderPrice(order.getPrice().longValue())
                        .setGoodsCount(order.getNumber().longValue())
        );

        // 6、历史订单总量 7、历史订单总额 8、历史货物总量
        historyStatisticsService.insertOrUpdate(
                HistoryStatistics.of()
                        .setId("history")
                        .setOrderPrice(order.getPrice().longValue())
                        .setOrderCount(1L)
                        .setGoodsCount(order.getNumber().longValue())
        );

        // 9、近期订单金额统计(按周统计，记录每周一的日期，这一周的订单金额都累计到周一日期的记录里)
        weekPriceTrendService.insertOrUpdate(
                WeekPriceTrend.of()
                        .setMonDate(monDate)
                        .setOrderPrice(order.getPrice().longValue())
        );


        //10、城市货运金额统计 11、城市货物数量统计
        cityPriceGoodsCountService.insertOrUpdate(
                CityPriceGoodsCount.of()
                        .setYear(Integer.toString(year))
                        .setCity(order.getCity())
                        .setOrderPrice(order.getPrice().longValue())
                        .setGoodsCount(order.getNumber().longValue())
        );

        // 12、全网货运品类排行
        hotCategoryService.insertOrUpdate(
                HotCategory.of()
                        .setCategory(order.getCategory())
                        .setOrderCount(1L)
        );
    }
}

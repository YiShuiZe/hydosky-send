package org.hydosky.send.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.hydosky.send.vo.CityVO;
import org.hydosky.send.vo.CommonResponse;
import org.hydosky.send.entity.*;
import org.hydosky.send.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     统计数据接口
 * </p>
 * Created by yang on 19-12-1 下午5:37
 * updated by yang on 19-12-1 下午5:37
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IDateHotTypeService dateHotTypeService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IDateStatisticsService dateStatisticsService;
    @Autowired
    private IHistoryStatisticsService historyStatisticsService;
    @Autowired
    private ICityPriceGoodsCountService cityPriceGoodsCountService;
    @Autowired
    private IHotCategoryService hotCategoryService;
    @Autowired
    private IWeekPriceTrendService weekPriceTrendService;

    /**
     * 当日实时热门货物品类
     */
    @GetMapping("/date-hot-type")
    public List<DateHotType> dateHotType() {
        List<DateHotType> dateHotTypeList = dateHotTypeService.list(
                Wrappers.lambdaQuery(
                    DateHotType.of()
                        .setDate(LocalDate.now())
                ).orderByDesc(DateHotType::getOrderCount)
                .last("LIMIT 10")
        );
        return dateHotTypeList;
    }

    /**
     * 实时热门货运城市
     * zoom lng lat
     */
    @GetMapping("/hot-city")
    public List<CityVO> hotCity() {
        List<City> hotCityList = cityService.list();
        // 获取历史总订单数量
        HistoryStatistics historyStatistics = historyStatisticsService.getOne(
                Wrappers.lambdaQuery(
                        HistoryStatistics.of()
                                .setId("history")));
        List<CityVO> hotCityVOList = new ArrayList();
        hotCityList.forEach(city -> {
            CityVO cityVO = CityVO.of();
            if (city.getOrderCount() == 0L || historyStatistics == null || historyStatistics.getOrderCount() == 0L) {
                cityVO.setZoom(0.0D);
            } else {
                Long orderCount = historyStatistics.getOrderCount();
                // 使用该城市的订单数量除以历史总订单数量
                Double zoom = city.getOrderCount() * 1.00 / orderCount * 10;
                String zoomStr = String.valueOf(zoom);
                String zoomNewStr = zoomStr.substring(0, zoomStr.indexOf(".") + 4);
                cityVO.setZoom(Double.valueOf(zoomNewStr));
            }
            cityVO.setName(city.getName())
                    .setLat(city.getLat())
                    .setLng(city.getLng())
                    .setOrderCount(city.getOrderCount());
            hotCityVOList.add(cityVO);
        });
        return hotCityVOList;
    }

    /**
     * 今日 订单总量、订单总额、货物总量
     */
    @GetMapping("/date-statistics")
    public List<Map<String, String>> dateStatistics() {
        DateStatistics dateStatistics = dateStatisticsService.getOne(
                Wrappers.lambdaQuery(
                        DateStatistics.of()
                                .setDate(LocalDate.now())));
        List<Map<String, String>> hashMaps = new ArrayList<>();
        HashMap<String, String> map = new LinkedHashMap<>();
        if (dateStatistics == null) {
            map.put("今日订单总量", 0 + "笔");
            map.put("今日订单总额", 0 + "元");
            map.put("今日货物总量", 0 + "件");
        } else {
            map.put("今日订单总量", dateStatistics.getOrderCount() + "笔");
            map.put("今日订单总额", dateStatistics.getOrderPrice() + "元");
            map.put("今日货物总量", dateStatistics.getGoodsCount() + "件");
        }
        hashMaps.add(map);
        return hashMaps;
    }

    /**
     * 历史 订单总量、订单总额、货物总量
     */
    @GetMapping("/history-statistics")
    public List<Map<String, String>> historyStatistics() {
        HistoryStatistics historyStatistics = historyStatisticsService.getOne(
                Wrappers.lambdaQuery(
                        HistoryStatistics.of()
                                .setId("history")));
        List<Map<String, String>> hashMaps = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();
        if (historyStatistics == null) {
            map.put("历史订单总量", 0 + "笔");
            map.put("历史订单总额", 0 + "元");
            map.put("历史货物总量", 0 + "件");
        } else {
            map.put("历史订单总量", historyStatistics.getOrderCount() + "笔");
            map.put("历史订单总额", historyStatistics.getOrderPrice() + "元");
            map.put("历史货物总量", historyStatistics.getGoodsCount() + "件");
        }
        hashMaps.add(map);
        return hashMaps;
    }

    /**
     * 城市货运金额统计
     */
    @GetMapping("/city-price")
    public List<CommonResponse> cityPrice() {
        List<CityPriceGoodsCount> cityPriceGoodsCountList = cityPriceGoodsCountService.list(
                Wrappers.lambdaQuery(
                        CityPriceGoodsCount.of()
                                .setYear(Integer.toString(LocalDate.now().getYear())))
                        .orderByDesc(CityPriceGoodsCount::getOrderPrice));
        List<CommonResponse> responseList = new ArrayList<>();
        cityPriceGoodsCountList.forEach(cityPriceGoodsCount ->
                responseList.add(
                        CommonResponse.of(
                                cityPriceGoodsCount.getCity(),
                                cityPriceGoodsCount.getOrderPrice())));
        return responseList;
    }

    /**
     * 城市货物数量统计
     */
    @GetMapping("/city-goods-count")
    public List<CommonResponse> cityGoodsCount() {
        List<CityPriceGoodsCount> cityPriceGoodsCountList = cityPriceGoodsCountService.list(
                Wrappers.lambdaQuery(
                        CityPriceGoodsCount.of()
                                .setYear(Integer.toString(LocalDate.now().getYear())))
                        .orderByDesc(CityPriceGoodsCount::getGoodsCount));
        List<CommonResponse> responseList = new ArrayList<>();
        cityPriceGoodsCountList.forEach(cityPriceGoodsCount ->
                responseList.add(
                        CommonResponse.of(
                                cityPriceGoodsCount.getCity(),
                                cityPriceGoodsCount.getGoodsCount())));
        return responseList;
    }


    /**
     * 全网货运品类排行
     */
    @GetMapping("/hot-category")
    public List<CommonResponse> hotCategory() {
        List<HotCategory> hotCategoryList = hotCategoryService.list(
                Wrappers.<HotCategory>lambdaQuery()
                        .orderByDesc(HotCategory::getOrderCount));
        List<CommonResponse> responseList = new ArrayList<>();
        hotCategoryList.forEach(hotCategory ->responseList.add(
                CommonResponse.of(
                        hotCategory.getCategory(),
                        hotCategory.getOrderCount())));
        return responseList;
    }

    /**
     * 近期订单金额统计
     */
    @GetMapping("/week-price-trend")
    public List<CommonResponse> weekPriceTrend() {
        // 获取小于等于当前日期的数据，limit 8
        List<WeekPriceTrend> weekPriceTrendList = weekPriceTrendService.list(
                Wrappers.<WeekPriceTrend>lambdaQuery()
                        .le(WeekPriceTrend::getMonDate, LocalDate.now())
                        .orderByDesc(WeekPriceTrend::getMonDate)
                        .last("LIMIT 8"));
        // 自定义日期格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd");
        List<CommonResponse> commonResponseList = new ArrayList<>();
        weekPriceTrendList.forEach(weekPriceTrend ->
                commonResponseList.add(
                        CommonResponse.of(
                                weekPriceTrend.getMonDate().format(dtf),
                                weekPriceTrend.getOrderPrice())
                                .setS(1)));
        return commonResponseList;
    }

}

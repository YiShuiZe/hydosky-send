package org.hydosky.send.schedule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.hydosky.send.entity.BaseData;
import org.hydosky.send.entity.DateHotType;
import org.hydosky.send.entity.DateStatistics;
import org.hydosky.send.entity.Order;
import org.hydosky.send.service.IBaseDataService;
import org.hydosky.send.service.IDateHotTypeService;
import org.hydosky.send.service.IDateStatisticsService;
import org.hydosky.send.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     删除指定日期前的数据表记录，
 *     所删除的表记录，都是按天增长的，
 *     定期删除，使得数据库不用人为去维护其磁盘空间。
 * </p>
 * Created by yang on 19-12-15 下午8:28
 * updated by yang on 19-12-15 下午8:28
 */
@Component
public class DelDataSche {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IDateHotTypeService dateHotTypeService;
    @Autowired
    private IDateStatisticsService dateStatisticsService;
    @Autowired
    private IBaseDataService baseDataService;

    @Async
    @Scheduled(cron = "0 0 02 * * ?") // 每天凌晨两点定时执行
    public void delDataTasks() {
        // 从数据库获取 指定的天数（删除几天前的数据）
        BaseData baseData = baseDataService.getOne(Wrappers.<BaseData>lambdaQuery().last("limit 1"));
        int days = baseData.getDays();
        // 获取 指定天数前 的时间
        LocalDateTime minusDateTime = LocalDateTime.now().minusDays(days);
        // 得到日期
        LocalDate date = minusDateTime.toLocalDate();

        // 删除订单表指定时间前的数据
        orderService.remove(Wrappers.<Order>lambdaQuery()
                .lt(Order::getCreateTime, minusDateTime));

        // 删除当日实时热门货物品类表指定日期前的数据
        dateHotTypeService.remove(Wrappers.<DateHotType>lambdaQuery()
                .lt(DateHotType::getDate, date));

        // 删除当日订单总量-订单总额-货物总量表指定日期前的数据
        dateStatisticsService.remove(Wrappers.<DateStatistics>lambdaQuery()
                .lt(DateStatistics::getDate, date));

    }
}

package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.WeekPriceTrend;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:30
 * updated by yang on 19-12-1 下午5:30
 */
public interface IWeekPriceTrendService extends IService<WeekPriceTrend> {
    int insertOrUpdate(WeekPriceTrend weekPriceTrend);
}

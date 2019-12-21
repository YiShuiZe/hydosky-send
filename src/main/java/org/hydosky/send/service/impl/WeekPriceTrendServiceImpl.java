package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.WeekPriceTrend;
import org.hydosky.send.mapper.WeekPriceTrendMapper;
import org.hydosky.send.service.IWeekPriceTrendService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:31
 * updated by yang on 19-12-1 下午5:31
 */
@Service
public class WeekPriceTrendServiceImpl extends ServiceImpl<WeekPriceTrendMapper, WeekPriceTrend> implements IWeekPriceTrendService {
    @Override
    public int insertOrUpdate(WeekPriceTrend weekPriceTrend) {
        return baseMapper.insertOrUpdate(weekPriceTrend);
    }
}

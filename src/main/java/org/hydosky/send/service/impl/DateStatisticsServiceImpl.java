package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.DateStatistics;
import org.hydosky.send.mapper.DateStatisticsMapper;
import org.hydosky.send.service.IDateStatisticsService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:31
 * updated by yang on 19-12-1 下午5:31
 */
@Service
public class DateStatisticsServiceImpl extends ServiceImpl<DateStatisticsMapper, DateStatistics> implements IDateStatisticsService {
    @Override
    public int insertOrUpdate(DateStatistics dateStatistics) {
        return baseMapper.insertOrUpdate(dateStatistics);
    }
}

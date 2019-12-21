package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.HistoryStatistics;
import org.hydosky.send.mapper.HistoryStatisticsMapper;
import org.hydosky.send.service.IHistoryStatisticsService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:30
 * updated by yang on 19-12-1 下午5:30
 */
@Service
public class HistoryStatisticsServiceImpl extends ServiceImpl<HistoryStatisticsMapper, HistoryStatistics> implements IHistoryStatisticsService {
    @Override
    public int insertOrUpdate(HistoryStatistics historyStatistics) {
        return baseMapper.insertOrUpdate(historyStatistics);
    }
}

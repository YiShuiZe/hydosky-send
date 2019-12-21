package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.HistoryStatistics;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:29
 * updated by yang on 19-12-1 下午5:29
 */
public interface IHistoryStatisticsService extends IService<HistoryStatistics> {
    int insertOrUpdate(HistoryStatistics historyStatistics);
}

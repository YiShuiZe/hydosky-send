package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.DateHotType;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:34
 * updated by yang on 19-12-1 下午5:34
 */
public interface IDateHotTypeService extends IService<DateHotType> {
    int insertOrUpdate(DateHotType dateHotType);
}

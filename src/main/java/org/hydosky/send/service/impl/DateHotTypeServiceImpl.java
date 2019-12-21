package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.DateHotType;
import org.hydosky.send.mapper.DateHotTypeMapper;
import org.hydosky.send.service.IDateHotTypeService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:34
 * updated by yang on 19-12-1 下午5:34
 */
@Service
public class DateHotTypeServiceImpl extends ServiceImpl<DateHotTypeMapper, DateHotType> implements IDateHotTypeService {
    @Override
    public int insertOrUpdate(DateHotType dateHotType) {
        return baseMapper.insertOrUpdate(dateHotType);
    }
}

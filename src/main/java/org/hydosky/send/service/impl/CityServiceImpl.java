package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.City;
import org.hydosky.send.mapper.CityMapper;
import org.hydosky.send.service.ICityService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午1:14
 * updated by yang on 19-12-1 下午1:14
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {
    @Override
    public int insertOrUpdate(City city) {
        return baseMapper.insertOrUpdate(city);
    }
}

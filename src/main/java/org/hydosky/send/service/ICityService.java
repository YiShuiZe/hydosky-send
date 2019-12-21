package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.City;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午1:12
 * updated by yang on 19-12-1 下午1:12
 */
public interface ICityService extends IService<City> {
    int insertOrUpdate(City city);

}

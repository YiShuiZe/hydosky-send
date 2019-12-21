package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.CityPriceGoodsCount;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:27
 * updated by yang on 19-12-1 下午5:27
 */
public interface ICityPriceGoodsCountService extends IService<CityPriceGoodsCount> {
    int insertOrUpdate(CityPriceGoodsCount cityPriceGoodsCount);
}

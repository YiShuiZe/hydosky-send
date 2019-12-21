package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.CityPriceGoodsCount;
import org.hydosky.send.mapper.CityPriceGoodsCountMapper;
import org.hydosky.send.service.ICityPriceGoodsCountService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:28
 * updated by yang on 19-12-1 下午5:28
 */
@Service
public class CityPriceGoodsCountServiceImpl extends ServiceImpl<CityPriceGoodsCountMapper, CityPriceGoodsCount> implements ICityPriceGoodsCountService {
    @Override
    public int insertOrUpdate(CityPriceGoodsCount cityPriceGoodsCount) {
        return baseMapper.insertOrUpdate(cityPriceGoodsCount);
    }
}

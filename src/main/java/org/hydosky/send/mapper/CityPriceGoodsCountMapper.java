package org.hydosky.send.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.hydosky.send.entity.CityPriceGoodsCount;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午1:09
 * updated by yang on 19-12-1 下午1:09
 */
public interface CityPriceGoodsCountMapper extends BaseMapper<CityPriceGoodsCount> {
    int insertOrUpdate(@Param("cityPriceGoodsCount") CityPriceGoodsCount cityPriceGoodsCount);
}

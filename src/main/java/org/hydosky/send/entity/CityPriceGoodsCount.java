package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     城市货运金额统计
       城市货物数量统计
        实体类
 * </p>
 * Created by yang on 19-12-1 下午5:11
 * updated by yang on 19-12-1 下午5:11
 */
@Data
@TableName(value = "city_price_goodscount")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CityPriceGoodsCount implements Serializable {
    private static final long serialVersionUID = 1L;

    // 年份
    private String year;
    // 城市
    private String city;
    // 订单金额
    private Long orderPrice;
    // 货物数量
    private Long goodsCount;
}

package org.hydosky.send.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     城市视图对象
 * </p>
 * Created by yang on 19-12-19 上午9:20
 * updated by yang on 19-12-19 上午9:20
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 城市名称
    private String name;
    // 经度
    private BigDecimal lng;
    // 纬度
    private BigDecimal lat;
    // 订单数量
    private Long orderCount;
    // 缩放等级
    private Double zoom;
}

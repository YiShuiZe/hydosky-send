package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     历史订单总量
 *     历史订单总额
 *     历史货物总量
 *     实体类
 * </p>
 * Created by yang on 19-12-1 下午5:06
 * updated by yang on 19-12-1 下午5:06
 */
@Data
@TableName(value = "history_statistics")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class HistoryStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
    // 标识
    private String id;
    // 订单总量
    private Long orderCount;
    // 订单总额
    private Long orderPrice;
    // 历史货物总量
    private Long goodsCount;
}

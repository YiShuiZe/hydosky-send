package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     全网货运品类排行 实体类
 * </p>
 * Created by yang on 19-12-1 下午5:17
 * updated by yang on 19-12-1 下午5:17
 */
@Data
@TableName(value = "hot_category")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class HotCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    // 一级分类
    private String category;
    // 订单数量
    private Long orderCount;

}

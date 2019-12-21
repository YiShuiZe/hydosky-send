package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h3>hydosky-send</h3>
 * <p>订单实体类</p>
 * Created by yang on 19-12-1 上午11:05
 * updated by yang on 19-12-1 上午11:05
 */
@TableName(value = "`order`")
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    // 订单id
    @TableId(
            value = "id",
            type = IdType.ID_WORKER
    )
    private Long id;
    // 货物一级分类
    private String category;
    // 货物二级分类
    private String type;
    // 货物数量
    private Integer number;
    // 订单运费
    private Integer price;
    // 城市
    private String city;
    // 生成时间
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime createTime;
}

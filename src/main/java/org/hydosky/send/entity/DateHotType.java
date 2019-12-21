package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     当日实时热门货物品类 实体类
 * </p>
 * Created by yang on 19-12-1 下午4:49
 * updated by yang on 19-12-1 下午4:49
 */
@Data
@TableName(value = "date_hot_type")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class DateHotType implements Serializable {
    private static final long serialVersionUID = 1L;

    // 日期
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    private LocalDate date;
    // 二级分类名称
    private String type;
    // 订单数量
    private Integer orderCount;

}

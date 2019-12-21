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
 *     今日 订单数量、订单总额、货物总量实体类
 * </p>
 * Created by yang on 19-12-1 下午5:00
 * updated by yang on 19-12-1 下午5:00
 */
@Data
@TableName(value = "date_statistics")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class DateStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    // 日期
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    private LocalDate date;
    // 订单数量
    private Integer orderCount;
    // 订单总额
    private Long orderPrice;
    // 货物总量
    private Long goodsCount;

}

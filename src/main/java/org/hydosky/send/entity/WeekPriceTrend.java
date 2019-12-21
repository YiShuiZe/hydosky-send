package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <h3>hydosky-send</h3>
 * <p>周金额实体类</p>
 * Created by yang on 19-12-15 下午6:19
 * updated by yang on 19-12-15 下午6:19
 */
@TableName(value = "week_price_trend")
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class WeekPriceTrend implements Serializable {
    private static final long serialVersionUID = 1L;

    // 周一日期
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    private LocalDate monDate;
    // 周订单金额
    private Long orderPrice;

}

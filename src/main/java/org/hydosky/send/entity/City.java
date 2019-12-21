package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     城市实体类
 * </p>
 * Created by yang on 19-12-1 下午12:31
 * updated by yang on 19-12-1 下午12:31
 */
@Data
@TableName(value = "city")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    // 城市名称
    @NotEmpty(message = "城市名称不能为空")
    @Length(min = 2, max = 10, message = "城市名称长度在2~10位之间")
    @NonNull
    private String name;

    @NotNull(message = "经度不能为空")
    @Digits(integer = 3, fraction = 7, message = "经度整数位数3位，小数位数7位")
    private BigDecimal lng;

    @NotNull(message = "纬度不能为空")
    @Digits(integer = 3, fraction = 7, message = "纬度整数位数3位，小数位数7位")
    private BigDecimal lat;

    // 订单数量
    @Min(value = 0, message = "订单数量只能为0")
    @Max(value = 0, message = "订单数量只能为0")
    private Long orderCount;

    // 城市系数
    @NotNull(message = "城市系数不能为空")
    @Digits(integer = 1, fraction = 3, message = "城市系数整数位数1位，小数位数3位")
    private BigDecimal weight;
}

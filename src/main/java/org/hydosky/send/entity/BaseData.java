package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     基准数据
 * </p>
 * Created by yang on 19-12-21 下午5:02
 * updated by yang on 19-12-21 下午5:02
 */
@Data
@TableName(value = "base_data")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class BaseData implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "城市名称不能为空")
    @Length(min = 2, max = 10, message = "城市名称长度在2~10位之间")
    @NonNull
    private String cityName;

    @NotNull(message = "日订单数量不能为空")
    @Min(value = 20, message = "日订单数量最小值20")
    @Max(value = 100000, message = "日订单数量最大值100000")
    private Integer dayOrderCount;

    @NotNull(message = "开始营业时间不能为空")
    @Length(max = 5, message = "开始营业时间最大长度为5")
    private String startTime;

    @NotNull(message = "结束营业时间不能为空")
    @Length(max = 5, message = "结束营业时间最大长度为5")
    private String endTime;

    @NotNull(message = "货物件数区间不能为空")
    @Length(max = 8, message = "货物件数区间最大长度为8")
    private String goodsCountScope;

    @NotNull(message = "订单金额区间不能为空")
    @Length(max = 8, message = "订单金额区间最大长度为8")
    private String priceScope;

    @NotNull(message = "天数不能为空")
    @Min(value = 1, message = "天数最小值为1")
    @Max(value = 999, message = "天数最大值为999")
    private Integer days;

}

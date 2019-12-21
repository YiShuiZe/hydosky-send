package org.hydosky.send.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     货物分类实体
 * </p>
 * Created by yang on 19-12-1 下午12:33
 * updated by yang on 19-12-1 下午12:33
 */
@TableName(value = "category")
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 父主键
    private Integer parentId;
    // 分类名称
    @NotEmpty(message = "分类名称不能为空")
    @Length(min = 4, max = 10, message = "分类名称长度在4~10位之间")
    private String name;

}

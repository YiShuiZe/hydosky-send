package org.hydosky.send.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hydosky.send.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     分类视图类
 * </p>
 * Created by yang on 19-12-1 下午3:18
 * updated by yang on 19-12-1 下午3:18
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 父主键
    private Integer parentId;
    // 分类名称
    private String name;
    // 子分类
    private List<Category> children;
}

package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.Category;
import org.hydosky.send.vo.CategoryVO;

import java.util.List;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     分类业务接口
 * </p>
 * Created by yang on 19-12-1 下午2:31
 * updated by yang on 19-12-1 下午2:31
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryVO> treeList();
}

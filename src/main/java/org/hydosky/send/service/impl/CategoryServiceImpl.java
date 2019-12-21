package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.Category;
import org.hydosky.send.vo.CategoryVO;
import org.hydosky.send.mapper.CategoryMapper;
import org.hydosky.send.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     分类业务实现类
 * </p>
 * Created by yang on 19-12-1 下午2:32
 * updated by yang on 19-12-1 下午2:32
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<CategoryVO> treeList() {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        List<Category> categoryList = baseMapper.selectList(Wrappers.lambdaQuery());
        categoryList.forEach(categoryParent -> {
            if (categoryParent.getParentId() == 0) {
                List<Category> categoryChildrenList = new ArrayList<>();
                CategoryVO categoryParentVO = CategoryVO.of().setId(categoryParent.getId()).setParentId(categoryParent.getParentId()).setName(categoryParent.getName());
                categoryList.forEach(categoryChildren -> {
                    if (categoryChildren.getParentId().equals(categoryParent.getId())) {
                        categoryChildrenList.add(categoryChildren);
                    }
                });
                categoryParentVO.setChildren(categoryChildrenList);
                categoryVOList.add(categoryParentVO);
            }
        });
        return categoryVOList;
    }
}

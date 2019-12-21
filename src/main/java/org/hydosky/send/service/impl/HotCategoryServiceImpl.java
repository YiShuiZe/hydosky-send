package org.hydosky.send.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hydosky.send.entity.HotCategory;
import org.hydosky.send.mapper.HotCategoryMapper;
import org.hydosky.send.service.IHotCategoryService;
import org.springframework.stereotype.Service;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:26
 * updated by yang on 19-12-1 下午5:26
 */
@Service
public class HotCategoryServiceImpl extends ServiceImpl<HotCategoryMapper, HotCategory> implements IHotCategoryService {
    @Override
    public int insertOrUpdate(HotCategory hotCategory) {
        return baseMapper.insertOrUpdate(hotCategory);
    }
}

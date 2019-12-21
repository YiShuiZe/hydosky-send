package org.hydosky.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hydosky.send.entity.HotCategory;

/**
 * <h3>hydosky-send</h3>
 * <p>${description}</p>
 * Created by yang on 19-12-1 下午5:24
 * updated by yang on 19-12-1 下午5:24
 */
public interface IHotCategoryService extends IService<HotCategory> {
    int insertOrUpdate(HotCategory hotCategory);
}

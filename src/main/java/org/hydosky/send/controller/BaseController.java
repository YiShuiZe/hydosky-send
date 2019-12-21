package org.hydosky.send.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.hydosky.send.entity.BaseData;
import org.hydosky.send.entity.Category;
import org.hydosky.send.entity.City;
import org.hydosky.send.service.IBaseDataService;
import org.hydosky.send.service.ICityService;
import org.hydosky.send.vo.CategoryVO;
import org.hydosky.send.response.R;
import org.hydosky.send.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     基础数据接口
 * </p>
 * Created by yang on 19-12-1 下午2:34
 * updated by yang on 19-12-1 下午2:34
 */
@RestController
public class BaseController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IBaseDataService baseDataService;

    /********** 基准数据相关接口    ***********/

    // 更新基准数据。如更新基准数据 赣州 的 日订单数量
    @PostMapping("/base-data/add")
    public R<Boolean> updateBaseData(@Valid @RequestBody BaseData baseData) {
        return baseDataService.count(Wrappers.lambdaQuery(BaseData.of(baseData.getCityName()))) == 0 ? R.fail("该城市记录不存在，无法更新") : R.data(baseDataService.update(baseData, Wrappers.<BaseData>lambdaUpdate().eq(BaseData::getCityName, baseData.getCityName())));
    }

    /********** 城市相关接口    ***********/

    @PostMapping("/city/add")
    public R<Boolean> addCity(@Valid @RequestBody City city) {
        return cityService.count(Wrappers.lambdaQuery(City.of(city.getName()))) == 0 ? R.data(cityService.save(city)) : R.fail("该城市已存在，请勿重复添加！");
    }

    @GetMapping("/city/list")
    public R<List<City>> listCity() {
        return R.data(cityService.list());
    }

    @GetMapping("/city/check/{name}")
    public R<Boolean> checkCity(@PathVariable("name") String name) {
        return R.data(cityService.count(Wrappers.lambdaQuery(City.of(name))) != 0);
    }


    /********** 品类相关接口    ***********/

    @PostMapping("/category/add")
    public R<Boolean> addCategory(@Valid @RequestBody Category category) {
        if (category.getParentId() == null || category.getParentId().equals(0)) {
            return categoryService.count(Wrappers.lambdaQuery(category.setParentId(0))) == 0 ? R.data(categoryService.save(category)) : R.fail(400, "该分类已存在，请勿重复添加！");
        } else {
            return categoryService.count(Wrappers.lambdaQuery(Category.of().setId(category.getParentId()))) == 0 ? R.fail(400, "添加失败，该父级分类不存在！") : R.data(categoryService.save(category));
        }
    }

    @GetMapping("/category/list")
    public R<List<CategoryVO>> listCategory() {
        return R.data(categoryService.treeList());
    }

}

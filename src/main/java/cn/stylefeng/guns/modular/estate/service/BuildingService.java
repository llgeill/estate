package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.Building;
import cn.stylefeng.guns.modular.estate.model.params.BuildingParam;
import cn.stylefeng.guns.modular.estate.model.result.BuildingResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 城区 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface BuildingService extends IService<Building> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void add(BuildingParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void delete(BuildingParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void update(BuildingParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    BuildingResult findBySpec(BuildingParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    List<BuildingResult> findListBySpec(BuildingParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
     LayuiPageInfo findPageBySpec(BuildingParam param);

    /**
     * 获取所有城区信息列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    Page<Map<String, Object>> list(String temp);

}

package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import cn.stylefeng.guns.modular.estate.model.params.BuildingBlockParam;
import cn.stylefeng.guns.modular.estate.model.result.BuildingBlockResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 楼盘 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface BuildingBlockService extends IService<BuildingBlock> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void add(BuildingBlockParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void delete(BuildingBlockParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void update(BuildingBlockParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    BuildingBlockResult findBySpec(BuildingBlockParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    List<BuildingBlockResult> findListBySpec(BuildingBlockParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
     LayuiPageInfo findPageBySpec(BuildingBlockParam param);

    Page<Map<String, Object>> list(String temp);

}

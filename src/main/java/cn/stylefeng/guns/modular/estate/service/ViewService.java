package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.View;
import cn.stylefeng.guns.modular.estate.model.params.ViewParam;
import cn.stylefeng.guns.modular.estate.model.result.ViewResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视图 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface ViewService extends IService<View> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void add(ViewParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void delete(ViewParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void update(ViewParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    ViewResult findBySpec(ViewParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    List<ViewResult> findListBySpec(ViewParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
     LayuiPageInfo findPageBySpec(ViewParam param);

}

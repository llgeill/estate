package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.City;
import cn.stylefeng.guns.modular.estate.model.params.CityParam;
import cn.stylefeng.guns.modular.estate.model.result.CityResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface CityService extends IService<City> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void add(CityParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void delete(CityParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void update(CityParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    CityResult findBySpec(CityParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    List<CityResult> findListBySpec(CityParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
     LayuiPageInfo findPageBySpec(CityParam param);

}

package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.model.result.HouseResourceResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 房源信息 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface HouseResourceService extends IService<HouseResource> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void add(HouseResourceParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void delete(HouseResourceParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    void update(HouseResourceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    HouseResourceResult findBySpec(HouseResourceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    List<HouseResourceResult> findListBySpec(HouseResourceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-07-11
     */
     LayuiPageInfo findPageBySpec(HouseResourceParam param);

     Page<Map<String, Object>> selectHouseResources(String roomNumber,Integer price_start,Integer price_end,Integer rental_start,Integer rental_end
             ,String transaction,Integer roomTotalStart,Integer roomTotalEnd);

}

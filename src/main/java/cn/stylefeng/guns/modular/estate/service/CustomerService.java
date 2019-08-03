package cn.stylefeng.guns.modular.estate.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.Customer;
import cn.stylefeng.guns.modular.estate.model.CustomerDto;
import cn.stylefeng.guns.modular.estate.model.FollowInfoDto;
import cn.stylefeng.guns.modular.estate.model.params.CustomerParam;
import cn.stylefeng.guns.modular.estate.model.result.CustomerResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户信息 服务类
 * </p>
 *
 * @author 李利光
 * @since 2019-08-03
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    void add(CustomerParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    void delete(CustomerParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    void update(CustomerParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    CustomerResult findBySpec(CustomerParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    List<CustomerResult> findListBySpec(CustomerParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2019-08-03
     */
     LayuiPageInfo findPageBySpec(CustomerParam param);

    /**
     * 获取所有跟進信息列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    Page<Map<String, Object>> list(CustomerDto customerDto);

}

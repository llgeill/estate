package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.Customer;
import cn.stylefeng.guns.modular.estate.mapper.CustomerMapper;
import cn.stylefeng.guns.modular.estate.model.CustomerDto;
import cn.stylefeng.guns.modular.estate.model.FollowInfoDto;
import cn.stylefeng.guns.modular.estate.model.params.CustomerParam;
import cn.stylefeng.guns.modular.estate.model.result.CustomerResult;
import  cn.stylefeng.guns.modular.estate.service.CustomerService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户信息 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-08-03
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public void add(CustomerParam param){
        Customer entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CustomerParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CustomerParam param){
        Customer oldEntity = getOldEntity(param);
        Customer newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CustomerResult findBySpec(CustomerParam param){
        return null;
    }

    @Override
    public List<CustomerResult> findListBySpec(CustomerParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CustomerParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Customer> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 获取所有跟進信息列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(CustomerDto customerDto) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page,customerDto);
    }

    private Serializable getKey(CustomerParam param){
        return param.getCustomerId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Customer getOldEntity(CustomerParam param) {
        return this.getById(getKey(param));
    }

    private Customer getEntity(CustomerParam param) {
        Customer entity = new Customer();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

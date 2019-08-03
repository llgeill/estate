package cn.stylefeng.guns.modular.estate.mapper;

import cn.stylefeng.guns.modular.estate.entity.Customer;
import cn.stylefeng.guns.modular.estate.model.CustomerDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 客户信息 Mapper 接口
 * </p>
 *
 * @author 李利光
 * @since 2019-08-03
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 获取所有跟进信息列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("customer") CustomerDto customer);

}

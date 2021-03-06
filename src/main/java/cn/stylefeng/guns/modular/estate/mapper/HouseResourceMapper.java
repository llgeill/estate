package cn.stylefeng.guns.modular.estate.mapper;

import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.model.HouseResourceSearchDto;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 房源信息 Mapper 接口
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface HouseResourceMapper extends BaseMapper<HouseResource> {
    /**
     * 根据条件查询用户列表
     */
    Page<Map<String, Object>> selectHouseResources(@Param("page") Page page, @Param("hrsDto") HouseResourceSearchDto houseResourceSearchDto);

}

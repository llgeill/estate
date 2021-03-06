package cn.stylefeng.guns.modular.estate.mapper;

import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 楼盘 Mapper 接口
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public interface BuildingBlockMapper extends BaseMapper<BuildingBlock> {

    /**
     * 获取所有城区信息列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page);

}

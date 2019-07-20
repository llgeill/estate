package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.Building;
import cn.stylefeng.guns.modular.estate.mapper.BuildingMapper;
import cn.stylefeng.guns.modular.estate.model.params.BuildingParam;
import cn.stylefeng.guns.modular.estate.model.result.BuildingResult;
import  cn.stylefeng.guns.modular.estate.service.BuildingService;
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
 * 城区 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    @Override
    public void add(BuildingParam param){
        Building entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BuildingParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BuildingParam param){
        Building oldEntity = getOldEntity(param);
        Building newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BuildingResult findBySpec(BuildingParam param){
        return null;
    }

    @Override
    public List<BuildingResult> findListBySpec(BuildingParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BuildingParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Building> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BuildingParam param){
        return param.getBuildingId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Building getOldEntity(BuildingParam param) {
        return this.getById(getKey(param));
    }

    private Building getEntity(BuildingParam param) {
        Building entity = new Building();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }


    /**
     * 获取所有城区信息列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String temp) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page);
    }

}

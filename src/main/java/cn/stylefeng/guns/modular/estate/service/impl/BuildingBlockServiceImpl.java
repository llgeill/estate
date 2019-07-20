package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import cn.stylefeng.guns.modular.estate.mapper.BuildingBlockMapper;
import cn.stylefeng.guns.modular.estate.model.params.BuildingBlockParam;
import cn.stylefeng.guns.modular.estate.model.result.BuildingBlockResult;
import  cn.stylefeng.guns.modular.estate.service.BuildingBlockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 楼盘 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class BuildingBlockServiceImpl extends ServiceImpl<BuildingBlockMapper, BuildingBlock> implements BuildingBlockService {

    @Override
    public void add(BuildingBlockParam param){
        BuildingBlock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BuildingBlockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BuildingBlockParam param){
        BuildingBlock oldEntity = getOldEntity(param);
        BuildingBlock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BuildingBlockResult findBySpec(BuildingBlockParam param){
        return null;
    }

    @Override
    public List<BuildingBlockResult> findListBySpec(BuildingBlockParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BuildingBlockParam param){
        Page pageContext = getPageContext();
        QueryWrapper<BuildingBlock> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BuildingBlockParam param){
        return param.getBuildingBlockId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private BuildingBlock getOldEntity(BuildingBlockParam param) {
        return this.getById(getKey(param));
    }

    private BuildingBlock getEntity(BuildingBlockParam param) {
        BuildingBlock entity = new BuildingBlock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.mapper.HouseResourceMapper;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.model.result.HouseResourceResult;
import  cn.stylefeng.guns.modular.estate.service.HouseResourceService;
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
 * 房源信息 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class HouseResourceServiceImpl extends ServiceImpl<HouseResourceMapper, HouseResource> implements HouseResourceService {

    @Override
    public void add(HouseResourceParam param){
        HouseResource entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(HouseResourceParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(HouseResourceParam param){
        HouseResource oldEntity = getOldEntity(param);
        HouseResource newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public HouseResourceResult findBySpec(HouseResourceParam param){
        return null;
    }

    @Override
    public List<HouseResourceResult> findListBySpec(HouseResourceParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(HouseResourceParam param){
        Page pageContext = getPageContext();
        QueryWrapper<HouseResource> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(HouseResourceParam param){
        return param.getHouseResourceId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private HouseResource getOldEntity(HouseResourceParam param) {
        return this.getById(getKey(param));
    }

    private HouseResource getEntity(HouseResourceParam param) {
        HouseResource entity = new HouseResource();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

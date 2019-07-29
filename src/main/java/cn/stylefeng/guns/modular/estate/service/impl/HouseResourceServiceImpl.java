package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.mapper.HouseResourceMapper;
import cn.stylefeng.guns.modular.estate.model.HouseResourceSearchDto;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.model.result.HouseResourceResult;
import  cn.stylefeng.guns.modular.estate.service.HouseResourceService;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseData addItemBeforeCheck(HouseResourceParam houseResourceParam){
        QueryWrapper<HouseResource> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("building_block_id", houseResourceParam.getBuildingBlockId())
                .eq("room_number", houseResourceParam.getRoomNumber());
        List<HouseResource> buildingBlocks = this.list(queryWrapper);
        if(buildingBlocks.size()>0){
            return ResponseData.error("房源已经存在！");
        }
        this.add(houseResourceParam);
        return ResponseData.success();
    }

    /**
     * 根据条件查询房源列表
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:45
     */
    @Override
    public Page<Map<String, Object>> selectHouseResources(HouseResourceSearchDto houseResourceSearchDto) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectHouseResources(page,houseResourceSearchDto);
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

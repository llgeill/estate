package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.City;
import cn.stylefeng.guns.modular.estate.mapper.CityMapper;
import cn.stylefeng.guns.modular.estate.model.params.CityParam;
import cn.stylefeng.guns.modular.estate.model.result.CityResult;
import  cn.stylefeng.guns.modular.estate.service.CityService;
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
 * 城市 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public void add(CityParam param){
        City entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CityParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CityParam param){
        City oldEntity = getOldEntity(param);
        City newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CityResult findBySpec(CityParam param){
        return null;
    }

    @Override
    public List<CityResult> findListBySpec(CityParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CityParam param){
        Page pageContext = getPageContext();
        QueryWrapper<City> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CityParam param){
        return param.getCityId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private City getOldEntity(CityParam param) {
        return this.getById(getKey(param));
    }

    private City getEntity(CityParam param) {
        City entity = new City();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

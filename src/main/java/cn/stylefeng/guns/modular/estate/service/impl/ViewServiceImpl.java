package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.View;
import cn.stylefeng.guns.modular.estate.mapper.ViewMapper;
import cn.stylefeng.guns.modular.estate.model.params.ViewParam;
import cn.stylefeng.guns.modular.estate.model.result.ViewResult;
import  cn.stylefeng.guns.modular.estate.service.ViewService;
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
 * 视图 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {

    @Override
    public void add(ViewParam param){
        View entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ViewParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ViewParam param){
        View oldEntity = getOldEntity(param);
        View newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ViewResult findBySpec(ViewParam param){
        return null;
    }

    @Override
    public List<ViewResult> findListBySpec(ViewParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ViewParam param){
        Page pageContext = getPageContext();
        QueryWrapper<View> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getViewId())) {
            objectQueryWrapper.and(i -> i.eq("view_id", param.getViewId()).or().eq("view_id", param.getViewId()));
        }
//        pageContext.setAsc("sort");
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ViewParam param){
        return param.getViewId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private View getOldEntity(ViewParam param) {
        return this.getById(getKey(param));
    }

    private View getEntity(ViewParam param) {
        View entity = new View();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

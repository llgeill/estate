package cn.stylefeng.guns.modular.estate.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.FollowInfo;
import cn.stylefeng.guns.modular.estate.mapper.FollowInfoMapper;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.model.result.FollowInfoResult;
import  cn.stylefeng.guns.modular.estate.service.FollowInfoService;
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
 * 跟进信息 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Service
public class FollowInfoServiceImpl extends ServiceImpl<FollowInfoMapper, FollowInfo> implements FollowInfoService {

    @Override
    public void add(FollowInfoParam param){
        FollowInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FollowInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FollowInfoParam param){
        FollowInfo oldEntity = getOldEntity(param);
        FollowInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FollowInfoResult findBySpec(FollowInfoParam param){
        return null;
    }

    @Override
    public List<FollowInfoResult> findListBySpec(FollowInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FollowInfoParam param){
        Page pageContext = getPageContext();
        QueryWrapper<FollowInfo> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FollowInfoParam param){
        return param.getFollowInfoId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FollowInfo getOldEntity(FollowInfoParam param) {
        return this.getById(getKey(param));
    }

    private FollowInfo getEntity(FollowInfoParam param) {
        FollowInfo entity = new FollowInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

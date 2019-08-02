package cn.stylefeng.guns.modular.estate.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.estate.entity.FollowInfo;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.model.FollowInfoDto;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.service.FollowInfoService;
import cn.stylefeng.guns.modular.estate.service.HouseResourceService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.model.MenuDto;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.DeptIdWrapper;
import cn.stylefeng.guns.modular.system.warpper.DeptWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 跟进信息控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/followInfo")
public class FollowInfoController extends BaseController {

    @Autowired
    private UserService userService;

    private String PREFIX = "/modular/estate/followInfo";

    @Autowired
    private FollowInfoService followInfoService;
    @Autowired
    private HouseResourceService houseResourceService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("")
    @Permission({Const.BOSS_NAME,Const.ADMIN_NAME})
    public String index(Model model,Long staffId) {
        if(staffId==null)staffId=ShiroKit.getUser().getId();
        model.addAttribute("staffId",staffId);
        return PREFIX + "/followInfo.html";
    }

    /**
     * 跳转到slave主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/slave")
    public String indexSlave(Model model,Long staffId) {
        if(staffId==null)staffId=ShiroKit.getUser().getId();
        model.addAttribute("staffId",staffId);
        return PREFIX + "/followInfo_slave.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add(Model model,Long houseResourceId) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        model.addAllAttributes(BeanUtil.beanToMap(user));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        model.addAttribute("houseResourceId",houseResourceId);
        return PREFIX + "/followInfo_add.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/forceAdd")
    public String forceAdd(Model model,Long houseResourceId) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        model.addAllAttributes(BeanUtil.beanToMap(user));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        model.addAttribute("houseResourceId",houseResourceId);
        return PREFIX + "/followInfo_force_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
    @Permission({Const.BOSS_NAME,Const.ADMIN_NAME})
    public String edit() {
        return PREFIX + "/followInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FollowInfoParam followInfoParam) {

        HouseResource detail=houseResourceService.getById(followInfoParam.getHouseResourceId());
        Map map=BeanUtil.beanToMap(detail);
        map.put("buildingNameNumber", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId"))+" "+detail.getRoomNumber());
        String msg= (String) map.get("buildingNameNumber");
        if(ToolUtil.isEmpty(followInfoParam.getStaffName()))followInfoParam.setStaffName(ShiroKit.getUser().getName());
        if(ToolUtil.isEmpty(followInfoParam.getDeptId()))followInfoParam.setDeptId(ShiroKit.getUser().getDeptId());
        followInfoParam.setContent(msg+": "+followInfoParam.getContent());
        followInfoParam.setStaffId(ShiroKit.getUser().getId());
        this.followInfoService.add(followInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/editItem")
    @ResponseBody
    @Permission({Const.BOSS_NAME,Const.ADMIN_NAME})
    public ResponseData editItem(FollowInfoParam followInfoParam) {
        this.followInfoService.update(followInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Permission({Const.BOSS_NAME,Const.ADMIN_NAME})
    public ResponseData delete(FollowInfoParam followInfoParam) {
        this.followInfoService.delete(followInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FollowInfoParam followInfoParam) {
        FollowInfo detail = this.followInfoService.getById(followInfoParam.getFollowInfoId());
        FollowInfoDto followInfoDto = new FollowInfoDto();
        BeanUtil.copyProperties(detail, followInfoDto);
        followInfoDto.setPName(ConstantFactory.me().getDeptName(followInfoDto.getDeptId()));
        return ResponseData.success(followInfoDto);
    }



    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FollowInfoDto followInfoDto) {
        //精确范围范围
        if (ToolUtil.isNotEmpty(followInfoDto.getEntrustBetweenTime())) {
            String[] split = followInfoDto.getEntrustBetweenTime().split(" - ");
            if (split.length > 1) {
                followInfoDto.setBeginTime(split[0]);
                followInfoDto.setEndTime(split[1]);
            }
        }
        if(ToolUtil.isNotEmpty(followInfoDto.getQuickTime())){
            if(followInfoDto.getQuickTime()==0)followInfoDto.setQuickTime(null);
            else if(followInfoDto.getQuickTime()==1)followInfoDto.setQuickTime(1);
            else followInfoDto.setQuickTime(followInfoDto.getQuickTime()-1);
        }
        Page<Map<String, Object>> list = this.followInfoService.list(followInfoDto);
        Page<Map<String, Object>> wrap = new DeptIdWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
        //return this.followInfoService.findPageBySpec(followInfoParam);
    }

    /**
     * 查询相应房源跟进信息
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/followInfoList")
    public List<FollowInfo> followInfoList(FollowInfoParam followInfoParam) {
        QueryWrapper<FollowInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("house_resource_id",followInfoParam.getHouseResourceId()).orderByDesc("create_time")
                ;
        return this.followInfoService.list(queryWrapper);

    }



}



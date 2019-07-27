package cn.stylefeng.guns.modular.estate.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.HouseResourceDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.Contrast;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.model.HouseResourceSearchDto;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.service.FollowInfoService;
import cn.stylefeng.guns.modular.estate.service.HouseResourceService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.HouseResourceWrapper;
import cn.stylefeng.guns.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 房源信息控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/houseResource")
public class HouseResourceController extends BaseController {

    private String PREFIX = "/modular/estate/houseResource";

    @Autowired
    private HouseResourceService houseResourceService;
    @Autowired
    private FollowInfoService followInfoService;
    @Autowired
    private UserService userService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/houseResource.html";
    }

    /**
     * 跳转到Master主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/master")
    public String indexMaster() {
        return PREFIX + "/houseResource_master.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/houseResource_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/houseResource_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(HouseResourceParam houseResourceParam) {
        this.houseResourceService.add(houseResourceParam);
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
    public ResponseData editItem(HouseResourceParam houseResourceParam) {
        HouseResource detail = this.houseResourceService.getById(houseResourceParam.getHouseResourceId());
        Map map=BeanUtil.beanToMap(detail);
        map.put("buildingBlockName", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")));
        map.put("buildingName", ConstantFactory.me().getBuildingName((Long) map.get("buildingId")));
        map.put("deptName",ConstantFactory.me().getDeptNameByUserId((Long)map.get("staffId")));
        map.put("buildingNameNumber", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId"))+" "+houseResourceParam.getRoomNumber());
        map.remove("createTime");
        String msg="房源信息变动：";
        try {
             msg+= Contrast.contrastObj(HouseResourceDict.class, "buildingNameNumber", houseResourceParam, map);
             msg=msg.replaceAll("null=","");
             if(!msg.equals(("房源信息变动："+ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId"))+" "+houseResourceParam.getRoomNumber()) +"—>")){
                 Long userId = ShiroKit.getUserNotNull().getId();
                 User user = this.userService.getById(userId);
                 FollowInfoParam followInfoParam=new FollowInfoParam();
                 followInfoParam.setHouseResourceId(houseResourceParam.getHouseResourceId());
                 followInfoParam.setContent(msg);
                 followInfoParam.setDeptId(user.getDeptId());
                 followInfoParam.setStaffName(user.getName());
                 this.followInfoService.add(followInfoParam);
             }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        this.houseResourceService.update(houseResourceParam);
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
    public ResponseData delete(HouseResourceParam houseResourceParam) {
        this.houseResourceService.delete(houseResourceParam);
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
    public ResponseData detail(HouseResourceParam houseResourceParam) {
        HouseResource detail = this.houseResourceService.getById(houseResourceParam.getHouseResourceId());
        Map map=BeanUtil.beanToMap(detail);
        map.put("buildingBlockName", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")));
        map.put("buildingName", ConstantFactory.me().getBuildingName((Long) map.get("buildingId")));
        map.put("deptName",ConstantFactory.me().getDeptNameByUserId((Long)map.get("staffId")));
        return ResponseData.success(map);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list( HouseResourceSearchDto houseResourceSearchDto) {
        //查询条件
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getCondition())) {
            String[] split = houseResourceSearchDto.getCondition().split("-");
            if(split.length>1){
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[1]);
            }else{
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[0]);
            }
        }
        //房型
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getRoomTotal())) {
            String[] split = houseResourceSearchDto.getRoomTotal().split("-");
            houseResourceSearchDto.setRoomTotalStart(Integer.parseInt(split[0]));
            if(split.length>1){
                houseResourceSearchDto.setRoomTotalEnd(Integer.parseInt(split[1]));
            }
        }
        //租价
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getRental())) {
            String[] split = houseResourceSearchDto.getRental().split("-");
            houseResourceSearchDto.setRentalStart(Double.parseDouble(split[0]));
            if(split.length>1){
                houseResourceSearchDto.setRentalEnd(Double.parseDouble(split[1]));
            }
        }
        //售价
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getPrice())) {
            String[] split = houseResourceSearchDto.getPrice().split("-");
            houseResourceSearchDto.setPriceStart(Double.parseDouble(split[0]));
            if(split.length>1){
                houseResourceSearchDto.setPriceEnd(Double.parseDouble(split[1]));
            }
        }
        //面积
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getArea())) {
            String[] split = houseResourceSearchDto.getArea().split("-");
            houseResourceSearchDto.setAreaStart(Double.parseDouble(split[0]));
            if(split.length>1){
                houseResourceSearchDto.setAreaEnd(Double.parseDouble(split[1]));
            }
        }
        //智能搜索
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getCondition())) {
            String[] split = houseResourceSearchDto.getCondition().split("-");
            if(split.length>1){
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[1]);
                houseResourceSearchDto.setCondition(null);
            }
        }
        //委托范围
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getEntrustBetweenTime())) {
            String[] split = houseResourceSearchDto.getEntrustBetweenTime().split(" - ");
            if(split.length>1){
                houseResourceSearchDto.setBeginTime(split[0]);
                houseResourceSearchDto.setEndTime(split[1]);
            }
        }
        Page<Map<String, Object>> users = houseResourceService.selectHouseResources(houseResourceSearchDto);

        Page wrapped = new HouseResourceWrapper(users).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
//        return LayuiPageFactory.createPageInfo(users);
    }

    /**
     * 查询管理员列表
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
//    @RequestMapping("/list")
//    @Permission
//    @ResponseBody
//    public Object list(@RequestParam(required = false) String name,
//                       @RequestParam(required = false) String timeLimit,
//                       @RequestParam(required = false) Long deptId) {

//        //拼接查询条件
//        String beginTime = "";
//        String endTime = "";
//
//        if (ToolUtil.isNotEmpty(timeLimit)) {
//            String[] split = timeLimit.split(" - ");
//            beginTime = split[0];
//            endTime = split[1];
//        }
//
//        if (ShiroKit.isAdmin()) {
//            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptId);
//            Page wrapped = new UserWrapper(users).wrap();
//            return LayuiPageFactory.createPageInfo(wrapped);
//        } else {
//            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
//            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptId);
//            Page wrapped = new UserWrapper(users).wrap();
//            return LayuiPageFactory.createPageInfo(wrapped);
//        }
//    }


}



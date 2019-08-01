package cn.stylefeng.guns.modular.estate.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.HouseResourceDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.Contrast;
import cn.stylefeng.guns.core.util.IsNumberUtil;
import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import cn.stylefeng.guns.modular.estate.entity.FollowInfo;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.entity.RenderComparator;
import cn.stylefeng.guns.modular.estate.model.HouseResourceSearchDto;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.service.BuildingBlockService;
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
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    @Autowired
    private BuildingBlockService buildingBlockService;

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
     * 跳转到Master主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/slave")
    public String indexSlave(Model model, Long belongId) {
        if(belongId==null)belongId=ShiroKit.getUser().getId();
        model.addAttribute("belongId",belongId);
        return PREFIX + "/houseResource_slave.html";
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
        if(houseResourceParam.getHouseResourceType()==null||houseResourceParam.getHouseResourceType().equals(""))
            houseResourceParam.setHouseResourceType("推介房");
        houseResourceParam.setUpdateTime(new Date());
        return houseResourceService.addItemBeforeCheck(houseResourceParam);
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
        Map map = BeanUtil.beanToMap(houseResourceParam);
        map.put("buildingBlockName", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")));
        map.put("buildingName", ConstantFactory.me().getBuildingName((Long) map.get("buildingId")));
        map.put("deptName", ConstantFactory.me().getDeptNameByUserId((Long) map.get("staffId")));
        map.put("buildingNameNumber", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")) + " " + houseResourceParam.getRoomNumber());
        map.remove("createTime");
        String msg = "房源信息变动：";
        try {
            msg += Contrast.contrastObj(HouseResourceDict.class, "buildingNameNumber", detail, map);
            msg = msg.replaceAll("null=", "");
            if (!msg.equals(("房源信息变动：" + ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")) + " " + houseResourceParam.getRoomNumber()) + "—>")) {
                Long userId = ShiroKit.getUserNotNull().getId();
                User user = this.userService.getById(userId);
                FollowInfoParam followInfoParam = new FollowInfoParam();
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
        houseResourceParam.setStaffId(ShiroKit.getUser().getId());
        houseResourceParam.setStaff(ShiroKit.getUser().getName());
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
        Map map = BeanUtil.beanToMap(detail);
        map.put("buildingBlockName", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")));
        map.put("buildingName", ConstantFactory.me().getBuildingName((Long) map.get("buildingId")));
        map.put("deptName", ConstantFactory.me().getDeptNameByUserId((Long) map.get("staffId")));
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
    public LayuiPageInfo list(HouseResourceSearchDto houseResourceSearchDto) {
        //默认所有都是推介房
        //高級用戶 查看所有房源  按条件查看房源
        //普通用户 查看所有推介房源  不包含加密房源
        if(houseResourceSearchDto.getHouseResourceType()==null||houseResourceSearchDto.getHouseResourceType().equals("")){
            houseResourceSearchDto.setHouseResourceType("推介房");
            //高权限用户分配所有房源(加密+推介)
            if(ShiroKit.isAdmin()||ShiroKit.isBoss()){
                houseResourceSearchDto.setHouseResourceType(null);
            }
        }

        //查询条件
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getCondition())) {
            houseResourceSearchDto.setCondition(houseResourceSearchDto.getCondition().trim());
            String[] split = houseResourceSearchDto.getCondition().split("-");
            if (split.length > 1) {
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[1]);
            } else {
                houseResourceSearchDto.setOwnerPhone(split[0]);
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[0]);
            }
        }
        //查询对应的building_block_id
        QueryWrapper<BuildingBlock> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("name", houseResourceSearchDto.getBuildingBlock());
        List<BuildingBlock> buildingBlocks = buildingBlockService.list(queryWrapper);
        if (buildingBlocks.size() > 0) {
            houseResourceSearchDto.setBuildingBlockId(buildingBlocks.get(0).getBuildingBlockId());
            houseResourceSearchDto.setRoomNumber(null);
            houseResourceSearchDto.setOwnerPhone(null);
        } else {
            houseResourceSearchDto.setBuildingBlock(null);
            boolean flag1=false;
            boolean flag2=false;
            boolean flag3=false;
            flag1=houseResourceSearchDto.getOwnerPhone()!=null;
            if(houseResourceSearchDto.getOwnerPhone()!=null){
                flag2=houseResourceSearchDto.getOwnerPhone().length()==11;
                flag3=IsNumberUtil.isNumeric(houseResourceSearchDto.getOwnerPhone());
            }
            if(flag1&&flag2&&flag3){

                houseResourceSearchDto.setRoomNumber(null);
            }else{
                houseResourceSearchDto.setOwnerPhone(null);
            }
        }
        //房型
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getRoomTotal())) {
            String[] split = houseResourceSearchDto.getRoomTotal().split("-");
            houseResourceSearchDto.setRoomTotalStart(Integer.parseInt(split[0]));
            if (split.length > 1) {
                houseResourceSearchDto.setRoomTotalEnd(Integer.parseInt(split[1]));
            }
        }
        //租价
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getRental())) {
            String[] split = houseResourceSearchDto.getRental().split("-");
            houseResourceSearchDto.setRentalStart(Double.parseDouble(split[0]));
            if (split.length > 1) {
                houseResourceSearchDto.setRentalEnd(Double.parseDouble(split[1]));
            }
        }
        //售价
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getPrice())) {
            String[] split = houseResourceSearchDto.getPrice().split("-");
            houseResourceSearchDto.setPriceStart(Double.parseDouble(split[0]));
            if (split.length > 1) {
                houseResourceSearchDto.setPriceEnd(Double.parseDouble(split[1]));
            }
        }
        //面积
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getArea())) {
            String[] split = houseResourceSearchDto.getArea().split("-");
            houseResourceSearchDto.setAreaStart(Double.parseDouble(split[0]));
            if (split.length > 1) {
                houseResourceSearchDto.setAreaEnd(Double.parseDouble(split[1]));
            }
        }
        //尚未跟进
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getQuickTime())) {
            if(houseResourceSearchDto.getQuickTime()==0)houseResourceSearchDto.setQuickTime(null);
            else houseResourceSearchDto.setQuickTime(houseResourceSearchDto.getQuickTime()-1);
        }
        //查询厅卫数量
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getHallToilet())) {
            if (ToolUtil.isNotEmpty(houseResourceSearchDto.getHallToiletTotal())){
                if(houseResourceSearchDto.getHallToilet().equals("hall")){
                    houseResourceSearchDto.setHallTotal(houseResourceSearchDto.getHallToiletTotal());
                }else if(houseResourceSearchDto.getHallToilet().equals("toilet")){
                    houseResourceSearchDto.setToiletTotal(houseResourceSearchDto.getHallToiletTotal());
                }
            }

        }
        //智能搜索
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getCondition())) {
            String[] split = houseResourceSearchDto.getCondition().split("-");
            if (split.length > 1) {
                houseResourceSearchDto.setBuildingBlock(split[0]);
                houseResourceSearchDto.setRoomNumber(split[1]);
                houseResourceSearchDto.setCondition(null);
            }
        }
        //委托范围
        if (ToolUtil.isNotEmpty(houseResourceSearchDto.getEntrustBetweenTime())) {
            String[] split = houseResourceSearchDto.getEntrustBetweenTime().split(" - ");
            if (split.length > 1) {
                houseResourceSearchDto.setBeginTime(split[0]);
                houseResourceSearchDto.setEndTime(split[1]);
            }
        }
        //加密属主问题
        if(houseResourceSearchDto.getBelongId()!=null&&!houseResourceSearchDto.getBelongId().equals("")){
            houseResourceSearchDto.setStaffId(houseResourceSearchDto.getBelongId());
            if(houseResourceSearchDto.getState()==null||houseResourceSearchDto.getState().equals("")){
                houseResourceSearchDto.setStateSlave("ttt");
            }
        }
        //查询页面
        Page<Map<String, Object>> users = houseResourceService.selectHouseResources(houseResourceSearchDto);
        Page wrapped = new HouseResourceWrapper(users).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
//        return LayuiPageFactory.createPageInfo(users);
    }


    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */

    @RequestMapping("/updateState")
    @ResponseBody
    public ResponseData updateState(HouseResourceParam houseResourceParam) {
        //创建一个容器存放旧数据
        HouseResource oldDetail=new HouseResource();
        //获取旧数据
        HouseResource detail = this.houseResourceService.getById(houseResourceParam.getHouseResourceId());
        //容器旧数据复赋值
        BeanUtil.copyProperties(detail,oldDetail);
        //赋值新数据
        detail.setState(houseResourceParam.getState());
        detail.setStaff(ShiroKit.getUser().getName());
        detail.setStaffId(ShiroKit.getUser().getId());
        //赋值新数据
        houseResourceParam.setStaff(ShiroKit.getUser().getName());
        houseResourceParam.setStaffId(ShiroKit.getUser().getId());
        //修改字典
        Map map = BeanUtil.beanToMap(detail);
        map.remove("createTime");
        map.remove("updateTime");
        map.remove("belongToId");
        map.remove("staffId");
        //栋座+房号
        map.put("buildingNameNumber", ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")) + " " + detail.getRoomNumber());
        String msg = "房源信息变动：" ;
        try {
            msg += Contrast.contrastObj(HouseResourceDict.class, "buildingNameNumber", oldDetail, map);
            msg = msg.replaceAll("null=", "");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(!msg.equals(("房源信息变动：" + ConstantFactory.me().getBuildingBlockName((Long) map.get("buildingBlockId")) + " " + detail.getRoomNumber()) + "—>")){
            FollowInfoParam followInfoParam = new FollowInfoParam();
            followInfoParam.setStaffId(ShiroKit.getUser().getId());
            followInfoParam.setHouseResourceId(houseResourceParam.getHouseResourceId());
            followInfoParam.setContent(msg);
            followInfoParam.setDeptId(ShiroKit.getUser().getDeptId());
            followInfoParam.setStaffName(ShiroKit.getUser().getName());
            this.followInfoService.add(followInfoParam);
        }
        //更新字段
        this.houseResourceService.update(houseResourceParam);
        return ResponseData.success();
    }




    @RequestMapping("/salePerformance")
    @ResponseBody
    public List<Map<Object,Object>> salePerformance(String dateScope){
        //统计除了admin的所有用户租售數量
        List<User> userList=userService.list();
        List<Map<Object,Object>> mapList=new ArrayList<>();
        for(User user:userList){
            if(ConstantFactory.me().getRoleName(user.getRoleId()).equals("超级管理员"))continue;
            Map<Object,Object> map=new HashMap();
            map.put("name",user.getName());
            map.put("userId",user.getUserId());
            QueryWrapper<HouseResource> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("staff_id",user.getUserId());
            //如果有时间范围要求
            if(dateScope!=null&&!dateScope.equals("")){
                String time[]=dateScope.split(" - ");
                SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
                try {
                    Date date1 = sdf.parse(time[0]);
                    Date date2 = sdf.parse(time[1]);
                    queryWrapper.between("update_time",date1,date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            List<HouseResource> houseResourceList=houseResourceService.list(queryWrapper);
            for(HouseResource houseResource:houseResourceList){
                if(houseResource.getState().equals("我租")){
                    if(map.get("render")==null)map.put("render",1);
                    else map.put("render",(Integer)map.get("render")+1);
                }else if(houseResource.getState().equals("我售")){
                    if(map.get("sell")==null)map.put("sell",1);
                    else map.put("sell",(Integer)map.get("sell")+1);
                }
            }
            if(map.get("render")==null)map.put("render",0);
            if(map.get("sell")==null)map.put("sell",0);
            Integer render=(Integer)map.get("render");
            Integer sell=(Integer) map.get("sell");
            map.put("total",render+sell);
            mapList.add(map);
        }
        Collections.sort(mapList,new Comparator<Map<Object,Object>>() {
            @Override
            public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
                return (Integer)o2.get("total")-(Integer)o1.get("total");
            }
        });
        return mapList;
    }
}



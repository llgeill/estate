package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.model.params.HouseResourceParam;
import cn.stylefeng.guns.modular.estate.service.HouseResourceService;
import cn.stylefeng.guns.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestParam(required = false) String condition) {
        Page<Map<String, Object>> users = houseResourceService.selectHouseResources(condition,null,null,1200,1600,
                "出租",null,3,null,2,"",5);
        return LayuiPageFactory.createPageInfo(users);
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



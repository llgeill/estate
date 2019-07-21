package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.Building;
import cn.stylefeng.guns.modular.estate.model.params.BuildingParam;
import cn.stylefeng.guns.modular.estate.model.result.BuildingResult;
import cn.stylefeng.guns.modular.estate.service.BuildingService;
import cn.stylefeng.guns.modular.system.warpper.DeptIdWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 城区控制器
 *
 * @author 李利光
 * @Date 2019-07-11 13:35:03
 */
@Controller
@RequestMapping("/building")
public class BuildingController extends BaseController {

    private String PREFIX = "/modular/estate/building";

    @Autowired
    private BuildingService buildingService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/building.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/building_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/building_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BuildingParam buildingParam) {
        buildingParam.setProfileAddress(buildingParam.getProvidName()+"-"+buildingParam.getCitysidName()+"-"+buildingParam.getAreaidName());
        this.buildingService.add(buildingParam);
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
    public ResponseData editItem(BuildingParam buildingParam) {
        this.buildingService.update(buildingParam);
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
    public ResponseData delete(BuildingParam buildingParam) {
        this.buildingService.delete(buildingParam);
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
    public ResponseData detail(BuildingParam buildingParam) {
        Building detail = this.buildingService.getById(buildingParam.getBuildingId());
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
    public LayuiPageInfo list(BuildingParam buildingParam) {
        Page<Map<String, Object>> list = this.buildingService.list("");
        Page<Map<String, Object>> wrap = new DeptIdWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
        //return this.buildingService.findPageBySpec(buildingParam);
    }

    /**
     * 获取数据列表
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/listData")
    public List<Building> listData(BuildingParam buildingParam) {
        List<Building> ss=this.buildingService.list();
        return ss;
    }

}



package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import cn.stylefeng.guns.modular.estate.model.params.BuildingBlockParam;
import cn.stylefeng.guns.modular.estate.service.BuildingBlockService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 楼盘控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/buildingBlock")
public class BuildingBlockController extends BaseController {

    private String PREFIX = "/modular/estate/buildingBlock";

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
        return PREFIX + "/buildingBlock.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/buildingBlock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/buildingBlock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BuildingBlockParam buildingBlockParam) {
        this.buildingBlockService.add(buildingBlockParam);
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
    public ResponseData editItem(BuildingBlockParam buildingBlockParam) {
        this.buildingBlockService.update(buildingBlockParam);
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
    public ResponseData delete(BuildingBlockParam buildingBlockParam) {
        this.buildingBlockService.delete(buildingBlockParam);
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
    public ResponseData detail(BuildingBlockParam buildingBlockParam) {
        BuildingBlock detail = this.buildingBlockService.getById(buildingBlockParam.getBuildingBlockId());
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
    public LayuiPageInfo list(BuildingBlockParam buildingBlockParam) {
        return this.buildingBlockService.findPageBySpec(buildingBlockParam);
    }

    /**
     * 查询特定楼盘列表
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/bulidingBlockList")
    public Collection<BuildingBlock> bulidingBlockList(Long buildingId) {
        Map map=new HashMap<>();
        map.put("building_id",buildingId);
        return this.buildingBlockService.listByMap(map);
    }

}



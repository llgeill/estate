package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.FollowInfo;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.service.FollowInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 跟进信息控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/followInfo")
public class FollowInfoController extends BaseController {

    private String PREFIX = "/modular/estate/followInfo";

    @Autowired
    private FollowInfoService followInfoService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/followInfo.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/followInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
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
    public LayuiPageInfo list(FollowInfoParam followInfoParam) {
        return this.followInfoService.findPageBySpec(followInfoParam);
    }

}



package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.View;
import cn.stylefeng.guns.modular.estate.model.params.ViewParam;
import cn.stylefeng.guns.modular.estate.service.ViewService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 视图控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/view")
public class    ViewController extends BaseController {

    private String PREFIX = "/modular/estate/view";

    @Autowired
    private ViewService viewService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/view.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/view_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/view_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ViewParam viewParam) {
        this.viewService.add(viewParam);
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
    public ResponseData editItem(ViewParam viewParam) {
        this.viewService.update(viewParam);
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
    public ResponseData delete(ViewParam viewParam) {
        this.viewService.delete(viewParam);
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
    public ResponseData detail(ViewParam viewParam) {
        View detail = this.viewService.getById(viewParam.getViewId());
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
    public LayuiPageInfo list(ViewParam viewParam) {
        return this.viewService.findPageBySpec(viewParam);
    }

}



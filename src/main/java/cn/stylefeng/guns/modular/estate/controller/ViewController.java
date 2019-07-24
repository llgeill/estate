package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.estate.entity.FollowInfo;
import cn.stylefeng.guns.modular.estate.entity.View;
import cn.stylefeng.guns.modular.estate.model.params.FollowInfoParam;
import cn.stylefeng.guns.modular.estate.model.params.ViewParam;
import cn.stylefeng.guns.modular.estate.service.ViewService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * 视图控制器
 *
 * @author 李利光
 * @Date 2019-07-11 22:26:44
 */
@Controller
@RequestMapping("/view")
public class    ViewController extends BaseController {

    @Autowired
    private GunsProperties gunsProperties;

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

    /**
     * layui上传组件 通用文件上传接口
     *
     * @author fengshuonan
     * @Date 2019-2-23 10:48:29
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile picture) {
        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        String fileSavePath="";
        try {
             fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        ViewParam viewParam=new ViewParam();
        viewParam.setCreateTime(new Date());
        viewParam.setResourceId(null);
        viewParam.setViewPath(pictureName);
        viewParam.setViewType(ToolUtil.getFileSuffix(picture.getOriginalFilename()));
        this.viewService.add(viewParam);
        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        return ResponseData.success(0, "上传成功", map);
    }

    /**
     * 查询相应多媒体信息
     *
     * @author 李利光
     * @Date 2019-07-11
     */
    @ResponseBody
    @RequestMapping("/viewList")
    public List<View> viewList(ViewParam viewParam) {
        QueryWrapper<View> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("resource_id",viewParam.getResourceId());
        return this.viewService.list(queryWrapper);
    }

}



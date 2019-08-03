package cn.stylefeng.guns.modular.estate.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.IsNumberUtil;
import cn.stylefeng.guns.modular.estate.entity.BuildingBlock;
import cn.stylefeng.guns.modular.estate.entity.Customer;
import cn.stylefeng.guns.modular.estate.model.CustomerDto;
import cn.stylefeng.guns.modular.estate.model.params.CustomerParam;
import cn.stylefeng.guns.modular.estate.service.BuildingBlockService;
import cn.stylefeng.guns.modular.estate.service.CustomerService;
import cn.stylefeng.guns.modular.system.warpper.CustomerWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;


/**
 * 客户信息控制器
 *
 * @author 李利光
 * @Date 2019-08-03 12:45:40
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private String PREFIX = "/modular/estate/customer";

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BuildingBlockService buildingBlockService;

    /**
     * 跳转到主页面
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/customer.html";
    }

    /**
     * 新增页面
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/add")
    public String add(Model model) {
        List<BuildingBlock> buildingBlockList=this.buildingBlockService.list();
        model.addAttribute("buildingBlockList",buildingBlockList);
        return PREFIX + "/customer_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/edit")
    public String edit(Model model) {
        List<BuildingBlock> buildingBlockList=this.buildingBlockService.list();
        model.addAttribute("buildingBlockList",buildingBlockList);
        return PREFIX + "/customer_edit.html";
    }

    /**
     * 编辑页面
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/detailTemp")
    public String detail(Model model) {
        List<BuildingBlock> buildingBlockList=this.buildingBlockService.list();
        model.addAttribute("buildingBlockList",buildingBlockList);
        return PREFIX + "/customer_detail.html";
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CustomerParam customerParam) {
        this.customerService.add(customerParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CustomerParam customerParam) {
        this.customerService.update(customerParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CustomerParam customerParam) {
        this.customerService.delete(customerParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(CustomerParam customerParam) {
        Customer detail = this.customerService.getById(customerParam.getCustomerId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2019-08-03
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(CustomerDto customerDto) {

        //查询条件
        if (ToolUtil.isNotEmpty(customerDto.getCondition())) {
            customerDto.setCondition(customerDto.getCondition().trim());
            if(customerDto.getCondition().length()==11&&IsNumberUtil.isNumeric(customerDto.getCondition())){
                customerDto.setPhone(customerDto.getCondition());
            }else{
                customerDto.setName(customerDto.getCondition());
            }
        }
        Page<Map<String, Object>> list = this.customerService.list(customerDto);
        Page<Map<String, Object>> wrap = new CustomerWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
//        return this.customerService.findPageBySpec(customerParam);
    }

}



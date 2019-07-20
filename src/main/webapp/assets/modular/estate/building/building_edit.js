/**
 * 详情对话框
 */
var BuildingInfoDlg = {
    data: {
        cityId: "",
        pinyinInitials: "",
        propertyUse: "",
        propertyType: "",
        buildingTime: "",
        profileAddress: "",
        detailedAddress: "",
        saleAveragePrice: "",
        manageFee: "",
        note: "",
        createTime: "",
        updateTime: "",
        provid:"",
        citysid:"",
        areaid:"",
        providName:"",
        citysidName:"",
        areaidName:""
    }
};

layui.use(['form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    // 渲染时间选择框
    laydate.render({
        elem: '#buildingTime'
        ,type: 'year'
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#createTime'
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime'
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/building/detail?buildingId=" + Feng.getUrlParam("buildingId"));
    var result = ajax.start();
    var strs=result.data.profileAddress.split("-");
    for(var str in threeSelectData){
        if(str==strs[0]){
            result.data.provid=threeSelectData[str].val;
            for(var city in threeSelectData[str].items){
                if(city==strs[1]){
                    result.data.citysid=threeSelectData[str].items[city].val
                    for(var area in threeSelectData[str].items[city].items){
                        if(area==strs[2]){
                            result.data.areaid=threeSelectData[str].items[city].items[area];
                        }
                    }
                }
            }
        }
    }
    /**
     * 省市区三级联动
     * @type {{s1: string, s2: string, s3: string, v1: null, v2: null, v3: null}}
     */
    var defaults = {
        s1: 'provid',
        s2: 'cityid',
        s3: 'areaid',
        v1: result.data.provid,
        v2: result.data.citysid,
        v3: result.data.areaid
    };
    treeSelect(defaults);
    console.log(result.data);
    form.val('buildingForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/building/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});
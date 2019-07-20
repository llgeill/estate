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
        areaid:""
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
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/building/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});
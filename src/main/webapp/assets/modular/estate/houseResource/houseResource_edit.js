/**
 * 详情对话框
 */
var HouseResourceInfoDlg = {
    data: {
        buildingBlockId: "",
        buildingId:"",
        roomNumber: "",
        floor: "",
        roomTotal: "",
        hallTotal: "",
        toiletTotal: "",
        balconyTotal: "",
        purpose: "",
        houseResourceType: "",
        area: "",
        practicalArea: "",
        orientation: "",
        houseType: "",
        buildingTime: "",
        transaction: "",
        state: "",
        price: "",
        rental: "",
        priceFloor: "",
        rentalFloor: "",
        taxes: "",
        resource: "",
        statusQuo: "",
        decorate: "",
        matchState: "",
        furnitureState: "",
        householdElectricalState: "",
        propertyRight: "",
        certificates: "",
        payment: "",
        payCommission: "",
        houseInspection: "",
        keyNumber: "",
        entrust: "",
        staff: "",
        staffId: "",
        ownerName: "",
        ownerPhone: "",
        contactsContent: "",
        manageExpense: "",
        remark: "",
        nationality: "",
        belongToId: "",
        createTime: "",
        updateTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/houseResource/detail?houseResourceId=" + Feng.getUrlParam("houseResourceId"));
    var result = ajax.start();
    form.val('houseResourceForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/houseResource/editItem", function (data) {
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
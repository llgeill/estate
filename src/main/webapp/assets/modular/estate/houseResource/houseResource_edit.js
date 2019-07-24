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

layui.use(['form', 'admin','laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    // 渲染时间选择框
    laydate.render({
        elem: '#createTime',
        min: 0,
        value: new Date()
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime',min: 0
    });

    //获取楼盘列表
    function getbulidingBlockList(vals){
        //获取下拉框选值
        var buildingId={
            buildingId:vals
        };
        var ajax = new $ax(Feng.ctxPath + "/buildingBlock/bulidingBlockList",function (data){
            if(data==null||data.length==0){
                $("#buildingBlockId").empty();

            }else{
                var content="";
                for(var i=0;i<data.length;i++){
                    if(i==0){
                        content+="<option value='"+data[i].buildingBlockId+"' selected=\"\">"+data[i].name+"</option>"
                    }else{
                        content+="<option value='"+data[i].buildingBlockId+"'>"+data[i].name+"</option>"
                    }
                }
                $("#buildingBlockId").append(content);
            }
        });
        ajax.set(buildingId);
        ajax.start();
        form.render();
    }
    //获取城区信息并且初始化城区数据
    function getbuilding(vals){
        //获取下拉框选值
        var buildingId={
            buildingId:vals
        };
        var ajax = new $ax(Feng.ctxPath + "/building/detail",function (data){
            var obj=data.data;
            $("#buildingTime").val(obj.buildingTime);
            $("#purpose").find("option[value='"+obj.purpose+"']").attr("selected", true);
            $("#houseType").find("option[value='"+obj.houseType+"']").attr("selected", true);

        });
        ajax.set(buildingId);
        ajax.start();
        form.render();
    }

    //渲染城区
    var ajax = new $ax(Feng.ctxPath + "/building/listData",function (data) {
        var content="";
        for(var i=0;i<data.length;i++){
            if(i==0){
                //初始化调用操作
                getbulidingBlockList(data[i].buildingId);
                getbuilding(data[i].buildingId);

                content+="<option value='"+data[i].buildingId+"' selected=\"\">"+data[i].cityId+"</option>"
            }else{
                content+="<option value='"+data[i].buildingId+"'>"+data[i].cityId+"</option>"
            }
        }
        $("#buildingId").append(content);
    });
    ajax.start();
    form.render();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/houseResource/detail?houseResourceId=" + Feng.getUrlParam("houseResourceId"));
    var result = ajax.start();
    console.log(result.data);
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
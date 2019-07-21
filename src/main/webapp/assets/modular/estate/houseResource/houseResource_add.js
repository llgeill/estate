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
        elem: '#createTime',min: 0
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime',min: 0

    });

    //渲染城区
    var buildingIdHead="";
    var ajax = new $ax(Feng.ctxPath + "/building/listData",function (data) {
        var content="";
        for(var i=0;i<data.length;i++){
            if(i==0){
                 getbulidingBlockList(data[i].buildingId);
                content+="<option value='"+data[i].buildingId+"' selected=\"\">"+data[i].cityId+"</option>"
            }else{
                content+="<option value='"+data[i].buildingId+"'>"+data[i].cityId+"</option>"
            }
        }
        $("#buildingId").append(content);


    });
    ajax.start();
    form.render();

    //选中城区后
    form.on('select(buildingId)', function(data){
        getbulidingBlockList(data.value);
    });

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

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/houseResource/addItem", function (data) {
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
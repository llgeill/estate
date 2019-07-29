//备注
var remarkData="";
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
        type: 'datetime',
        min: 0
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime',min: 0
    });
    //智能添加楼层
    $("#roomNumber").on("input",function(e){
        //获取input输入的值

        var val=e.delegateTarget.value;
        if(val!=null){
            console.log(val.length)
            if(val.length>=4){
                console.log(val.substring(0,2));
                $("#floor").val(parseInt(val.substring(0,2)));
            }else{
                $("#floor").val(parseInt(val.substring(0,1)));
            }

        }else{
            $("#floor").val();
        }
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
    //选中交易后
    form.on('select(transaction)', function (data) {
        transactionInfo(data.value);
    });
    //选中交易后的数据处理
    function transactionInfo(data){
        if(data!=null&&data=="出租"){
            $("#price").attr("disabled","disabled");
            $("#price").addClass("layui-disabled");
            $("#price").css({"background-color": "#d6d4d4"});
            $("#priceFloor").attr("disabled","disabled");
            $("#priceFloor").addClass("layui-disabled");
            $("#priceFloor").css({"background-color": "#d6d4d4"});
            $("#rental").removeAttr("disabled");
            $("#rental").removeClass("layui-disabled");
            $("#rental").removeAttr("style","");
            $("#rentalFloor").removeAttr("disabled");
            $("#rentalFloor").removeClass("layui-disabled");
            $("#rentalFloor").removeAttr("style","");
        }else if(data!=null&&data=="出售"){
            $("#rental").attr("disabled","disabled");
            $("#rental").addClass("layui-disabled");
            $("#rental").css({"background-color": "#d6d4d4"});
            $("#rentalFloor").attr("disabled","disabled");
            $("#rentalFloor").addClass("layui-disabled");
            $("#rentalFloor").css({"background-color": "#d6d4d4"});
            $("#price").removeAttr("disabled");
            $("#price").removeClass("layui-disabled");
            $("#price").removeAttr("style","");
            $("#priceFloor").removeAttr("disabled");
            $("#priceFloor").removeClass("layui-disabled");
            $("#priceFloor").removeAttr("style","");
        }else if(data!=null&&data=="租售"){
            $("#rental").removeAttr("disabled");
            $("#rental").removeClass("layui-disabled");
            $("#rental").removeAttr("style","");
            $("#rentalFloor").removeAttr("disabled");
            $("#rentalFloor").removeClass("layui-disabled");
            $("#rentalFloor").removeAttr("style","");
            $("#price").removeAttr("disabled");
            $("#price").removeClass("layui-disabled");
            $("#price").removeAttr("style","");
            $("#priceFloor").removeAttr("disabled");
            $("#priceFloor").removeClass("layui-disabled");
            $("#priceFloor").removeAttr("style","");
        }
        form.render();
    }
    //添加备注信息
    form.on('select(infoAll)', function(data){
         remarkData+=data.value+"  ";
         $("#remark").val(remarkData);
    });
    //选中钥匙
    form.on('select(houseInspection)', function(data){
        if(data.value!=null&&data.value=='有匙'){
            $('#keyNumber').removeAttr("disabled");
            $("#keyNumber").removeClass("layui-disabled");
            $("#keyNumber").removeAttr("style","");
        }else{
            $("#keyNumber").attr("disabled","disabled");
            $("#keyNumber").addClass("layui-disabled");
            $("#keyNumber").css({"background-color": "#d6d4d4"});
        }
    });

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
    remarkData= $("#remark").val();
    transactionInfo(result.data.transaction);



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
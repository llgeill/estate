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
        staffId: "",
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

/**
 * 初始化调用区域
 */



layui.use(['table','form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var table = layui.table;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //初始化数据操作
    initData();

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    // 渲染时间选择框
    laydate.render({
        elem: '#createTime',
        min: 0,
        type: 'datetime',
        value: new Date()
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime',min: 0

    });

    //获取当前用户数据并且初始化数据
    function getUserInfo(){
        var ajax = new $ax(Feng.ctxPath + "/system/user_info_data",function (data){
            console.log(data);
            $("#staff").val(data.name);
            $("#staffId").val(data.userId);
        });
        ajax.start();
        form.render();
    }

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

    //选中交易后
    form.on('select(transaction)', function(data){
        console.log(data.value);
        if(data.value!=null&&data.value=="出租"){
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
        }else if(data.value!=null&&data.value=="出售"){
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
        }else if(data.value!=null&&data.value=="租售"){
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
    });
    //选中城区后
    form.on('select(buildingId)', function(data){
        getbulidingBlockList(data.value);
        //赋予部分初始化值
        getbuilding(data.value);
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

    form.on('select(infoAll)', function(data){
        remarkData+=data.value+"  ";
        $("#remark").val(remarkData);
    });


    //初始化数据
    function initData(){
        getUserInfo();
    }
    //自定义验证规则
    form.verify({
        roomNumber: function(value){
            console.log(value);
            if(value<0){
                return '当前值不允许为负数';
            }

        }
    });
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




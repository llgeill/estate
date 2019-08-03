/**
 * 详情对话框
 */
var CustomerInfoDlg = {
    data: {
        name: "",
        phone: "",
        rental: "",
        price: "",
        priceStart:"",
        rentalStart: "",
        rentalEnd: "",
        priceTax: "",
        houseType: "",
        direction: "",
        buildingBlock: "",
        floor: "",
        roomNumber: "",
        remark: "",
        customerType: "",
        updateTime:"",
        createTime:""
    }
};

layui.use(['form', 'admin', 'ax','laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate=layui.laydate;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    // 渲染选择框
    laydate.render({
        elem: '#rentalStart',
        min: 0,
        type: 'datetime'
    });
    // 渲染时间选择框
    laydate.render({
        elem: '#rentalEnd',
        min: 0,
        type: 'datetime'

    });
    // 渲染时间选择框
    laydate.render({
        elem: '#priceStart',
        min: 0,
        type: 'datetime'
    });

    //智能添加楼层
    $("#roomNumber").on("input",function(e){
        //获取input输入的值
        var val=e.delegateTarget.value;
        if(val!=null){
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

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/customer/addItem", function (data) {
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
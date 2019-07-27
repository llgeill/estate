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



/**
 * 三級聯動
 * @type {{s1: string, s2: string, s3: string, v1: null, v2: null, v3: null}}
 */
var defaults = {
    s1: 'provid',
    s2: 'citysid',
    s3: 'areaid',
    v1: null,
    v2: null,
    v3: null
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
        ,type: 'year',max: 0
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#createTime',min: 0
    });

    form.render();

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime',min: 0

    });
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        for(var str in threeSelectData){
            if(threeSelectData[str].val==data.field.provid){
                data.field.providName=str;
                for(var city in threeSelectData[str].items){
                    if(threeSelectData[str].items[city].val==data.field.citysid){
                        data.field.citysidName=city;
                        for(var area in threeSelectData[str].items[city].items){
                            if(threeSelectData[str].items[city].items[area]==data.field.areaid){
                                data.field.areaidName=area;
                            }
                        }
                    }
                }
            }
        }
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
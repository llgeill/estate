/**
 * 折叠
 * @param data
 */

layui.use(['table','form', 'admin', 'ax','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var form =layui.form;

    /**
     * 房源信息管理
     */
    var HouseResource = {
        tableId: "houseResourceTable"
    };

    /**
     * 初始化表格的列
     */
    HouseResource.initColumn = function () {
        return [[
            {type: 'checkbox'},
            // {field: 'houseResourceId', hide: true, title: '主键id'},
            {field: 'buildingName', sort: true, title: '城区'},
            {field: 'buildingBlockName', sort: true, title: '栋座'},
            {field: 'roomNumber', sort: true, title: '房号'},
            {field: 'floor', sort: true, title: '楼层'},
            {field: 'roomTotal', sort: true, title: '房'},
            {field: 'hallTotal', sort: true, title: '厅'},
            {field: 'toiletTotal', sort: true, title: '卫'},
            {field: 'balconyTotal' , sort: true, title: '阳台'},
            // {field: 'purpose',hide: true, sort: true, title: '用途'},
            // {field: 'houseResourceType',hide: true, sort: true, title: '房源类型'},
            {field: 'area', sort: true, title: '面积'},
            // {field: 'practicalArea',hide: true, sort: true, title: '实用面积'},
            // {field: 'orientation', sort: true, title: '朝向'},
            // {field: 'houseType', hide: true,sort: true, title: '房屋类型'},
            // {field: 'buildingTime',hide: true, sort: true, title: '建房年代'},
            // {field: 'transaction',hide: true, sort: true, title: '交易'},
            // {field: 'state',hide: true, sort: true, title: '状态'},
            // {field: 'price', hide: true,sort: true, title: '售价'},
            // {field: 'rental',hide: true, sort: true, title: '租价'},
            // {field: 'priceFloor', hide: true,sort: true, title: '出售底价'},
            // {field: 'rentalFloor', hide: true,sort: true, title: '租价底价'},
            // {field: 'taxes', hide: true,sort: true, title: '包税费'},
            // {field: 'resource',hide: true, sort: true, title: '来源'},
            // {field: 'statusQuo',hide: true, sort: true, title: '现状'},
            // {field: 'decorate',hide: true, sort: true, title: '装修情况'},
            // {field: 'matchState',hide: true, sort: true, title: '配套情况'},
            // {field: 'furnitureState',hide: true, sort: true, title: '家具情况'},
            // {field: 'householdElectricalState',hide: true, sort: true, title: '家电情况'},
            // {field: 'propertyRight', hide: true,sort: true, title: '产权'},
            // {field: 'certificates',hide: true, sort: true, title: '证件'},
            // {field: 'payment',hide: true, sort: true, title: '付款'},
            // {field: 'payCommission',hide: true, sort: true, title: '付佣'},
            // {field: 'houseInspection',hide: true, sort: true, title: '看房'},
            // {field: 'keyNumber',hide: true, sort: true, title: '钥匙号'},
            // {field: 'entrust',hide: true, sort: true, title: '委托方式'},
            // {field: 'staff_id', hide: true, sort: true, title: '员工id'},
            {field: 'staff', sort: true, title: '员工'},
            // {field: 'ownerName', hide: true,sort: true, title: '业主姓名'},
            // {field: 'ownerPhone',hide: true, sort: true, title: '业主手机号'},
            // {field: 'contactsContent',hide: true, sort: true, title: '联系人内容'},
            // {field: 'manageExpense',hide: true, sort: true, title: '管理费'},
            // {field: 'remark',hide: true, sort: true, title: '备注'},
            // {field: 'nationality',hide: true, sort: true, title: '国籍'},
           // {field: 'belongToId', sort: true, title: '属主用户id'},
            {field: 'createTime', sort: true, title: '委托时间'},
            //{field: 'updateTime', hide: true,sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    //日期时间范围
    laydate.render({
        elem: '#entrustBetweenTime'
        ,range: true
        ,change: function(value, date, endDate){
           getAllSearchValue(value);
        }
    });


    /**
     * 点击查询按钮
     */
    HouseResource.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        console.log($("#condition").val());
        table.reload(HouseResource.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    HouseResource.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['700px', '450px'],
            title: '添加房源信息',
            content: Feng.ctxPath + '/houseResource/add',
            end: function () {
                // admin.getTempData('formOk') &&
                table.reload(HouseResource.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    HouseResource.exportExcel = function () {
        var checkRows = table.checkStatus(HouseResource.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改房源信息',
            content: Feng.ctxPath + '/houseResource/edit?houseResourceId=' + data.houseResourceId,
            end: function () {
                admin.getTempData('formOk') && table.reload(HouseResource.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/houseResource/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(HouseResource.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("houseResourceId", data.houseResourceId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + HouseResource.tableId,
        url: Feng.ctxPath + '/houseResource/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        height: 472,
        cols: HouseResource.initColumn(),
        // toolbar:true,
        // defaultToolbar:['filter']
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        HouseResource.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        HouseResource.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        HouseResource.exportExcel();
    });
    // 展开与折叠
    $('#foldAll').click(function () {
        if($("#showAndHideDiv").attr("shval")==1){
            $("#showAndHideDiv").hide();
            $("#foldAll").html('<i class="layui-icon"></i>展开</button>');
            $("#showAndHideDiv").attr("shval",2);
        }else{
            $("#showAndHideDiv").show();
            $("#foldAll").html('<i class="layui-icon"></i>折叠</button>');
            $("#showAndHideDiv").attr("shval",1);
        }
        return false;
    });

    // 工具条点击事件
    table.on('tool(' + HouseResource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            HouseResource.openEditDlg(data);
        } else if (layEvent === 'delete') {
            HouseResource.onDeleteItem(data);
        }
    });

    //下拉事件
    form.on('select()', function(data){
        getAllSearchValue();
        form.render();
    });

    //选中交易后
    form.on('radio()', function(data){
        getAllSearchValue();
        form.render();
    });

    function getAllSearchValue(date) {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['roomTotal'] = $("#roomTotal").val();
        queryData['transaction'] = $("#transaction").val();
        queryData['state'] = $("#state").val();
        queryData['rental'] = $("input[name='rental']:checked").val();
        queryData['price'] = $("input[name='price']:checked").val();
        queryData['area'] = $("input[name='area']:checked").val();
        queryData['houseType'] = $("#houseType").val();
        queryData['orientation'] = $("#orientation").val();
        if(date!=null){
            queryData['entrustBetweenTime'] = date;
        }else{
            queryData['entrustBetweenTime'] = $("#entrustBetweenTime").val();
        }
        queryData['purpose'] = $("#purpose").val();
        queryData['hallToilet'] = $("#hallToilet").val();
        queryData['hallToiletTotal'] = $("#hallToiletTotal").val();
        queryData['houseResourceType'] = $("#houseResourceType").val();
        table.reload(HouseResource.tableId, {where: queryData});
    }

    function setAllSearchValueNull() {
            $("#roomTotal").val("");
            $("#transaction").val("");
            $("#state").val("");
            $("input[name='rental']:checked").val("");
            $("input[name='price']:checked").val("");
            $("input[name='area']:checked").val("");
            $("#houseType").val("");
            $("#orientation").val("");
            $("#entrustBetweenTime").val("");
            $("#purpose").val("");
            $("#hallToilet").val("");
            $("#hallToiletTotal").val("");
            $("#houseResourceType").val("");
    }

    //监听行单击事件（单击事件为：rowDouble）
    table.on('row(houseResourceTable)', function(obj){
        //詳細信息
        var data = obj.data;
        //跟进信息
        var ajax = new $ax(Feng.ctxPath + "/followInfo/followInfoList", function (data) {
            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("houseResourceId", data.houseResourceId);
        ajax.start();
        //多媒体信息
        var ajaxX = new $ax(Feng.ctxPath + "/view/viewList", function (data) {
            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajaxX.set("resourceId", data.houseResourceId);
        ajaxX.start();
        //

        //
        // layer.alert(JSON.stringify(data), {
        //     title: '当前行数据：'
        // });

        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });
});


layui.use(['element', 'layer'], function(){
    var element = layui.element
        ,layer = layui.layer;

    element.on('tab(docDemoTabBrief)', function(data){
        layer.msg('切到到了'+ data.index + '：' + this.innerHTML);
    });
});
layui.use('element', function(){
    var element = layui.element;
});
layui.use('laydate', function(){
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#dateRange',
        range: true
    });
    laydate.render({
        elem: '#test6'
        ,range: true
    });
});
layui.use('carousel', function() {
    var carousel = layui.carousel;
    var ins = carousel.render({
        elem: '#test1',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });
    //重置轮播
    ins.reload({
        elem: '#test1',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });

    var ins2 = carousel.render({
        elem: '#test2',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });
    ins2.reload({
        elem: '#test2',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });

});
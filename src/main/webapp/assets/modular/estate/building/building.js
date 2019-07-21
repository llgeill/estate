layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 城区管理
     */
    var Building = {
        tableId: "buildingTable"
    };

    /**
     * 初始化表格的列
     */
    Building.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'buildingId', hide: false, title: '主键id'},
            {field: 'cityId', sort: true, title: '城区'},
            {field: 'pinyinInitials', hide: false, sort: true, title: '拼音缩写'},
            {field: 'propertyUse', sort: true, title: '物业用途'},
            {field: 'propertyType', sort: true, title: '物业类型'},
            {field: 'buildingTime', sort: true, title: '建房年代'},
            {field: 'profileAddress', sort: true, title: '概要地址'},
            {field: 'detailedAddress', sort: true, title: '详细地址'},
            {field: 'saleAveragePrice', sort: true, title: '售均价'},
            {field: 'manageFee', sort: true, title: '管理费'},
            {field: 'note', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Building.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Building.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Building.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加城区',
            content: Feng.ctxPath + '/building/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Building.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Building.exportExcel = function () {
        var checkRows = table.checkStatus(Building.tableId);
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
    Building.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改城区',
            content: Feng.ctxPath + '/building/edit?buildingId=' + data.buildingId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Building.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Building.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/building/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Building.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("buildingId", data.buildingId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Building.tableId,
        url: Feng.ctxPath + '/building/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Building.initColumn(),
        toolbar:true,
        defaultToolbar:['filter']
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Building.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Building.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Building.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Building.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Building.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Building.onDeleteItem(data);
        }
    });

});

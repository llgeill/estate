layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 楼盘管理
     */
    var BuildingBlock = {
        tableId: "buildingBlockTable"
    };

    /**
     * 初始化表格的列
     */
    BuildingBlock.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'buildingBlockId', hide: true, title: '主键id'},
            {field: 'buildingId', sort: true, title: '楼盘id'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'layerTotal', sort: true, title: '总层数'},
            {field: 'areaTotal', sort: true, title: '总面积'},
            {field: 'elevatorTotal', sort: true, title: '总电梯数'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    BuildingBlock.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(BuildingBlock.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    BuildingBlock.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加楼盘',
            content: Feng.ctxPath + '/buildingBlock/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(BuildingBlock.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    BuildingBlock.exportExcel = function () {
        var checkRows = table.checkStatus(BuildingBlock.tableId);
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
    BuildingBlock.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改楼盘',
            content: Feng.ctxPath + '/buildingBlock/edit?buildingBlockId=' + data.buildingBlockId,
            end: function () {
                admin.getTempData('formOk') && table.reload(BuildingBlock.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    BuildingBlock.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/buildingBlock/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(BuildingBlock.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("buildingBlockId", data.buildingBlockId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + BuildingBlock.tableId,
        url: Feng.ctxPath + '/buildingBlock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: BuildingBlock.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        BuildingBlock.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        BuildingBlock.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        BuildingBlock.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + BuildingBlock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            BuildingBlock.openEditDlg(data);
        } else if (layEvent === 'delete') {
            BuildingBlock.onDeleteItem(data);
        }
    });
});

layui.use(['table', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;

    /**
     * 视图管理
     */
    var View = {
        tableId: "viewTable"
    };

    /**
     * 初始化表格的列
     */
    View.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'viewId', hide: true, title: '主键id'},
            {field: 'resourceId', sort: true, title: '来源id'},
            {field: 'viewPath', sort: true, title: '视图路径'},
            {field: 'viewType', sort: true, title: '视图类型'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    /**
     * 点击查询按钮
     */
    View.search = function () {
        var queryData = {};
        queryData['viewId'] = $("#condition").val();
        table.reload(View.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    View.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加视图',
            content: Feng.ctxPath + '/view/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(View.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    View.exportExcel = function () {
        var checkRows = table.checkStatus(View.tableId);
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
    View.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改视图',
            content: Feng.ctxPath + '/view/edit?viewId=' + data.viewId,
            end: function () {
                admin.getTempData('formOk') && table.reload(View.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    View.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/view/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(View.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("viewId", data.viewId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + View.tableId,
        url: Feng.ctxPath + '/view/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: View.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        View.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        View.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        View.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + View.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            View.openEditDlg(data);
        } else if (layEvent === 'delete') {
            View.onDeleteItem(data);
        }
    });
});

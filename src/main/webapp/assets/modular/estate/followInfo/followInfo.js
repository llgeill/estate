layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 跟进信息管理
     */
    var FollowInfo = {
        tableId: "followInfoTable"
    };

    /**
     * 初始化表格的列
     */
    FollowInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            // {field: 'houseResourceId', sort: true, title: '房源id'},
            {field: 'staffName', sort: true, title: '员工姓名'},
            // {field: 'deptId', sort: true, title: '部门id'},
            {field: 'pName', sort: true, title: '部门'},
            {field: 'content', sort: true, title: '内容'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    FollowInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(FollowInfo.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    FollowInfo.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加跟进信息',
            content: Feng.ctxPath + '/followInfo/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(FollowInfo.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    FollowInfo.exportExcel = function () {
        var checkRows = table.checkStatus(FollowInfo.tableId);
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
    FollowInfo.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改跟进信息',
            content: Feng.ctxPath + '/followInfo/edit?followInfoId=' + data.followInfoId,
            end: function () {
                admin.getTempData('formOk') && table.reload(FollowInfo.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    FollowInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/followInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(FollowInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("followInfoId", data.followInfoId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + FollowInfo.tableId,
        url: Feng.ctxPath + '/followInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: FollowInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        FollowInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        FollowInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        FollowInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + FollowInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            FollowInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            FollowInfo.onDeleteItem(data);
        }
    });
});

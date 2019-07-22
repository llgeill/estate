layui.use(['table','upload', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var upload = layui.upload;

    //多图片上传
    upload.render({
        elem: '#test2'
        ,url: '/view/upload/'
        ,multiple: true
        ,before: function(obj){
            // //预读本地文件示例，不支持ie8
            // obj.preview(function(index, file, result){
            //     $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            // });
        }
        ,done: function(res){
            //上传完毕
        }
    });

    /**
     * 城市管理
     */
    var City = {
        tableId: "cityTable"
    };

    /**
     * 初始化表格的列
     */
    City.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'cityId', hide: true, title: '主键id'},
            {field: 'pid', sort: true, title: '父城区id'},
            {field: 'name', sort: true, title: ' 名称'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    City.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(City.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    City.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加城市',
            content: Feng.ctxPath + '/city/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(City.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    City.exportExcel = function () {
        var checkRows = table.checkStatus(City.tableId);
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
    City.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改城市',
            content: Feng.ctxPath + '/city/edit?cityId=' + data.cityId,
            end: function () {
                admin.getTempData('formOk') && table.reload(City.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    City.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/city/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(City.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("cityId", data.cityId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + City.tableId,
        url: Feng.ctxPath + '/city/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: City.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        City.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        City.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        City.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + City.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            City.openEditDlg(data);
        } else if (layEvent === 'delete') {
            City.onDeleteItem(data);
        }
    });
});

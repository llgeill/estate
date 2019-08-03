layui.use(['table', 'admin', 'ax','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var form=layui.form;

    /**
     * 客户信息管理
     */
    var Customer = {
        tableId: "customerTable"
    };

    /**
     * 初始化表格的列
     */
    Customer.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'customerId', hide: true, title: '主键ID'},
            {field: 'name', sort: true, title: '姓名',width:80},
            {field: 'phone', sort: true, title: '手机号',width:120},
            {field: 'customerType', sort: true, title: '客户类型',width:100},
            {field: 'rental', sort: true, title: '租价',width:80},
            {field: 'rentalStart', sort: true, title: '起始时间',width:120},
            {field: 'rentalEnd', sort: true, title: '结束时间',width:120},
            {field: 'price', sort: true, title: '售价',width:80},
            {field: 'priceTax', sort: true, title: '售价税费',width:100},
            {field: 'priceStart', sort: true, title: '售房时间',width:120},
            {field: 'houseType', sort: true, title: '户型',width:80},
            {field: 'direction', sort: true, title: '方向',width:65},
            {field: 'buildingBlock', sort: true, title: '栋座名称',width:100},
            {field: 'floor', sort: true, title: '楼层',width:65},
            {field: 'roomNumber', sort: true, title: '房号',width:65},
            {field: 'remark', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作',width:120}
        ]];
    };

    //下拉事件
    form.on('select()', function(){
        getAllSearchValue();
        form.render();
    });

    function getAllSearchValue() {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['customerType'] = $("#customer_type").val();
        table.reload(Customer.tableId, {where: queryData});
    }

    /**
     * 点击查询按钮
     */
    Customer.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Customer.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Customer.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加客户信息',
            content: Feng.ctxPath + '/customer/add',
            area: ['850px', '900px'],
            end: function () {
                admin.getTempData('formOk') && table.reload(Customer.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Customer.exportExcel = function () {
        var checkRows = table.checkStatus(Customer.tableId);
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
    Customer.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改客户信息',
            area: ['850px', '900px'],
            content: Feng.ctxPath + '/customer/edit?customerId=' + data.customerId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Customer.tableId);
            }
        });
    };

    /**
     * 点击查看
     *
     * @param data 点击按钮时候的行数据
     */
    Customer.openDetailDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改客户信息',
            area: ['850px', '900px'],
            content: Feng.ctxPath + '/customer/detailTemp?customerId=' + data.customerId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Customer.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Customer.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/customer/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Customer.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("customerId", data.customerId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Customer.tableId,
        url: Feng.ctxPath + '/customer/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Customer.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Customer.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Customer.openAddDlg();
    });



    // 导出excel
    $('#btnExp').click(function () {
        Customer.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Customer.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Customer.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Customer.onDeleteItem(data);
        } else if (layEvent === 'detail') {
            Customer.openDetailDlg(data);
        }
    });
});

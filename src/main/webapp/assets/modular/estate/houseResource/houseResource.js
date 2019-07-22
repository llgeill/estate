layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

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
            {field: 'houseResourceId', hide: true, title: '主键id'},
            {field: 'buildingId', sort: true, title: '城区id'},
            {field: 'buildingBlockId', sort: true, title: '栋座id'},
            {field: 'roomNumber', sort: true, title: '房号'},
            {field: 'floor', sort: true, title: '楼层'},
            {field: 'roomTotal', sort: true, title: '房'},
            {field: 'hallTotal', sort: true, title: '厅'},
            {field: 'toiletTotal', sort: true, title: '卫'},
            {field: 'balconyTotal', sort: true, title: '阳台'},
            {field: 'purpose', sort: true, title: '用途'},
            {field: 'houseResourceType', sort: true, title: '房源类型'},
            {field: 'area', sort: true, title: '面积'},
            {field: 'practicalArea', sort: true, title: '实用面积'},
            {field: 'orientation', sort: true, title: '朝向'},
            {field: 'houseType', sort: true, title: '房屋类型'},
            {field: 'buildingTime', sort: true, title: '建房年代'},
            {field: 'transaction', sort: true, title: '交易'},
            {field: 'state', sort: true, title: '状态'},
            {field: 'price', sort: true, title: '售价'},
            {field: 'rental', sort: true, title: '租价'},
            {field: 'priceFloor', sort: true, title: '出售底价'},
            {field: 'rentalFloor', sort: true, title: '租价底价'},
            {field: 'taxes', sort: true, title: '包税费'},
            {field: 'resource', sort: true, title: '来源'},
            {field: 'statusQuo', sort: true, title: '现状'},
            {field: 'decorate', sort: true, title: '装修情况'},
            {field: 'matchState', sort: true, title: '配套情况'},
            {field: 'furnitureState', sort: true, title: '家具情况'},
            {field: 'householdElectricalState', sort: true, title: '家电情况'},
            {field: 'propertyRight', sort: true, title: '产权'},
            {field: 'certificates', sort: true, title: '证件'},
            {field: 'payment', sort: true, title: '付款'},
            {field: 'payCommission', sort: true, title: '付佣'},
            {field: 'houseInspection', sort: true, title: '看房'},
            {field: 'keyNumber', sort: true, title: '钥匙号'},
            {field: 'entrust', sort: true, title: '委托方式'},
            {field: 'staff_id', sort: true, title: '员工id'},
            {field: 'staff', sort: true, title: '员工'},
            {field: 'ownerName', sort: true, title: '业主姓名'},
            {field: 'ownerPhone', sort: true, title: '业主手机号'},
            {field: 'contactsContent', sort: true, title: '联系人内容'},
            {field: 'manageExpense', sort: true, title: '管理费'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'nationality', sort: true, title: '国籍'},
            {field: 'belongToId', sort: true, title: '属主用户id'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    HouseResource.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(HouseResource.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    HouseResource.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加房源信息',
            content: Feng.ctxPath + '/houseResource/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(HouseResource.tableId);
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
        cols: HouseResource.initColumn()
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
});

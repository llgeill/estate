/**
 * 详情对话框
 */
var FollowInfoInfoDlg = {
    data: {
        houseResourceId: "",
        staffName: "",
        deptId: "",
        content: "",
        createTime: "",
        updateTime: "",
        pName: ""
    }
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
        elem: '#createTime',
        type: 'datetime',
        value: new Date()
        ,min: 0 //7天前
        ,max: 0 //7天后

    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime'
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/followInfo/detail?followInfoId=" + Feng.getUrlParam("followInfoId"));
    var result = ajax.start();
    form.val('followInfoForm', result.data);

    // 点击上级角色时
    $('#pName').click(function () {
        var formName = encodeURIComponent("parent.FollowInfoInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.FollowInfoInfoDlg.data.deptId");
        var treeUrl = encodeURIComponent("/dept/tree");

        layer.open({
            type: 2,
            title: '父级部门',
            area: ['300px', '200px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#deptId").val(FollowInfoInfoDlg.data.deptId);
                $("#pName").val(FollowInfoInfoDlg.data.pName);
            }
        });
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/followInfo/editItem", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});
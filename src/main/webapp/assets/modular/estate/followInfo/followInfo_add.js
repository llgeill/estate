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

    });

    // 渲染时间选择框
    laydate.render({
        elem: '#updateTime'
    });

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

    function followInfo(houseResourceId) {
        //跟进信息
        var ajax = new $ax(Feng.ctxPath + "/followInfo/followInfoList", function (data) {
            var content="";
            if(data!=null&&data.length>0){
                for(var i=0;i<data.length;i++){
                    var temp='<li class="layui-timeline-item">\n' +
                        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                        ' <div class="layui-timeline-content layui-text">\n' +
                        '<h3 class="layui-timeline-title">'+data[i].createTime+'&nbsp&nbsp'+data[i].staffName+'</h3>\n' +
                        '<p>'+data[i].content+'</p>' +
                        '</div>\n' +
                        '</li>\n' ;
                    content+=temp;
                }
                $("#followInfoDetail").html(content);
            }else {
                $("#followInfoDetail").html('');
            }

        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("houseResourceId",houseResourceId);
        ajax.start();
    }

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/followInfo/addItem", function (data) {
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
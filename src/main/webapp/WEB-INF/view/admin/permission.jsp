<%--
  Created by IntelliJ IDEA.
  User: terabithia
  Date: 1/3/18
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>权限管理</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .layui-body {
            background-color: #f3f3f4;
        }

        .body-content {
            padding: 15px;
            background-color: #fff;
            margin: 15px;
        }

        .body-content {
            height: auto;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="body-content">
            <div class="content">
                <div class="layui-fluid">
                    <div class="table-content">
                        <table class="layui-table">
                            <colgroup>
                                <col width="80">
                                <col width="150">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>商户帐号</th>
                                <th>商铺列表</th>
                                <th>权限</th>
                                <th>过期时间</th>
                                <th style="text-align: center">权限修改</th>
                                <th style="text-align: center">时间修改</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>01</td>
                                <td>18660706071</td>
                                <td>冰雪怪(外卖)、冰雪怪(团购)</td>
                                <td>外卖</td>
                                <td>2017-12-30 12:00:05</td>
                                <th style="text-align: center"><a href="javascript:void(0)" class="p_up">修改</a></th>
                                <th style="text-align: center"><a href="javascript:void(0)" class="t_up">修改</a></th>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <div id="page" style="text-align: right"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<%@include file="js.jsp" %>
<script>
    layui.use(['element', 'util', 'layer', 'laypage', 'form', 'laydate'], function () {
        var element = layui.element;
        var page = layui.laypage;
        var form = layui.form;
        var date = layui.laydate;

        page.render({
            elem: 'page'
            , count: 50
            , groups: 3
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    //不是第一页
                }
            }
        });

        //权限弹窗
        $(".p_up").on("click", function () {
            var contentStr = "<div>" +
                "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <label class=\"layui-form-label\">权限选择</label>\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <select name=\"interest\" lay-filter=\"aihao\">\n" +
                "        <option value=\"0\">外卖</option>\n" +
                "      </select>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <button class=\"layui-btn\" type='button' lay-submit lay-filter=\"perEnter\">确定</button>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</form>" +
                "</div>";
            var params = {
                title: ["权限修改", "background-color:#1E9FFF;color:#fff"],
                content: contentStr,
                fixed: true,
                area: {width: '380px', height: '200px'}
            };
            layer_pageTier(params);
            form.render();

        });

        //时间修改弹窗
        $(".t_up").on("click", function () {
            var contentStr = "<div>" +
                "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <label class=\"layui-form-label\">时间选择</label>\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <input type=\"text\" id='date' name=\"date\" required  lay-verify=\"required\" readonly placeholder=\"请输入选择日期\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <button class=\"layui-btn\" type='button' lay-submit lay-filter=\"dateEnter\">确定</button>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</form>" +
                "</div>";
            var params = {
                title: ["时间修改", "background-color:#1E9FFF;color:#fff"],
                content: contentStr,
                fixed: true,
                area: {width: '380px', height: '200px'}
            };
            layer_pageTier(params);
            form.render();
            date.render({
                elem: "#date",
                theme: '#20A0FF',
                type: 'datetime',
                min: -365,
                max: 0,
                trigger: 'click',
                ready: function () {
                },
                done: function (value) {
                }
            });
        });
    })
</script>
</body>
</html>

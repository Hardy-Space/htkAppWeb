<%--
  Created by IntelliJ IDEA.
  User: terabithia
  Date: 1/3/18
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title>订座订单管理页面</title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<style type="text/css">
* {
	font-family: "微软雅黑";
}
.useBtn{
margin: auto 10px;
}
.seatOrder{
	width: 95%;
	height: 100%;
	background-color: white;
	margin: auto;
	padding-top: 80px;
	text-align: center;
}
.body-content{
	height: 100%;
}
.orderContent{
	margin:auto 80px;	
}
.codition{
	text-align: left;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="body-content">
				<div class="seatOrder">
					<div class="codition">
						<label>条件筛选</label>
						<div class="conditionInput" style="display: inline-block">
							<input name="date useBtn" id="date" title="" value="" />
							<input type="button" value="添加座位" class="btn btn-info useBtn" id="addSeatInformation" >
							<input type="button" value="删除座位" class="btn btn-danger useBtn" id="delSeatInfo">
							<input type="button" value="调整座位" class="btn btn-info useBtn" id="changeSeatInfo">
						</div>
					</div>
					<div class="orderContent">
						<div class="tableContent">
							<table class="layui-table">
								<colgroup>
									<col>
									<col width="150">
								</colgroup>
								<thead>
									<tr>
										<th>序号</th>
										<th>订单号</th>
										<th>人数</th>
										<th>座位名</th>
										<th>预定人</th>
										<th>预定时间</th>
										<th>预定手机号</th>
										<th>备注</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${list != null}">
											<c:forEach items="${list }" var="each" varStatus="i">
												<tr>
													<td>${i.index }</td>
													<td>${each.orderNumber}</td>
													<td>${each.seatCount}</td>
													<td>${each.seatName }</td>
													<td>${each.scheduledName }</td>
													<td>${each.scheduledTime }</td>
													<td>${each.seatPhone }</td>
													<td>${each.remarks }</td>
													<td>${each.status==0?"未处理":"已处理" }</td>
													<td><input type="checkbox" class="doOrder"></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td>目前没有预定订单</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<div class="pages_">
							<div id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
<%@include file="js.jsp"%>
<script type="text/javascript">
	$(function() {
		$('input, textarea').placeholder();
	});
	layui.use([ 'element', 'util', 'layer', 'laypage' ], function() {
		var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
		var util = layui.util;
		var page = layui.laypage;

		page.render({
			elem : 'page',
			count : 100,
			groups : 3,
			curr : 1,
			prev : "<"
            , next: ">",
			theme : '#1E9FFF',
			first : false,
			last : false
		});
	});
</script>
</html>

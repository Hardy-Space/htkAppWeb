<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="IE_lang.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>座位信息管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<style>
* {
	font-family: "微软雅黑";
}

.szuo {
	font-size: 13px;
	text-align: left;
	height: 20px;
	padding-left: 0px;
}

.szhong {
	font-size: 13px;
	text-align: right;
	height: 20px;
	text-align: right;
}

.syou {
	font-size: 13px;
	height: 20px;
	text-align: right;
}

.xiaojie {
	font-size: 13px;
	float: right;
	text-align: right;
	margin-right: 0px;
	padding-right: 0px;
}

.tog {
	width: 100%;
	padding: 0px;
}

.xiangqing {
	margin-top: 0px;
}

.row {
	margin-top: 20px;
	background-color: white;
	height: 100%;
	background-color: white;
	width: 100%;
	float: right;
	margin-right: 1px;
	padding-left: 0px;
	padding-right: 0px;
	padding-bottom: 10px;
	border-bottom: 2px solid #ddd;
}

.bianhao {
	text-align: left;
	padding-right: 20px;
	font-size: 12px;
	height: 30px;
	margin-top: 12px;
	font-weight: 700;
	padding-left: 0px;
	line-height: 30px;
}

.shouqi {
	font-size: 10px;
	color: dodgerblue;
	float: right;
	margin-right: 13px;
	line-height: 40px;
	margin-top: 5px;
}

.modal-body span {
	text-align: center;
	margin-top: 10px;
	font-weight: 800;
}

.xuhao {
	background-color: lightgray;
	margin-top: 0px;
	height: 40px;
	line-height: 40px;
	padding-left: 0px;
}

.bt {
	width: 10%;
	height: 30px;
	background-color: white;
	border: 1px solid dodgerblue;
	border-radius: 5px;
	color: lightskyblue;
	margin-top: 12px;
}

.bt-primary {
	background-color: dodgerblue;
	color: white;
}

.nav {
	height: 40px;
	background-color: white;
	text-align: center;
	line-height: 40px;
}

.nav div a {
	text-decoration: none;
	out-line: none;
	color: # *****;
	font-size: 15px;
	font-weight: 700;
}

.active {
	border-bottom: 3px solid dodgerblue;
	height: 40px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="tabListDiv">
				<c:choose>
					<c:when test="${list!=null}">
						<!-- 有东西 -->
						<div>
							<table border="0">
								<tr>
									<td>座位编号</td>
									<td>是否有使用</td>
									<td>座位人数</td>
									<td>删除</td>
								</tr>
								<c:forEach items="${list}" var="each">
									<input type="hidden" value="${each.id }">
									<tr>
										<td>${each.seatName }</td>
										<td>${each.seatStatus==0?"空闲":"客满" }</td>
										<td>${each.numberSeat }</td>
										<td>删除</td>
									</tr>
								</c:forEach>
								<tr>
								<td colspan="4">
									<input id="addSeatInformation"type="hidden" value="${data.shopId }">
									<a class="addSeatInformation">目前没有座位，点击此处添加</a></td>
								</tr>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<table border="0">
								<tr>
									<td>座位编号</td>
									<td>是否有使用</td>
									<td>座位人数</td>
									<td>删除</td>
								</tr>
								<tr>
									<td colspan="4">
									<input id="addSeatInformation"type="hidden" value="${data.shopId }">
									<a class="addSeatInformation">目前没有座位，点击此处添加</a></td>
								</tr>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<%@include file="js.jsp"%>
	<!-- 模态框（Modal） -->
	<script type="text/javascript">
	$(function(){
		$(".addSeatInformation").on("click",function(){
			$("#myModal").modal('show')
			$("#myModalc").on("click",function(){
				submitData()
			})
		})
	})
function submitData(){
		debugger
	var shopId=$("#addSeatInformation").val();
	var seatName=$("#seatName").val();
	var numberSeat=$("#numberSeat").val();
	const url = baseUrl + "/merchant/buffetFood/addaddSeatInfo";
	var params={
			shopId:shopId,
			seatName:seatName,
			numberSeat:numberSeat
	}
	$.post(url,params,function(data){
		console.log(data)
		if(data==-1){
			alert(data.message);
		}else{
			alert(data.message);
		}
	})
	console.log(seatName+"==="+numberSeat+"==="+shopId);
}
</script>
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12">添加座位信息</b>
				</h3>
				<hr />
				<form action="" method="post">
					座位编号： <input type="text" id="seatName" value=""></br> 座位人数： <input
						type="text" id="numberSeat" value=""></br>
				</form>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="myModalc"
						type="button" class="bt bt-primary col-md-4">添加</button> <input
					style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span> </span>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        var page = layui.laypage;
        page.render({
            elem: 'page'
            , curr: ${page == null ? 1 : page.pageNum}
            , groups: 3
            , count: ${page == null ? 10: page.pages * 10}
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });
    });
</script>

</html>
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

.tabListDiv {
	width: 60%;
	height: 100%;
	background-color: white;
	margin: auto;
}

.mainTab {
	border-collapse: collapse;
	margin: auto;
	width: 60%;
	height: 50%;
	font-size: 20px;
	text-align: center;
}

.mainTab,.seatTab, .seatTab>td,.seatTab>th{
	border: 2px solid black;
}

.btnDiv {
	width: 60%;
	word-wrap: break-word;
	white-space: normal;
	margin: auto;
}

	.tog {
			width: 100%;
			padding: 0px;
		}
	.modal-body span {
			text-align: center;
			margin-top: 10px;
			font-weight: 700;
		}
		
		.affirmSettleBtn {
			height: 30px;
			float: right;
			line-height: 30px;
			width: 20%;
			margin-top: 11px;
		}

		.oBuffetFoodQuerySpanSelect {
			margin-top: 5px;
			margin-bottom: 0;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanInput {
			margin-top: 5px;
			margin-bottom: 5px;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanButton {
			margin-top: 5px;
			margin-bottom: 5px;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanInput > span > input {
			width: 100%;
			border-radius: 2px;
			border: 1px solid #e2e2e2;
			height: 30px;
			padding-left: 10px;
		}

		.oBuffetFoodQuerySpanSelect > span > select {
			width: 100%;
			border-radius: 2px;
			border: 1px solid #e2e2e2;
			height: 30px;
			padding-left: 10px;
		}






		.numberH {
			border-radius: 6px 6px 0 0;
		}

		.tableTableModeH {
			margin-bottom: 15px;
		}

		.tableTableModeH > b {
			padding-top: 15px;
			font-size: 15px;
		}

		.productDetailH > h5 {
			margin-bottom: 0;
			padding-left: 0;
		}

		.shouqi.ModelWindow {
			cursor: pointer;
		}

		.xiangqing.modelWindow {
			border-bottom: 1px solid gainsboro;
			border-top: 1px solid gainsboro;
			height: 40px;
			padding-bottom: 13px;
		}

		.bt.modelWindow {
			width: 15%;
			border-radius: 6px;
		}

		.cancel.modelWindow {
			float: left;
			margin-left: 30px;
			width: 15%;
			border-radius: 6px;
		}

		.szhong.modelWindow {
			text-align: right;
			float: right;
			font-size: 14px;
			margin-top: 0;
			line-height: 0;
		}

		.szuo.modelWindow {
			line-height: 0;
			margin-top: 0;
		}

		.tabDivFirstChild {
			padding-left: 10px;
			padding-right: 10px;
			background-color: #f3f3f4;
			padding-bottom: 15px;
		}

		.productDetailBody {
			padding-left: 0;
			/*padding-bottom: -10px;*/
			margin-top: 10px;
		}

		.productDetailBody > h5 {
			margin-bottom: 0;
		}

		.xiangqing.bodyContent {
			border-bottom: 1px solid gainsboro;
			border-top: 1px solid gainsboro;
			height: 45px;
			margin-top: 10px;
		}

		.szuo.bodyContent {
			font-weight: 700;
			font-size: 12px;
			margin-top: -7px;
		}

		.szhong.bodyContent {
			text-align: right;
			font-weight: 700;
			font-size: 13px;
			margin-top: -7px;
		}

		.syou.bodyContent {
			padding-right: 0;
			text-align: right;
			margin-right: 0;
			font-weight: 700;
			font-size: 13px;
			margin-top: -7px;
		}

		.xiangqing.bodyContent {
			padding-right: 0;
		}

		.tog.modelWindow {
			height: 70px;
			overflow: auto;
		}
	</style>
</style>
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
	font-weight: 700;
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
<style>

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
		}

		.modal-body span {
			text-align: center;
			margin-top: 10px;
			font-weight: 700;
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
			font-size: 15px;
			font-weight: 700;
		}

		.active {
			border-bottom: 3px solid dodgerblue;
			height: 40px;
		}


	</style>

	<style>
		.affirmSettleBtn {
			height: 30px;
			float: right;
			line-height: 30px;
			width: 20%;
			margin-top: 11px;
		}

		.oBuffetFoodQuerySpanSelect {
			margin-top: 5px;
			margin-bottom: 0;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanInput {
			margin-top: 5px;
			margin-bottom: 5px;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanButton {
			margin-top: 5px;
			margin-bottom: 5px;
			padding: 5px;
		}

		.oBuffetFoodQuerySpanInput > span > input {
			width: 100%;
			border-radius: 2px;
			border: 1px solid #e2e2e2;
			height: 30px;
			padding-left: 10px;
		}

		.oBuffetFoodQuerySpanSelect > span > select {
			width: 100%;
			border-radius: 2px;
			border: 1px solid #e2e2e2;
			height: 30px;
			padding-left: 10px;
		}

		.spanFontSize {
			font-size: 13px;
		}

		.affirmAddItemBtn {
			height: 20px;
			border-radius: 2px;
			border: 1px solid #1E9FFF;
			color: #fff;
			padding: 8px;
			line-height: 2px;
			background-color: #1E9FFF;
		}

		.labelSpanText {
			font-size: 15px;
			font-weight: 500;
			line-height: 30px;
			padding-right: 0;
			text-align: left;
		}

		.iconSpan {
			position: relative;
			left: -150px;
			color: #1E9FFF;
			cursor: pointer;
		}

		.tableNumberH {
			padding-left: 0;
			margin-bottom: 15px;
		}

		.tableNumberH > b {
			padding-top: 15px;
			font-size: 15px;
		}

		.pd-0 {
			padding-left: 0;
		}

		.productDetailH {
			padding-bottom: 0;
			margin-top: 17px;
			text-align: left;
		}

		.numberH {
			border-radius: 6px 6px 0 0;
		}

		.tableTableModeH {
			margin-bottom: 15px;
		}

		.tableTableModeH > b {
			padding-top: 15px;
			font-size: 15px;
		}

		.productDetailH > h5 {
			margin-bottom: 0;
			padding-left: 0;
		}

		.shouqi.ModelWindow {
			cursor: pointer;
		}

		.xiangqing.modelWindow {
			border-bottom: 1px solid gainsboro;
			border-top: 1px solid gainsboro;
			height: 40px;
			padding-bottom: 13px;
		}

		.bt.modelWindow {
			width: 15%;
			border-radius: 6px;
		}

		.cancel.modelWindow {
			float: left;
			margin-left: 30px;
			width: 15%;
			border-radius: 6px;
		}

		.szhong.modelWindow {
			text-align: right;
			float: right;
			font-size: 14px;
			margin-top: 0;
			line-height: 0;
		}

		.szuo.modelWindow {
			line-height: 0;
			margin-top: 0;
		}

		.tabDivFirstChild {
			padding-left: 10px;
			padding-right: 10px;
			background-color: #f3f3f4;
			padding-bottom: 15px;
		}

		.productDetailBody {
			padding-left: 0;
			/*padding-bottom: -10px;*/
			margin-top: 10px;
		}

		.productDetailBody > h5 {
			margin-bottom: 0;
		}

		.xiangqing.bodyContent {
			border-bottom: 1px solid gainsboro;
			border-top: 1px solid gainsboro;
			height: 45px;
			margin-top: 10px;
		}

		.szuo.bodyContent {
			font-weight: 700;
			font-size: 12px;
			margin-top: -7px;
		}

		.szhong.bodyContent {
			text-align: right;
			font-weight: 700;
			font-size: 13px;
			margin-top: -7px;
		}

		.syou.bodyContent {
			padding-right: 0;
			text-align: right;
			margin-right: 0;
			font-weight: 700;
			font-size: 13px;
			margin-top: -7px;
		}

		.xiangqing.bodyContent {
			padding-right: 0;
		}

		.tog.modelWindow {
			height: 70px;
			overflow: auto;
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
	<table class="mainTab">
								<tr class="seatTab">
									<th>座位编号</th>
									<th>座位人数</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${list}" var="each" varStatus="i">
									<input type="hidden" value="${each.id }">
									<tr class="seatTab">
										<td>${each.seatName }</td>
										<td>${each.numberSeat }</td>
										<td>
										<input type="button" value="删除" class="delSeatInfo btn btn-danger" id="delSeatInfo${i.index}" shopId="${data.shopId }">
										<input type="button" value="修改" class="btn btn-info changeSeatInfo" id="change${i.index}" shopId="${data.shopId }">
										</td>
									</tr>
								</c:forEach>
								<tr>

							</table>
	<div class="btnDiv">
								<c:forEach items="${list}" var="each">
									<c:if test="${each.seatStatus==1 }">
										<input type="button" value="${each.seatName}" class="seatInfo" id="changeStatus" status="0" shopId="${data.shopId }"
											style="background-color: red;width: 100px;height: 100px;">
									</c:if>
									<c:if test="${each.seatStatus==0 }">
										<input type="button" value="${each.seatName}" class="seatInfo" id="changeStatus" status="1" shopId="${data.shopId }"
											style="background-color:green;width: 100px;height: 100px;">
									</c:if>
								</c:forEach>
								<button type="button" class="seatInfo" id="addSeatInformation" value="${data.shopId }"
								style="width: 100px;height: 100px;">添加</button>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div>
								<table border="0" class="mainTab">
								<tr>
									<td>座位编号</td>
									<td>座位人数</td>
									<td>操作</td>
								</tr>
							
							</table>
<div class="btnDiv">
							<button type="button" class="seatInfo" id="addSeatInformation" value="${data.shopId }"
								style="width: 100px;height: 100px;">添加</button>
								</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<%@include file="js.jsp"%>
	
<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px;border-radius: 6px 6px 0 0;"
				class="xuhao col-md-12"><b class="col-md-12" id="changeMsg"></b></h3>
				<hr />
				<div class="tog">
				<form action="" method="post" class="xiangqing col-md-12">
					座位编号： <input type="text" id="seatName" value=""></br> 座位人数： <input
						type="text" id="numberSeat" value=""></br>
				</form>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="myModalc"
						type="button" class="bt bt-primary col-md-4">添加</button>
				 <input style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span> 
			</div>
		</div>
	</div>
	<div class="modal fade " id="message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px;border-radius: 6px 6px 0 0;"
				class="xuhao col-md-12" ><b class="col-md-12" id="opMessage"></b></h3>
				<hr />
				<div class="tog">
				<span class="xiangqing col-md-12" style="" id="delSeatInfoName"></span>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="delMessage"
						type="button" class="bt bt-primary col-md-4">确定</button>
				 <input style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span> 
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
	$("body").on("click","#changeStatus",function(){
			debugger
			changeStatus(this);
		})
		$("#addSeatInformation").on("click",function(){
			$("#myModal").modal('show')
			$("#changeMsg").html("添加座位信息");
			$("#myModalc").on("click",function(){
				submitData()
			})
	})
	$("body").on("click",".changeSeatInfo",function(){
		$("#myModal").modal('show')
			var seatName=$(this).parent().siblings("td:eq(0)").html();
			var shopId=$(this).attr("shopId");
			var numberSeat=$(this).parent().siblings("td:eq(1)").html();
		$("#changeMsg").html("修改座位信息");
		$("#myModalc").on("click",function(){
			var newName=$("#seatName").val();
			updataSeatInfo(seatName,shopId,numberSeat,newName)
		})
	})
$("body").on("click",".delSeatInfo",function(){
			$("#message").modal("show")
			var seatName=$(this).parent().siblings("td:eq(0)").html();
			var shopId=$(this).attr("shopId");
			var numberSeat=$(this).parent().siblings("td:eq(1)").html();
				$("#opMessage").html("删除操作");
				$("#delSeatInfoName").html("确定要删除‘"+seatName+"’么？");
				$("#delMessage").on("click",function(){
					delSeatInfo(seatName,shopId,numberSeat)
				})
		})
	})
	//修改座位状态
	function 	changeStatus(change){
		var seatName=$(change).val();
		var shopId=$(change).attr("shopId");
		var status=$(change).attr("status")
		const url = baseUrl + "/merchant/buffetFood/changeStatus";
		params={
				seatName:seatName,
				shopId:shopId,
				status:status
		}
		$.post(url,params,function(data){
			if(data.code==0){
				location.reload()
			}else{
				aler("修改失败")
			}
		})
	}
	
	//修改座位信息
	function updataSeatInfo(seatName,shopId,numberSeat,newName){
		const url = baseUrl + "/merchant/buffetFood/updata";
		params={
				shopId:shopId,
				seatName:newName,
				numberSeat:numberSeat,
				oldName:seatName
		}
		$.post(url,params,function(data){
			if(data.code==0){
				alert("修改成功")
				$("#message").modal("hide");
				location.reload()
			}else{
				alert("修改失败")
			}
		})
	}
	//删除座位
	function delSeatInfo(seatName,shopId,numberSeat){
		const url = baseUrl + "/merchant/buffetFood/delSeatInfo";
		params={
				shopId:shopId,
				seatName:seatName,
				numberSeat:numberSeat
		}
		$.post(url,params,function(data){
			if(data.code==0){
				alert("删除成功")
				$("#myModal").modal("hide");
				location.reload()
			}else{
				alert("请联系管理员")
			}
		})
	}
	//添加座位
function submitData(){
		
	var shopId=$("#addSeatInformation").val();
	var seatName=$("#seatName").val();
	var numberSeat=$("#numberSeat").val();
if(parseInt(numberSeat).isNaN||parseInt(numberSeat)>0){
const url = baseUrl + "/merchant/buffetFood/addSeatInfo";
	var params={
			shopId:shopId,
			seatName:seatName,
			numberSeat:numberSeat
	}
	$.post(url,params,function(data){
		
		if(data.code==0){
				$("#myModal").modal("hide");
				alert(data.message)
				location.reload()
			}else{
				alert(data.message)
			}
		})
	}else{
		alert("请输入座位人数")
	}
}
</script>
	
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
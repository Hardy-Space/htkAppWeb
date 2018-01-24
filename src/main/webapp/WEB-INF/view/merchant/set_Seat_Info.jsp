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

</head>
<body>

	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<table class="table">
							<thead>
								<tr>
									<th>编号</th>
									<th>产品</th>
									<th>交付时间</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>TB - Monthly</td>
									<td>01/04/2012</td>
									<td>Default</td>
								</tr>
								<tr class="success">
									<td>1</td>
									<td>TB - Monthly</td>
									<td>01/04/2012</td>
									<td>Approved</td>
								</tr>
								<tr class="error">
									<td>2</td>
									<td>TB - Monthly</td>
									<td>02/04/2012</td>
									<td>Declined</td>
								</tr>
								<tr class="warning">
									<td>3</td>
									<td>TB - Monthly</td>
									<td>03/04/2012</td>
									<td>Pending</td>
								</tr>
								<tr class="info">
									<td>4</td>
									<td>TB - Monthly</td>
									<td>04/04/2012</td>
									<td>Call in to confirm</td>
								</tr>
							</tbody>
						</table>
						<div class="row-fluid">
							<div class="span4">
								<div class="row-fluid">
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
									<button class="btn" type="button">按钮</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
									<td colspan="4"><input id="addSeatInformation"
										type="hidden" value="${data.shopId }"> <a
										class="addSeatInformation">目前没有座位，点击此处添加</a></td>
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
									<td colspan="4"><input id="addSeatInformation"
										type="hidden" value="${data.shopId }"> <a
										class="addSeatInformation">目前没有座位，点击此处添加</a></td>
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
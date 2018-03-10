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
.seatName{
  position: relative;
  right: 30px;
  bottom: 10px;
}
.orderAmount{
 position: relative;
 right: 30px;
  bottom: 10px;
}

.numberSeat{
 	position: relative;
 	left: 15px;
	top: 30px;
}

.orderTime{
 	position: relative;
	 right: 25px;
  	bottom: 10px;

}


.seatInfo{
	border:1px solid #000;
	width: 120px;
	height: 120px;
	margin:20px 15px;
	padding:20px;
	float: left;
	-moz-border-radius: 10px;    
    -webkit-border-radius: 10px;  
    border-radius:10px;   
}

.tabListDiv {
	width: 80%;
	height:100%;
	background-color: white;
	margin: auto;
	padding-top: 80px;
	text-align: center;
}

.tog {
	width: 100%;
	padding: 0px;
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

.tog.modelWindow {
	height: 70px;
	overflow: auto;
}
</style>
<style>
* {
	font-family: "微软雅黑";
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
						<c:forEach items="${list}" var="each" varStatus="i">
							<c:if test="${each.seatStatus==1 }">
							<div class="seatInfo" style="background-color:#cc5b67" 
							seatStatus="${each.seatStatus }"
							shopId="${each.shopId }">
								<span class="seatName">${each.seatName}</span><br/>
								<c:if test="${each.bfo!=null }">
								<span class="orderAmount">¥${each.bfo.orderAmount }</span><br/>
								</c:if>
								<c:when test="${each.bfo.orderTime!=null}">
								<span class="orderTime">${each.bfo.orderTime }</span>
								</c:when>
								<c:otherwise test="${each.useSeatTime!=null}">
								<span class="orderTime">${each.useSeatTime }</span>
								</c:otherwise>
								<span class="numberSeat">${each.numberSeat }</span><br/>
							</div>
							</c:if>
							<c:if test="${each.seatStatus==0 }">
							<div class="seatInfo" style="background-color:#b0b0b0"
							seatStatus="${each.seatStatus }"
							shopId="${each.shopId }">
								<span class="seatName">${each.seatName}</span><br/>
								<c:if test="${each.bfo!=null }">
								<span class="orderAmount">¥${each.bfo.orderAmount }</span><br/>
								</c:if>
								<span class="numberSeat">${each.numberSeat }</span><br/>
							</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span>目前没有任何座位，请添加座位</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<%@include file="js.jsp"%>
	<div class="modal fade " id="message" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12" id="opMessage"></b>
				</h3>
				<hr />
				<div class="tog">
					<span class="xiangqing col-md-12" style="" id="delSeatInfoName"></span>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="delMessage"
						type="button" class="bt bt-primary col-md-4">确定</button> <input
					style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span>
			</div>
		</div>
	</div>
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12" id="changeMsg"></b>
				</h3>
				<hr />
				<div class="tog">
					<form action="" method="post" class="xiangqing col-md-12">
						座位编号： <input type="text" id="seatName" value=""></br> 座位人数： <input
							type="text" id="numberSeat" value=""></br>
					</form>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="myModalc"
						type="button" class="bt bt-primary col-md-4">添加</button> <input
					style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(function(){
	$(".seatInfo").on("click",function(){
		changeSeatState(this)
	})
})
function changeSeatState(change){
		var myDate=new Date();
		console.log(change)
		var seatName=$(change).find(".seatName").html();
		var shopId=$(change).attr("shopId");
		var status=$(change).attr("seatStatus");
		var orderTime=myDate.toLocaleTimeString('chinese',{hour12:false})
		var time=$(change).find(".orderTime").html();
		console.log(orderTime);
		if(status==1){
			status=0;
		}else if(status==0){
			status=1;
		}
		const url = baseUrl + "/merchant/buffetFood/changeStatus";
		params={
				seatName:seatName,
				shopId:shopId,
				status:status,
				useSeatTime:orderTime
		}
		$.post(url,params,function(data){
			if(data.code==0){
				if(status==1){
					$(change).css("background-color","#cc5b67")
					$(change).attr("seatStatus",1)
				}else if(status==0){
					$(change).css("background-color","#b0b0b0")
					$(change).attr("seatStatus",0)
				}
				if(time==null&&status==1){
					var div=$(change).find(".seatName")
					var span="<br class='needDel'/><span class='orderTime'>"+orderTime+"</span>"
					div.after(span)
				}
				if(time!=null&&status==0){
					var span=$(change).find(".orderTime");
					var br=$(change).find(".needDel")
					console.log(span)
					span.remove();
					br.remove();
				}
				
			}else{
				aler("修改失败")
			}
		})
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
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/7
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct,java.util.List,java.util.ArrayList,com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title></title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<link
	href="${staticFilePath}resource/plugins/assets/laydate/theme/default/laydate.css" />
<script
	src="${staticFilePath}resource/plugins/assets/laydate/laydate.js"></script>
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

.oBuffetFoodQuerySpanInput>span>input {
	width: 100%;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	height: 30px;
	padding-left: 10px;
}

.oBuffetFoodQuerySpanSelect>span>select {
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

.tableNumberH>b {
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

.tableTableModeH>b {
	padding-top: 15px;
	font-size: 15px;
}

.productDetailH>h5 {
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

.productDetailBody>h5 {
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
		<%
			Integer a = (Integer) request.getAttribute("count");
		%>
		<input type="hidden" id="count" value="<%=a%>">
		<%
		int c=0;
			List<BuffetFoodOrder> data = (List<BuffetFoodOrder>) request.getAttribute("data");
			for(int i=0;i<data.size();i++){
				BuffetFoodOrder bfo=data.get(i);
		%>
			<input type="hidden" class="getMinute" id="order<%=i%>" value="<%=bfo.getMinute() %>">
		<%	List<BuffetFoodOrderProduct> productList=bfo.getProductLists();
		%>
		<div class="oldorder<%=i%>" >
		<%
		for(int b=0;b<productList.size();b++){
			BuffetFoodOrderProduct bfop = productList.get(b);
			%>
			<input type="hidden" id="productName<%=c%>"  value="<%=bfop.getProductName()%>">
			<input type="hidden" id="quantity<%=c%>"  value="<%=bfop.getQuantity()%>">
			<input type="hidden" id="price<%=c%>"  value="<%=bfop.getPrice()%>">
			<input type="hidden" id="total<%=c%>"  value="<%=(bfop.getQuantity() * bfop.getPrice())%>">
			<%
			c++;
		}
		if(c== productList.size()){
			c=0;
		}
		%></div><%
			}
			
			for (int i = 0; i < a; i++) {
				List<BuffetFoodOrderProduct> productList = (List<BuffetFoodOrderProduct>) request.getAttribute("change" + i);
				%>
				<div class="order<%=i%>" >
				<%
				for (int b = 0; b < productList.size(); b++) {
					BuffetFoodOrderProduct bfop = productList.get(b);
		%>
		<input type="hidden" id="productName<%=c%>"  value="<%=bfop.getProductName()%>">
		<input type="hidden" id="quantity<%=c%>"  value="<%=bfop.getQuantity()%>">
		<input type="hidden" id="price<%=c%>"  value="<%=bfop.getPrice()%>">
		<input type="hidden" id="total<%=c%>"  value="<%=(bfop.getQuantity() * bfop.getPrice())%>">
		<%
			c++;
		}
				if(c== productList.size()){
					c=0;
				}
				%></div><%
			}
			
		%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="body-content">
				<div class="main-container" id="main-container">
					<div class="main-content">
						<div class="tabbable">
							<div class="col-md-12"
								style="padding-left: 10px; padding-right: 10px; background-color: #f3f3f4; padding-bottom: 15px;">
								<div>
									<div class="col-md-12 nav">
										<div class="col-md-2">
											<div>
												<a href="${sysPath}merchant/buffetFood/order/new">新订单</a>
											</div>
										</div>
										<div class="col-md-2">
											<div class="active">
												<a href="${sysPath}merchant/buffetFood/order/edit">订单调整</a>
											</div>
										</div>
										<div class="col-md-2">
											<a href="${sysPath}merchant/buffetFood/order/reminder">催单</a>
										</div>

									</div>
								</div>
								<!--这是一个订单单位-->
								<div class="tabListDiv">
									<c:choose>
										<c:when test="${data != null}">
											<c:forEach items="${data}" var="each" varStatus="a">
												<!-- 有数据显示 -->
												<div class="row item col-md-12">
													<h3 class="xuhao serialNumber bodyContent col-md-12 pd-0">
														<b class="col-md-12">#${each.allSerialNumber}</b>
													</h3>
													<h5 class="col-md-12 tableNumberH bodyContent">
														<b class="col-md-12">桌号：${each.seatName}</b>
													</h5>
													<hr />
													<span class="col-md-6 productDetailBody bodyContent">
														<h5 class="col-md-12">
															<b>商品信息</b>
														</h5>
													</span> <span class="shouqi">收起<i
														class="arrow fa fa-angle-up"></i></span><br />
													<div class="tog tabDivList bodyContent" >
														<c:forEach items="${each.productLists}" var="product" varStatus="i">
															<span class="xiangqing shopContent col-md-12" id="shop${i.index} ">
															<span class="szuo col-md-6">${product.productName}</span>
															<span class="szhong col-md-1">¥${product.price}</span> 
															<span class="syou col-md-2">X${product.quantity}</span> 
															<span class="xiaojie col-md-1">¥${product.price * product.quantity}</span>
																<br />
															</span>
														</c:forEach>
													</div>
													<span class="xiangqing bodyContent col-md-12"> <br />
														<span class="szuo bodyContent col-md-6">订单时间：${each.orderTime}&nbsp;&nbsp;&nbsp;&nbsp;已提交：<b>${each.minute}</b>钟
													</span> <span class="col-md-1"></span> <span
														class="szhong bodyContent col-md-2">共<b>${each.sum}</b>件
													</span> <span class="syou bodyContent col-md-1 floatRight">合计：${each.orderAmount}<b>
														</b>元
													</span><br /> <br />
													</span>
													 <span class="col-md-12"> 
														 <span class="col-md-6 bianhao orderNumber bodyContent">订单编号：${each.orderNumber}</span>
														<span class="xiangqing col-md-6"> 
															<input type="button" class="bt bt-primary col-md-2 floatRight settleBtn curPage" value="打印小票" />
															<input type="button" class="bt bt-primary col-md-2 floatRight settleBtna curPage" value="核退" data-orderNumber="${each.orderNumber}" /> 
															<input type="button" class="bt bt-primary col-md-2 floatRight settleBtnt curPage" value="调整" order="order${a.index }" data-orderNumber="${each.orderNumber}" />
														</span>
													</span>
												</div>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<!-- 没有数据显示 -->
											<div class="showNoDataText"
												style="text-align: center; margin-top: 50px;">
												<div class="noOrder" style="padding-top: 20px;">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"
																	style="font-size: 100px; line-height: 170px; color: #fff"></i>
															</div>
															<p class="noOrder-p">暂无调整订单数据</p>
															<span class="noOrder-span">当前没有自助点餐的调整订单记录!</span>
														</div>
													</div>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
									<c:if test="${data != null}">
										<div id="page"></div>
									</c:if>
								</div>
								<!--订单单位结束-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<!-- 模态框（Modal） -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12">#3</b>
				</h3>
				<h5 style="padding-left: 0px; margin-bottom: 15px;"
					class="col-md-12">
					<b class="col-md-12">桌号：03</b>
				</h5>
				<hr />
				<span
					style="padding-left: 0px; padding-bottom: 0px; margin-top: 5px;"
					class="col-md-6"><h5 class="col-md-12"
						style="margin-bottom: 0px;">
						<b>商品信息</b>
					</h5></span> <span class="shouqi">收起<i class="arrow fa fa-angle-up"></i></span><br />
			<div class="tog" >
				<div class="tog" id="modalTog"></div>
				<span class="xiangqing col-md-12" style="border-bottom: 1px solid gainsboro; border-top: 1px solid gainsboro; height: 40px; padding-bottom: 13px;"><br/> 
					<span class="szuo col-md-4 bianhao"style="line-height: 0px; margin-top: 0px;">订单已提交：<b id="orderTime">10</b></span> 
					<span class="col-md-2"></span>
					<span class="szhong col-md-2" style="text-align: right; float: right; font-size: 14px; margin-top: 0px; line-height: 0px;">共<b>3</b>件
					</span> 
				</span>
				<span class="col-md-12" > <span class="col-md-6 bianhao" id="orderNumber">订单编号：123456</span>
					<span class="xiangqing col-md-12" style="">
							<button style="width: 15%; border-radius: 6px;" id="myModalc" type="button" class="bt bt-primary col-md-4">打印</button>
							<button style="width: 15%; border-radius: 6px;" id="myModalt" type="button" class="bt bt-primary col-md-4" >确认调单</button>
							<input style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;" data-dismiss="modal" type="button" class="bt bt-default col-md-4 " value="取消" />
					</span>
				</span>
			</div>
		</div>
	</div>
	<%@include file="js.jsp"%>
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

<script>
    $(function () {
    	//给所有确认调整绑定激活模态框事件
        $('.shouqi').click(function () {
            if ($(this).html() == '收起<i class="arrow fa fa-angle-up"></i>') {
                $(this).html('展开<i class="arrow fa fa-angle-down"></i>');
            } else {
                $(this).html('收起<i class="arrow fa fa-angle-up"></i>');
            }
            $(this).nextAll('.tog').toggle(200);
        });

        $(function () {
            $("#myModalc").click(function () {
                $("#myModal *:not('.modal-footer')").jqprint();
            });

        });

        $(function () {
            laydate.render({
                elem: '.timepicker'
            });
            laydate.render({
                elem: '.timepicker2'
            });
        });
    });

    $(".settleBtna.curPage").on("click", function () {
        var orderNumber = $(this).attr("data-orderNumber");
        const url = baseUrl + "/merchant/buffetFood/replyFalse";
        $.post(url,{orderNumber:orderNumber},function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/buffetFood/order/edit";
                }else {
                    alert("核退失败");
                }
            }else {
                alert("网络连接失败");
            }
        })
    });

    //调单弹窗
	$(".settleBtnt.curPage").on("click", function () {
		debugger
		$("#myModal").modal('show');
		var modalTog=$("#modalTog");
			modalTog.empty();
		//获取订单序号
		var orderNum=$(this).attr("order");
		//获取订单下详细内容数量
		var order=($("."+orderNum).find("input").length)/4;
		//获取订单提交的时间
		var orderTime=$("#"+orderNum).val();
		$("#orderTime").html(orderTime);
		//获取订单编号
	    var orderNumber = $(this).attr("data-orderNumber");
		$("#orderNumber").html("订单编号："+orderNumber);
		//订单变更后
		var oldOrder=($(".old"+orderNum).find("input").length)/4;
		var orders;
		if(order<oldOrder){
			orders=oldOrder;
		}else if(order>oldOrder){
			orders=order;
		}else {
			orders=order;
		}
			var oldproductName;
			var oldprice;
			var oldquantity;
			var oldtotal;
			var productName;
			var price;
			var quantity;
			var total;
			var stylproductName;
			var stylprice;
			var stylquantity;
			var styltotal;
		for(var i=0;i<orders;i++){
			 oldproductName = $(".old"+orderNum).find("#productName"+i).val();
			 oldprice=$(".old"+orderNum).find("#price"+i).val();
			 oldquantity=$(".old"+orderNum).find("#quantity"+i).val();
			 oldtotal=$(".old"+orderNum).find("#total"+i).val();
			 productName = $("."+orderNum).find("#productName"+i).val();
			 price=$("."+orderNum).find("#price"+i).val();
			 quantity=$("."+orderNum).find("#quantity"+i).val();
			 total=$("."+orderNum).find("#total"+i).val();
		if(oldproductName!==productName||oldprice!==price||oldquantity!==quantity||oldtotal!==total){
			stylproductName="layui-bg-red"
		}else{
			stylproductName=""
		}
		var span="<span class='xiangqing col-md-12 "+stylproductName+"' id=shop"+i+" >"+
		"<span class='szuo col-md-3' id='model' >"+productName+"</span> <!--<span class='szhong col-md-3'>"+price+"</span>-->"+
		"<span class='syou col-md-2' style='float: right;'>X"+quantity+"</span>"+
		"<!--<span class='xiaojie col-md-3' >"+total+"</span>--> <br />"
		modalTog.append(span)
		}
        })
        
    $("#myModalt").on("click",function(){
        var orderNumber = $(this).attr("data-orderNumber");
        const url = baseUrl + "/merchant/buffetFood/enterAdjust";
        $.post(url,{orderNumber:orderNumber},function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/buffetFood/order/edit";
                }else {
                    alert("调单失败");
                }
            }else {
                alert("网络连接失败");
            }
    })
    })
  
</script>
</html>

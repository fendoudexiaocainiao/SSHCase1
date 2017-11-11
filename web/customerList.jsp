<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet"
	      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

	<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet"
	      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
			src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>

	<style type="text/css">
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
		}

		.bg {
			max-width: 530px;
			padding: 15px;
			margin: 0 auto;
		}
	</style>
	<script type="text/javascript">
		function addCustomer() {
			location.href = "${pageContext.request.contextPath}/addCustomer.jsp";
		}

		//当前页码
		var pageNum = 1;
		//总页数
		var totalPage = 0;
		//总条数
		var totalCount = 0;
		//每页条数
		var currentCount = 5;

		//查询订单操作
		var cid;

		function findOrder(customerId) {

			cid = customerId;

			$.post("${pageContext.request.contextPath}/orders/findOrders", {
				'cId': customerId,
				"currentPage": pageNum,
				"currentCount": currentCount
			}, function (data) {


				var json = eval(data);
				var clientsName = json.target.cName;
				//{"currentCount":5,"pageNum":1,"totalCount":2,"totalPage":1}
				var html = "";
				var jsonObj = json.target.orders;
				for (var i = 0; i < jsonObj.length; i++) {
					//将html代码组装
					html += "<tr><td>" + jsonObj[i].oId + "</td><td>" + jsonObj[i].oReceiverAddress + "</td><td>" + jsonObj[i].oPrice + "</td><td>" + clientsName + "</td><td><a href='#'>删除</a></td></tr>";
				}

				$("#msg").html(html);

				//分页信息处理
				pageNum = json.currentPage;
				totalPage = json.maxPages;
				totalCount = json.maxRecords;
				currentCount = json.currentRecords;
				//展示分页组件
				/*
				<ul class="pagination">
							<li class="disabled"><a href="#">&laquo;</a></li>
							<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
				*/
				var pageHtml = "<ul class=\"pagination\">";
				//1.判断是否可以向上翻页
				if (pageNum == 1) {
					pageHtml += "<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
				} else {
					pageHtml += "<li><a href=\"#\" onclick=\"prePage()\">&laquo;</a></li>";
				}

				//2.判断中间页码
				for (var i = 1; i <= totalPage; i++) {
					if (i == pageNum) {
						pageHtml += "<li class=\"active\"><a href=\"#\" onclick=\"selectPage(" + i + ")\">" + i + "</a></li>";
					} else {
						pageHtml += "<li><a href=\"#\" onclick=\"selectPage(" + i + ")\">" + i + "</a></li>";
					}
				}

				//3.判断是否可以向下翻页
				if (pageNum == totalPage) {
					pageHtml += "<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>";
				} else {
					pageHtml += "<li><a href=\"#\" onclick=\"nextPage()\">&raquo;</a></li>";
				}
				pageHtml += "</ul>";

				$("#page").html(pageHtml);
			},"json");
		}

		//向上翻页
		function prePage() {
			pageNum = pageNum - 1;
			findOrder(cid);
		}

		//向下翻页
		function nextPage() {
			pageNum = pageNum + 1;
			findOrder(cid);
		}

		//指定页跳转
		function selectPage(pn) {
			pageNum = pn;
			findOrder(cid);
		}
	</script>
</head>
<body>


<table class="table table-hover table-bordered bg">
	<tr>
		<td colspan="5">
			<!-- <button type="button" class="btn btn-primary btn-lg active btn-sm"
				onclick="addCustomer()">Add Customer</button> --> <a
				href="${pageContext.request.contextPath}/addCustomer.jsp"
				class="btn btn-primary btn-lg active" role="button">添加客户</a>
		</td>
	</tr>
	<tr>
		<td>序号</td>
		<td>客户</td>
		<td>客户名称</td>
		<td>联系电话</td>
		<td>操作</td>
	</tr>
	<s:iterator value="clientList" var="c" status="vs">
		<tr>
			<td><s:property value="#vs.index+1"/></td>
			<td><img src="${pageContext.request.contextPath}<s:property value='#c.cImgsrc'/>"
			         class="img-circle"></td>
			<td><s:property value="#c.cName"/></td>
			<td><s:property value="#c.cPhone"/></td>
			<td><a href="${pageContext.request.contextPath}/clients/delClients?cId=<s:property value="#c.cId"/>"
			       class="btn btn-primary btn-sm">删除客户</a> <a
					href="javascript:void(0)"
					onclick="findOrder('<s:property value="#c.cId"/>')"
					class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">订单详情</a></td>
		</tr>
	</s:iterator>

</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">订单详情</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered .table-hover">
					<tr>
						<td>订单编号</td>
						<td>收货地址</td>
						<td>订单价格</td>
						<td>客户名称</td>
						<td>操作</td>
					</tr>
					<tbody id="msg">

					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<nav id="page"></nav>
			</div>
		</div>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "vnua.fita.bookstore.util.Constant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bookstore_style.css">
<title>Thông tin đơn hàng</title>
</head>
<body>
	<jsp:include page="_header_backend.jsp"></jsp:include>
	<c:if test="${listType == Constant.WAIT_FOR_CONFIRMATION}">
	    <div style="padding: 5px; text-align: center;">
	        <a href="${pageContext.request.contextPath}/adminHome">Trang chủ</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/waiting" style="text-decoration:none;color:blue;">Các đơn hàng chưa xác nhận</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivering">Các đơn hàng đang chờ giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivered">Các đơn hàng đã giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/reject">Các đơn hàng khách trả lại</a>
	    </div>
	</c:if>
		<c:if test="${listType == Constant.WAITING_FOR_DELIVERY}">
	    <div style="padding: 5px; text-align: center;">
	        <a href="${pageContext.request.contextPath}/adminHome">Trang chủ</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/waiting">Các đơn hàng chưa xác nhận</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivering" style="text-decoration:none;color:blue;">Các đơn hàng đang chờ giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivered">Các đơn hàng đã giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/reject">Các đơn hàng khách trả lại</a>
	    </div>
	</c:if>
		<c:if test="${listType == Constant.DELIVERED}">
	    <div style="padding: 5px; text-align: center;">
	        <a href="${pageContext.request.contextPath}/adminHome">Trang chủ</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/waiting">Các đơn hàng chưa xác nhận</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivering">Các đơn hàng đang chờ giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivered" style="text-decoration:none;color:blue;">Các đơn hàng đã giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/reject">Các đơn hàng khách trả lại</a>
	    </div>
	</c:if>
		<c:if test="${listType == Constant.CUSTOMER_RETURN}">
	    <div style="padding: 5px; text-align: center;">
	        <a href="${pageContext.request.contextPath}/adminHome">Trang chủ</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/waiting">Các đơn hàng chưa xác nhận</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivering">Các đơn hàng đang chờ giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/delivered">Các đơn hàng đã giao</a>
	        |
	        <a href="${pageContext.request.contextPath }/adminOrderList/reject" style="text-decoration:none;color:blue;">Các đơn hàng khách trả lại</a>
	    </div>
	</c:if>
	<div align="center">
		<h3>DANH SÁCH ĐƠN HÀNG ${listType}</h3>
		<form id="adminOrderForm" method="post" action="">
			<input type="hidden" name="orderId" id="orderIdOfAction">
			<input type="hidden" name="confirmType" id="confirmTypeOfAction">
		</form>	
		<p style="color: red;">${errors}</p>
		<p style="color: blue;">${message}</p>
		<table border="1">
				<tr>
					<th>Mã hóa đơn</th>
					<th>Tên khách</th>
					<th>Số điện thoại</th>
					<th>Ngày đặt mua</th>
					<th>Ngày xác nhận</th>
					<th>Địa chỉ nhận sách</th>
					<th>Phương thức thanh toán</th>
					<th>Trạng thái đơn hàng</th>
				</tr>
				<c:forEach items="${orderListOfCustomer}" var="orderOfCustomer">
					<tr>
						<td>${orderOfCustomer.orderNo}</td>
						<td>${orderOfCustomer.customer.fullname}</td>
						<td>${orderOfCustomer.customer.mobile}</td>
						<td><fmt:formatDate value="${orderOfCustomer.orderDate}"
								pattern="dd-MM-yyyy HH:mm" /></td>
						<td><fmt:formatDate
								value="${orderOfCustomer.orderApproveDate}"
								pattern="dd-MM-yyyy HH:mm" /></td>
						<td>${orderOfCustomer.deliveryAddress}</td>
						<td>${orderOfCustomer.paymentModeDescription}<br>
							<c:if test="${Constant.TRANSFER_PAYMENT_MODE.equals(orderOfCustomer.paymentMode)}">
								<button
									onclick="document.getElementById('divImg${orderOfCustomer.orderId}').style.display='block';">Xem
									ảnh</button>
								<button
									onclick="document.getElementById('divImg${orderOfCustomer.orderId}').style.display='none';">Ẩn</button>
								<br>
								<div id="divImg${orderOfCustomer.orderId}"
									style="display: none; padding-top: 5px;">
									<img alt="Transfer Image"
										src="${pageContext.request.contextPath}/${orderOfCustomer.paymentImagePath}"
										width="150">
								</div>
							</c:if>
						</td>
						<td>${orderOfCustomer.orderStatusDescription}
							<button
								onclick="document.getElementById('div${orderOfCustomer.orderId}').style.display='block';">Xem
								chi tiết</button>
							<button
								onclick="document.getElementById('div${orderOfCustomer.orderId}').style.display='none';">Ẩn</button>
							<!-- Khối thông tin chi tiết hóa đơn  -->
							<div id="div${orderOfCustomer.orderId}" style="display: none;">
								<h3>Các cuốn sách trong hóa đơn</h3>
								<table border="1">
									<tr style="background-color: yellow;">
										<th>Tiêu đề</th>
										<th>Tác giả</th>
										<th>Giá tiền</th>
										<th>Số lượng mua</th>
										<th>Tổng thành phần</th>
									</tr>
									<c:forEach items="${orderOfCustomer.orderBookList}"
										var="cartItem">
										<tr>
											<td>${cartItem.selectedBook.title}</td>
											<td>${cartItem.selectedBook.author}</td>
											<td><fmt:formatNumber type="number"
													maxFractionDigits="0"
													value="${cartItem.selectedBook.price}"></fmt:formatNumber><sup>đ</sup></td>
											<td>${cartItem.quantity}</td>
											<td><fmt:formatNumber type="number"
													maxFractionDigits="0"
													value="${cartItem.selectedBook.price * cartItem.quantity}"></fmt:formatNumber><sup>đ</sup></td>
										</tr>
									</c:forEach>
								</table>
								<br> Tổng số tiền: <b> <span> <fmt:formatNumber
											type="number" maxFractionDigits="0"
											value="${orderOfCustomer.totalCost}"></fmt:formatNumber>
								</span> <sup>đ</sup>
							<c:if
								test="${Constant.WAITING_CONFIRM_ORDER_STATUS == orderOfCustomer.orderStatus}">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId},${Constant.DELIVERING_ORDER_STATUS},'${Constant.WAITING_APPROVE_ACTION}');">Xác nhận đơn</button>
							</c:if>
							<c:if
								test="${Constant.DELIVERING_ORDER_STATUS == orderOfCustomer.orderStatus}">
								<button onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId},${Constant.DELIVERED_ORDER_STATUS},'${Constant.DELEVERING_ACTION}');">Đã giao hàng</button>
								&nbsp;&nbsp;
								<button onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId},${Constant.REJECT_ORDER_STATUS},'${Constant.DELEVERING_ACTION}');">Khách trả hàng</button>
							</c:if>
							</div> <!-- ------------------------------------------------------------- -->
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
	<script type="text/javascript">
	function onClickAdminOrderConfirm(orderId,confirmType,action){
		document.getElementById("orderIdOfAction").value=orderId;
		document.getElementById("confirmTypeOfAction").value=confirmType;
		document.getElementById("adminOrderForm").action=action.substring(0);
		document.getElementById("adminOrderForm").submit();
	}
	</script>
</body>
</html>
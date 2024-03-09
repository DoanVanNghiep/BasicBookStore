package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.bcel.classfile.Constant;

import vnua.fita.bookstore.bean.Order;
import vnua.fita.bookstore.model.BookDAO;
import vnua.fita.bookstore.model.OrderDAO;
import vnua.fita.bookstore.util.MyUtil;

/**
 * Servlet implementation class AdminOrderListServlet
 */
@WebServlet(urlPatterns = { "/adminOrderList/waiting","/adminOrderList/delivering","/adminOrderList/delivered","/adminOrderList/reject"})
public class AdminOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		orderDAO = new OrderDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		String pathInfo = MyUtil.getPathInfoFromServletPath(servletPath);
		List<Order> orderListOfCustomer = new ArrayList<Order>();
		if(vnua.fita.bookstore.util.Constant.WAITING_APPROVE_ACTION.equals(pathInfo)) {
			orderListOfCustomer = orderDAO.getOrderList(vnua.fita.bookstore.util.Constant.WAITING_CONFIRM_ORDER_STATUS);
			request.setAttribute("listType", "CHỜ XÁC NHẬN");
		}else if(vnua.fita.bookstore.util.Constant.DELIVERING_ACTION.equals(pathInfo)) {
			orderListOfCustomer = orderDAO.getOrderList(vnua.fita.bookstore.util.Constant.DELIVERING_ORDER_STATUS);
			request.setAttribute("listType", "ĐANG CHỜ GIAO");
		}else if(vnua.fita.bookstore.util.Constant.DELIVERED_ACTION.equals(pathInfo)) {
			orderListOfCustomer = orderDAO.getOrderList(vnua.fita.bookstore.util.Constant.DELIVERED_ORDER_STATUS);
			request.setAttribute("listType", "ĐÃ GIAO");
		}else if(vnua.fita.bookstore.util.Constant.REJECT_ACTION.equals(pathInfo)) {
			orderListOfCustomer = orderDAO.getOrderList(vnua.fita.bookstore.util.Constant.REJECT_ORDER_STATUS);
			request.setAttribute("listType", "KHÁCH TRẢ LẠI");
		}
		
		request.setAttribute("orderListOfCustomer", orderListOfCustomer);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Views/adminOrderListView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		String orderIdStr = request.getParameter("orderId");
		String confirmTypeStr = request.getParameter("confirmType");
		int orderId = -1;
		try {
			orderId = Integer.parseInt(orderIdStr);
		} catch (Exception e) {
			// TODO: handle exception
			errors.add(vnua.fita.bookstore.util.Constant.ORDER_ID_INVALID_VALIDATE_MGS);
		}
		byte confirmType = -1;
		try {
			confirmType = Byte.parseByte(confirmTypeStr);
		} catch (Exception e) {
			// TODO: handle exception
			errors.add(vnua.fita.bookstore.util.Constant.VALUE_INVALID_VALIDATE_MGS);
		}
		if (errors.isEmpty()) {
			boolean updateResult = false;
			if (vnua.fita.bookstore.util.Constant.DELIVERING_ORDER_STATUS == confirmType) {
				// xác nhận đơn và chuyển trạng thái đang giao hàng
				updateResult = orderDAO.updateOrderNo(orderId, confirmType);
			}else if (vnua.fita.bookstore.util.Constant.DELIVERED_ORDER_STATUS == confirmType) {
				// xác nhận đơn và chuyển trạng thái đang giao hàng
				updateResult = orderDAO.updateOrder(orderId, confirmType);
			}else if (vnua.fita.bookstore.util.Constant.REJECT_ORDER_STATUS == confirmType) {
				// xác nhận đơn và chuyển trạng thái đang giao hàng
				updateResult = orderDAO.updateOrder(orderId, confirmType);
			}
			if (updateResult) {
				request.setAttribute("message", "cập nhật đơn hàng thành công");
			}else {
				errors.add("cập nhật đơn hàng không thành công");
			}
			
			if (!errors.isEmpty()) {
				request.setAttribute("errors", String.join(", ", errors));
			}
		}
		doGet(request, response);
	}

}

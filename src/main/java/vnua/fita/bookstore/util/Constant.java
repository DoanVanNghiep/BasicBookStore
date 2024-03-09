package vnua.fita.bookstore.util;

public class Constant {
	public static final byte WAITING_CONFIRM_ORDER_STATUS = 1; // trạng thái chờ xác nhận
	public static final byte DELIVERING_ORDER_STATUS = 2; //trạng thái đang giao
	public static final byte DELIVERED_ORDER_STATUS =3; //trạng thái đã giao
	public static final byte CANCEL_ORDER_STATUS =4; //trạng thái hủy đơn
	public static final byte REJECT_ORDER_STATUS =5; //trạng thái trả hàng
	public static final byte NOT_AVAIABLE_ORDER_STATUS = 6; //trạng thái hàng không đủ
	
	
	public static final String REJECT_ACTION = "Khách trả lại hàng";
	public static final String DELIVERING_ACTION = "Đang chờ giao";
	public static final String DELIVERED_ACTION = "Đã giao";
	public static final String WAITING_APPROVE_ACTION = " Đang chờ xác nhận ";
	public static final String PAYMENTED_STATUS = "Đã thanh toán";
	public static final String UNPAYMENT_STATUS = "Chưa thanh toán";
	public static final String WARTING_CONFIRM_ORDER_STATUS = "Cảnh báo xác nhận tình trạng đơn hàng";
	
	public static final String BOOK_ID_INVALID_VALIDATE_MSG="Book id xác thực không hợp lệ"; 
	public static final String BOOK_QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG="Số lượng trong kho xác thực không hợp lệ"; 
	public static final String ADD_TO_CART_ACTION="addToCart"; 
	public static final String ADD_ORDER_ERROR_MSG="Lỗi đơn hàng"; 
	public static final String NOT_ENOUGHT_ORDER_ERROR_MSG="Lỗi không đủ hàng"; 
	public static final String CART_OF_CUSTOMER="cartOfCustomer"; 
	public static final String ORDER_OF_CUSTOMER="orderOfCustomer"; 
	public static final String REMOVE_FROM_CART_ACTION="removeFromCart"; 
	public static final String GET_BOOK_FAIL="Lỗi lấy danh sách sách"; 
	public static final String INCORRECT_ACCOUNT_VALIDATE_MSG="Sai thông tin tài khoản"; 
	public static final int ADMIN_ROLE=1; 
	public static final int CUSTOMER_ROLE=2; 
	public static final String USERNAME_EMPTY_VALIDATE_MSG="Nhap username"; 
	public static final String PASSWORD_EMPTY_VALIDATE_MSG="Nhap password"; 
	public static final String LOGINED_USER="loginedUser";
	public static final String USERNAME_STORE_IN_COOKIE_OF_BOOKSTORE="username";
	public static final String TOKEN_STORE_IN_COOKIE_OF_BOOKSTORE="token";
	public static final String SECRET_STRING="SECRET_STRING";
	public static final String INSERT_BOOK_FAIL="Lỗi thêm mới sách";
	public static final String CASH_PAYMENT_MODE="cash";
	public static final String TRANSFER_PAYMENT_MODE = "transfer";
	public static final String UNPAYMENTED_STATUS="Chưa trả tiền";
	public static final String DELEVERY_ADDRESS_EMPTY_VALIDATE_MSG="Địa chỉ giao hàng không thể bỏ trống";
	public static final String TRANSFER_IMAGE_EMPTY_MSG="Ảnh giao dịch không thể bỏ trống";
	public static final String ORDER_LIST_OF_CUSTOMER="orderListOfCustomer";
	public static final String ORDER_ID_INVALID_VALIDATE_MGS = "id đơn hàng không hợp lệ";
	public static final String VALUE_INVALID_VALIDATE_MGS = "Giá trị đơn hàng không hợp lệ";
	
}

package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.common.Config;
import vnua.fita.bookstore.testDTO.PaymentResDTO;

public class ajaxServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        
//	        String orderType = "other";
//	        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//	        String bankCode = req.getParameter("bankCode");
		 	long amount = 1000000;
		 
		 
	        String vnp_TxnRef = Config.getRandomNumber(8);
	        String vnp_IpAddr = Config.getIpAddress(req);

	        String vnp_TmnCode = Config.vnp_TmnCode;
	        
	        Map<String, String> vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_Version", Config.vnp_Version);
	        vnp_Params.put("vnp_Command", Config.vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_Amount", String.valueOf(amount));
	        vnp_Params.put("vnp_CurrCode", "VND");
	        vnp_Params.put("vnp_BankCode", "NCB");
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
	        vnp_Params.put("vnp_Locale", "vn");
	        
//	        if (bankCode != null && !bankCode.isEmpty()) {
//	            vnp_Params.put("vnp_BankCode", bankCode);
//	        }
//	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//	        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
//	        vnp_Params.put("vnp_OrderType", orderType);

//	        String locate = req.getParameter("language");
//	        if (locate != null && !locate.isEmpty()) {
//	            vnp_Params.put("vnp_Locale", locate);
//	        } else {
//	            vnp_Params.put("vnp_Locale", "vn");
//	        }
//	        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
//	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        
	        cld.add(Calendar.MINUTE, 15);
	        String vnp_ExpireDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
	        
	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                //Build query
	                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	                query.append('=');
	                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
	        
	        PaymentResDTO dto = new PaymentResDTO();
	        dto.setStatus("OK");
	        dto.setMessage("Thành công");
	        dto.setURL(paymentUrl);
	        
	        System.out.println(dto.toString());
	        
//	        com.google.gson.JsonObject job = new JsonObject();
//	        job.addProperty("code", "00");
//	        job.addProperty("message", "success");
//	        job.addProperty("data", paymentUrl);
//	        Gson gson = new Gson();
//	        resp.getWriter().write(gson.toJson(job));
	    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
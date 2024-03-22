package vnua.fita.bookstore.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class vnpayQuery {
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        //Command:querydr

	        String vnp_RequestId = Config.getRandomNumber(8);
	        String vnp_Version = "2.1.0";
	        String vnp_Command = "querydr";
	        String vnp_TmnCode = Config.vnp_TmnCode;
	        String vnp_TxnRef = req.getParameter("order_id");
	        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + vnp_TxnRef;
	        //String vnp_TransactionNo = req.getParameter("transactionNo");
	        String vnp_TransDate = req.getParameter("trans_date");
	        
	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        
	        String vnp_IpAddr = Config.getIpAddress(req);
	        
	        Map<String, String> vnp_Params = new HashMap<String, String>();
	        
	        vnp_Params.put("vnp_RequestId", vnp_RequestId);
	        vnp_Params.put("vnp_Version", vnp_Version);
	        vnp_Params.put("vnp_Command", vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
	        //vnp_Params.put("vnp_TransactionNo", vnp_TransactionNo);
	        vnp_Params.put("vnp_TransactionDate", vnp_TransDate);
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
	        
	        String hash_Data= String.join("|", vnp_RequestId, vnp_Version, vnp_Command, vnp_TmnCode, vnp_TxnRef, vnp_TransDate, vnp_CreateDate, vnp_IpAddr, vnp_OrderInfo);
	        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hash_Data.toString());
	        
	        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);
	        
	        URL url = new URL (Config.vnp_ApiUrl);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(vnp_Params.toString());
	        wr.flush();
	        wr.close();
	        int responseCode = con.getResponseCode();
	        System.out.println("nSending 'POST' request to URL : " + url);
	        System.out.println("Post Data : " + vnp_Params);
	        System.out.println("Response Code : " + responseCode);
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	        String output;
	        StringBuffer response = new StringBuffer();
	        while ((output = in.readLine()) != null) {
	        response.append(output);
	        }
	        in.close();
	        System.out.println(response.toString());
	    }
}

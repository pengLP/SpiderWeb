package foreign;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * ·­ÒëÄ£¿é
 * @author ÂäÑ©·â³¾
 *
 */
import com.baidu.translate.demo.TransApi;

import spider.error_log;

public class translate {

    private static final String APP_ID = "20180312000134531";
    private static final String SECURITY_KEY = "YN8jUwZ7yFSwoMlr93Wk";
    
    public String translate_title(String query) {
    	String result = "";
    	try {
	    	TransApi api = new TransApi(APP_ID, SECURITY_KEY);
	    	String json = api.getTransResult(query, "auto", "zh");
	    	JSONObject jo = JSON.parseObject(json);
	    	String tmp = jo.getString("trans_result");
	    	Pattern p = Pattern.compile("dst\":\"(.*?)\",");
	    	Matcher m = p.matcher(tmp);
	    	while (m.find()) {
	    		return m.group(1);
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    		new error_log(e);
    	}
    	return "";
    }
//    public static void main(String[] args) {
//		 String s = new translate().translate_title("Best picture winner 'The Shape of Water' closes awards season punctuated by sexual-harassment scandals");
//		 System.out.println(s);
//    }
}


package other;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.junit.Test;

import com.cy.wx.util.CommonUtil;
import com.cy.wx.util.HttpClientUtils;

public class HTTPTest {
	
	@Test
	public void test() throws Exception{
		
		String url = "http://api.cy.com/login";
		String jsonObject = HttpClientUtils.get(url, "UTF-8");
		System.out.println(jsonObject);
	}
	
	@Test
	public void test2(){
		String loginname = "1581061451";
		String password = "fafafas";
		String charset = "utf-8";
		String date = "20170828";
		String version = "";
		String device = "";
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginname", loginname);
        params.put("password", password);
        params.put("date", date);
        params.put("charset", charset);
        params.put("version", version);
        params.put("device", device);
        
        HttpEntity entity = new HttpEntity();
        entity.setLoginname(loginname);
        entity.setDate(date);
        entity.setCharset(charset);
        entity.setPassword(password);
        
        // 过滤空域
        JsonConfig jsonConfig = new JsonConfig();
	    PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				return null == fieldValue || "".equals(fieldValue); // 过滤空值
			}
	    };
	    jsonConfig.setJsonPropertyFilter(filter);
	    JSONObject jsonObj = JSONObject.fromObject(entity, jsonConfig);
	    System.out.println(jsonObj);
	}

}

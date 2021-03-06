package comm;

import com.cy.wx.model.Token;
import com.cy.wx.util.CommonUtil;

public class InitUtil {
	public static String accessToken;

	public static void initAccessToken() throws Exception{
		long createTime = Long.valueOf(CommUtil.getProperties(Constant.CREATE_TIME));
		if(System.currentTimeMillis() - createTime > 7000*1000){
			Token token = CommonUtil.getToken(Constant.APPID, Constant.APPSECRET);
			CommUtil.setProperties("accessToken", token.getAccessToken());
			CommUtil.setProperties("createTime", String.valueOf(System.currentTimeMillis()));
		}
		
		accessToken = CommUtil.getProperties(Constant.ACCESS_TOKEN);
	}
}

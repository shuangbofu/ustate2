package cn.ustate.utils;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * @author EricFu
 * @date 2017-8-17 下午3:13:55
 */
@SuppressWarnings("deprecation")
public class AuthUtil {
	
	public static JSONObject doGetJson(String url, String method) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = null;
		HttpPost httpPost = null;
		HttpResponse response = null;
		if (method == "get") {
			httpGet = new HttpGet(url);
			response = client.execute(httpGet);
		} else {
			httpPost = new HttpPost(url);
			response = client.execute(httpPost);
		}
		
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		if (httpGet != null) {
			httpGet.releaseConnection();
		}
		if (httpPost != null) {
			httpPost.releaseConnection();
		}
		return jsonObject;
	}
	public static final String client_ID = "1832218868";
	public static final String client_SERCRET = "0cb34061807486291b88a81ea38eda30";
	
}

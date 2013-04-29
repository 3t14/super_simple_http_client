package asia.scri.super_simple_http_client;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public interface SuperSimpleHttpClientCallback {
	public void onReceiveResponse(HttpResponse response);
	public void onReceive(String result);
	public void onReceiveJSONObject(JSONObject jsonObject);
	public void onReceiveJSONArray(JSONArray jsonArray);	
}

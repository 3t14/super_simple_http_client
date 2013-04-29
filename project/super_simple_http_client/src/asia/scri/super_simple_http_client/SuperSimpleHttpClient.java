package asia.scri.super_simple_http_client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

public class SuperSimpleHttpClient {

	/**
	 * get a String response by HTTP GET Method.
	 * @param uri	access target URI
	 * @param callback callback instance
	 */
	static void get(String uri, SuperSimpleHttpClientCallback callback){
		GetAsyncTask task = new GetAsyncTask(callback, uri, GetAsyncTask.MODE_STRING);
		task.execute();
	}
}


class GetAsyncTask extends AsyncTask<Void, Void, String> {

	public final static  int MODE_STRING = 0;
	public final static  int MODE_RESPONSE = 1;
	public final static  int MODE_JSON_OBJECT = 2;
	public final static  int MODE_JSON_ARRAY = 3;
	
	SuperSimpleHttpClientCallback callback;
	String uri;
	int mode; 
	

	public GetAsyncTask(SuperSimpleHttpClientCallback callback, String uri, int mode) {
		super();
		this.callback = callback;
		this.uri = uri;
		this.mode = mode;
	}

	@Override
	protected String doInBackground(Void... params) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		 
		HttpResponse res = null;
		 
		try {
		    res = httpClient.execute(get);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String body;
		try {
			body = EntityUtils.toString(res.getEntity(), "UTF-8");
			return body;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	protected void onPostExecute(String result) {
		
		try {
			switch (mode){
			case MODE_STRING:
				callback.onReceive(result);
				break;
			case MODE_JSON_OBJECT:
				JSONObject jsonObject = new JSONObject(result);
				callback.onReceiveJSONObject(jsonObject);
				break;
			case MODE_JSON_ARRAY:
				JSONArray jsonArray = new JSONArray(result);
				callback.onReceiveJSONArray(jsonArray);
			}
			return;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		super.onPostExecute(result);
	}
}
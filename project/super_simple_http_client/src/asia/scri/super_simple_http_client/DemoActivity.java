package asia.scri.super_simple_http_client;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DemoActivity extends Activity implements SuperSimpleHttpClientCallback  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		String uri = "http://search.twitter.com/search.json?q=scri";
		SuperSimpleHttpClient.get(uri, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_demo, menu);
		return true;
	}

	@Override
	public void onReceiveResponse(HttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceive(String result) {
		Toast.makeText(this,result, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onReceiveJSONObject(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveJSONArray(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		
	}


}

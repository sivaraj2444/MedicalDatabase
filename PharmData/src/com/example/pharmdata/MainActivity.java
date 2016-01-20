package com.example.pharmdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.application.AppController;
import com.example.database.MedicDatabase;
import com.example.database.Medicine;

public class MainActivity extends Activity {

	private String URL = "https://www.1mg.com/api/v1/search/autocomplete?name=b&pageSize=100&_=1435404923427";
	MedicDatabase mdb;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			mdb = new MedicDatabase(this);
			mdb.onUpgrade(mdb.getWritableDatabase(), 1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		callService();
	}

	private void callService() {

	

		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, URL,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						onGetCompleted(response);
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("TAG", "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		AppController.getInstance().addToRequestQueue(jsonObjReq, "med_rep");
	}

	public void onGetCompleted(JSONObject newJsObj) {

		if (newJsObj != null) {
			try {
				JSONArray jsArray = newJsObj.getJSONArray("result");
				for (int i = 0; i < jsArray.length(); i++) {
					mdb.createMedicineRecord(new Medicine(jsArray
							.getJSONObject(i)));
				}
				startActivity(new Intent(MainActivity.this,
						MedicDataActivity.class));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

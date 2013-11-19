package alialicoo.com.dice;

import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

public class PhoneInfo {

	JSONObject mjo;

	public void getPhoneInfoParams(Context mc) {

		try {
			mjo = new JSONObject();
			mjo.put("APPName", mc.getApplicationContext().getPackageName());
			mjo.put("VersionCode",
					mc.getPackageManager().getPackageInfo(
							mc.getApplicationContext().getPackageName(), 0).versionCode);
			// getMemoryClass
			ActivityManager activityManager = (ActivityManager) mc
					.getSystemService("activity");
			mjo.put("MemoryClass", activityManager.getMemoryClass());
			// getPhoneBaseInfo
			mjo.put("Phone_Manufacturer", android.os.Build.MANUFACTURER);
			mjo.put("Sdk_Vesion", android.os.Build.VERSION.SDK);
			mjo.put("Phone_Language", Locale.getDefault().getLanguage());
			mjo.put("Phone_Country", Locale.getDefault().getCountry());
			DisplayMetrics dm = mc.getResources().getDisplayMetrics();
			Commdata.screenHeight = dm.heightPixels;
			Commdata.screenWidth = dm.widthPixels;
			mjo.put("Screen_Height", dm.heightPixels);
			mjo.put("Screen_Width", dm.widthPixels);
			// getANDROID_ID
			mjo.put("ANDROID_ID",
					android.provider.Settings.System.getString(
							mc.getContentResolver(), "android_id"));

			getPhoneNetLocal(mc);

			
			
			Listener<JSONObject> ml = new Listener<JSONObject>() {
			public void onResponse(JSONObject arg0) {
				try {
					Log.d("log",arg0.toString());
				
				} catch (Exception e) {
				}
			}
			
			};
			
			JsonObjectRequest mys = new JsonObjectRequest(Method.POST, Commdata.user_log_addrs, mjo, ml, null);
			Commdata.VolleyQueue.add(mys);
			Commdata.VolleyQueue.start();
			// getPhone_IDS
			// TelephonyManager tm = (TelephonyManager)
			// mc.getSystemService(Context.TELEPHONY_SERVICE);
			// mjo.put("Phone_DeviceId", tm.getDeviceId());
			// mjo.put("Phone_SubscriberId", tm.getSubscriberId());

			// 获取当前程序路径
			// getApplicationContext().getFilesDir().getAbsolutePath();
			// 获取该程序的安装包路径
			// String path=getApplicationContext().getPackageResourcePath();
			// 获取程序默认数据库路径
			// getApplicationContext().getDatabasePath(s).getAbsolutePath();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void getPhoneNetLocal(Context mc) {

		Listener<String> ml2 = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				try {
					String s = response.replace("function ", "\"");
					s = s.replace("{ return '", "\"");
					s = s.replace("'; }", "\",");
					s = s.replace("()", "\":");
					s = s.replace("\n", "");
					s = s.replace(" ", "");
					s = s.trim();
					s = s.substring(0, s.lastIndexOf(","));
					s = "{" + s + "}";
					mjo.put("Net_Local", new JSONObject(s));
				} catch (Exception e) {
					// TODO: handle exception
					return;
				}
				return;
			}
		};
		try {
			StringRequest mys = new StringRequest(Method.POST,
					Commdata.get_phone_loaction_servies_addr, ml2, null);
			Commdata.VolleyQueue.add(mys);
			Commdata.VolleyQueue.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

package java.com.redpine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.util.Log;
import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.NameValuePair;
import ch.boye.httpclientandroidlib.client.HttpClient;
import ch.boye.httpclientandroidlib.client.entity.UrlEncodedFormEntity;
import ch.boye.httpclientandroidlib.client.methods.HttpPost;
import ch.boye.httpclientandroidlib.impl.client.DefaultHttpClient;
import ch.boye.httpclientandroidlib.message.BasicNameValuePair;

public class Redpine {
	
	public JSONObject Login(String username, String password){
		
		StringBuilder stringBuilder;
		JSONObject jsonResponse = null;
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://96.126.105.125:9000/login");
		
        try{
        	
        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
        		nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            nameValuePairs.add(new BasicNameValuePair("appcode", "1234567890"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            HttpResponse response = httpclient.execute(httppost);
            
            InputStream inputStream = response.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            stringBuilder = new StringBuilder();
            String bufferedStrChunk = null;
            
            while((bufferedStrChunk = bufferedReader.readLine()) != null){
                stringBuilder.append(bufferedStrChunk);
            }
            
            jsonResponse = new JSONObject(stringBuilder.toString());
            Log.i("Log", jsonResponse.toString());
          	Log.i("Log", "jsonResponse");
            Log.i("Log", "another json");              
            Log.i("Log", "another json");
        }
        catch(Exception e){
        	e.printStackTrace();
        	Log.e("Redpine", e.toString());
        }
        return jsonResponse;
	}
}

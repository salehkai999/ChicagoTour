package com.example.chicagotour.fences;

import android.location.Geocoder;
import android.util.Log;

import com.example.chicagotour.MapsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FencesDownloader implements Runnable {

    private static final String TAG = "FencesDownloader";
    private static final String URL = "http://www.christopherhield.com/data/WalkingTourContent.json";
    private Geocoder geocoder;
    private  FenceManager fenceManager;


    public FencesDownloader(MapsActivity mapsActivity, FenceManager fenceManager) {
        this.fenceManager = fenceManager;
        geocoder = new Geocoder(mapsActivity);
    }

    public FencesDownloader() {
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "run: Response code: " + connection.getResponseCode());
                return;
            }

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                //Log.d(TAG, "run: "+line);
            }

        //    Log.d(TAG, "run: "+buffer.toString());
            processData(buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processData(String data){
        if(data == null){
            return;
        }

        try{
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("fences");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                FenceData fData = new FenceData();
                fData.setId(obj.getString("id"));
                fData.setAddress(obj.getString("address"));
                fData.setLatitude(obj.getDouble("latitude"));
                fData.setLongitude(obj.getDouble("longitude"));
                fData.setRadius(Float.parseFloat(obj.getString("radius")));
                fData.setDescription(obj.getString("description"));
                fData.setImage(obj.getString("image"));
                fData.setColor(obj.getString("fenceColor"));
                Log.d(TAG, "processData: "+fData.toString());
            }
        }
        catch (Exception e){

        }

    }

}

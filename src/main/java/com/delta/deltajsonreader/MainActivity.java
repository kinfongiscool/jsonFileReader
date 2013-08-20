package com.delta.deltajsonreader;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends Activity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = getJSONFile();
        ArrayList<String> myDataArray = new ArrayList<String>();

        try {
//            JSONObject jsonObject = new JSONObject(s);
//
//            Set<String> keySet = jsonObject.keySet();
//
//            while(keys.hasNext()) {
//                String key = (String)keys.next();
//                if(key.contains("Text")) {
//                    String tempString = key.();
//                    if(tempString.contains("RT ")) {
//                        mOutput.setText()
//                    }
//                }
//            }

            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if ((jsonObject.getString("text")).contains("RT ")) {
                    myDataArray.add(jsonObject.getString("text"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //setup my listView

        mListView = (ListView) findViewById(R.id.mylistview);

        ArrayAdapter<String> stringAdapter =
                new ArrayAdapter<String>(getApplicationContext(), R.layout.list_row, myDataArray);

        if (mListView != null) {
            mListView.setAdapter(stringAdapter);
        }


    }


    public String getJSONFile(){

        String json = null;
        try {

            InputStream is = getResources().openRawResource(R.raw.samplejsontwitter);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
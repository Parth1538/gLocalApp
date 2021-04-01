package com.triillionaire.dhruv.glocals;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhruv on 10/12/2016.
 */

public class BGworker extends AsyncTask<Void,Train,Void> {

    Context context;
    Activity activity;
    private RecyclerView recyclerView;
    private Train_Adapter tAdapter;
    private ArrayList<Train> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    public BGworker(Context context) {
        this.context = context;
        activity=(Activity)context;
    }

    String json_string = "http://192.168.43.34/GLocals/traindetails.php";
    @Override
    protected void onPreExecute() {
        recyclerView = (RecyclerView)activity.findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        tAdapter = new Train_Adapter(arrayList,context.getApplicationContext());
        Log.e("hf",tAdapter.toString());
        recyclerView.setAdapter(tAdapter);



    }

    @Override
    protected Void doInBackground(Void... params) {

        try {


            URL url=new URL(json_string);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line=bufferedReader.readLine())!=null)
            {

                stringBuilder.append(line+"\n");


            }
            con.disconnect();
            String json_string=stringBuilder.toString().trim();
            //Log.d("fnsjk",json_string);

            JSONObject jsonObject=new JSONObject(json_string);
            JSONArray jsonArray=jsonObject.getJSONArray("server_res");
            int count=0;
            while (count<jsonArray.length())
            {
                JSONObject jo= jsonArray.getJSONObject(count);
                count++;

                Train train=new Train(jo.getString("TNo"),jo.getString("Tname"),jo.getString("TFrom"),jo.getString("TTo"),jo.getString("TATime"),jo.getString("TDTime"),jo.getString("TType"));
                publishProgress(train);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Train... values) {

        arrayList.add(values[0]);
        tAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

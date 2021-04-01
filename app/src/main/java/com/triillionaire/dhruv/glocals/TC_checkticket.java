package com.triillionaire.dhruv.glocals;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class TC_checkticket extends AppCompatActivity {

    TextView tname,src,dest,type,ticket,fare;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc_checkticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tname=(TextView)findViewById(R.id.tv_tname);
        src=(TextView)findViewById(R.id.tv_tsrc);
        dest=(TextView)findViewById(R.id.tv_tdest);
        type=(TextView)findViewById(R.id.tv_type);
        ticket=(TextView)findViewById(R.id.tv_not);
        fare=(TextView)findViewById(R.id.tv_fare);




        final EditText tno=(EditText)findViewById(R.id.etno);
        Button check=(Button)findViewById(R.id.btn_find);
         check.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String tnumber=tno.getText().toString();
                 bg bgobj = new bg();

                 bgobj.execute(tnumber);
                 Log.e("tno",tnumber);
             }
         });


    }

    String response ="";
    String result = "";
    private static final String TAG = "NIGGAAAH@!";

    public class bg extends AsyncTask<String,Void,String>
    {

        String a,b,cc,d,e,f;
        @Override
        protected String doInBackground(String... params) {
            try {
                String tno=params[0];

                URL url = new URL("http://192.168.43.34/GLocals/ticket_details.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                JSONObject jobject = new JSONObject();

                jobject.put("tno", tno);

                Log.e(TAG, jobject.toString());

                //a=jobject.optString("tno");

                //Log.e(TAG,a);


                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(jobject.toString());
                wr.flush();

                //display what returns the POST request


                StringBuilder sb = new StringBuilder();
                int HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"));
                    while ((response = br.readLine()) != null) {
                        sb.append(response + "\n");
                        result += response;

                        Log.e("Result",response);
                        return result;



                    }




                    br.close();
                    System.out.println("" + sb.toString());






                } else {
                    System.out.println(con.getResponseMessage());
                }
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }



                return null;

        }

        @Override
        protected void onPostExecute(String result1) {
            super.onPostExecute(result1);
            result1=result;

            /*JSONArray jarr= null;
            try {
                jarr = new JSONArray(result);
                JSONObject jobj=jarr.getJSONObject(0);
                a=jobj.getString("train_name");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            */
            try {
                JSONObject jobj=new JSONObject(result);
               // JSONObject response=jobj.getJSONObject("server_res");
                JSONArray jr=jobj.getJSONArray("server_res");
                for (int i=0;i<jr.length();i++)
                {
                    JSONObject jb1=jr.getJSONObject(i);
                    a=jb1.getString("train_name");
                    b=jb1.getString("source");
                    cc=jb1.getString("destination");
                    d=jb1.getString("t_type");
                   e=jb1.getString("no_of_ticket");
                    f=jb1.getString("fare");



                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            tname.setText(a);
            src.setText(b);
            dest.setText(cc);
            type.setText(d);
            ticket.setText(e);
            fare.setText(f);



            Log.e("strings",a+b+cc+d+e+f);


        }


    }


}

package com.triillionaire.dhruv.glocals;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_SIGNUP = 0;
    private final String serverUrl = "http://192.168.43.34/GLocals/login.php";
    //AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    EditText inpt_email,inpt_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        final TextView link= (TextView)findViewById(R.id.link_signup);
         inpt_email = (EditText)findViewById(R.id.et_Email);
         inpt_pass = (EditText) findViewById(R.id.et_Password);


        Button btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inpt_email.getText().toString();
                String password = inpt_pass.getText().toString();

                if(email.trim().length() > 0 && password.trim().length() > 0)
                {

                    if (email.equals("tc") && password.equals("tcp")){
                     Intent ii=new Intent(LoginActivity.this,TC_checkticket.class);
                        startActivity(ii);
                    }
                    else {
                        bg bgobj = new bg();
                        bgobj.execute(email, password);
                    }

                }

                else
                {
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    Toast.makeText(LoginActivity.this, "Login failed..",Toast.LENGTH_LONG).show();
                }

            }
        });



        link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }
    String response="";
    String result="";


    private static final String TAG = "NIGGAAAH@!";



    public class bg extends AsyncTask<String, Void, String> {




        @Override
        protected String doInBackground(String... params) {

            try {

                String email = params[0];
                String password = params[1];


                Log.e(TAG, email+password);

                URL url = new URL("http://192.168.43.34/GLocals/login.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                JSONObject jobject = new JSONObject();


                jobject.put("email", email);
                jobject.put("password", password);


                Log.e(TAG, jobject.toString());

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



            if(result1.equals("success")){
                inpt_email = (EditText)findViewById(R.id.et_Email);
                String emailforsesson=inpt_email.getText().toString();
                Log.e("emailfor",emailforsesson);
                session.createLoginSession(emailforsesson);
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);


            }
            else {

                Toast.makeText(LoginActivity.this,"Check Your Internet Connection",Toast.LENGTH_LONG).show();

            }





        }


    }




}

package com.triillionaire.dhruv.glocals;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by dhruv on 03-10-2016.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    String type = "";



    @Override
    protected String doInBackground(String... params) {
        type = params[0];

        if (type.equals("login")) {
            String name = params[1];
            String password = params[2];
            try {
                HttpHandler sh = new HttpHandler();
                sh.makeServiceCall("POST", "login", name, password);





            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
        }
        else if (type.equals("Register")) {
            String name = params[1];
            String email = params[2];
            String password = params[3];
            String cno = params[4];
            try {
                HttpHandler sh = new HttpHandler();
                sh.makeServiceCall("POST", "Register", name, email, password, cno);





            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
        }
        else if (type.equals("bookticket")) {
            String train_name = params[1];
            String source = params[2];
            String destination = params[3];
            String t_type = params[4];
            String no_of_ticket = params[5];
            try {
                HttpHandler sh = new HttpHandler();
                sh.makeServiceCall("POST", "bookticket", train_name, source, destination, t_type, no_of_ticket);





            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
        }
        return null;

    }
    @Override
    protected void onPostExecute(String result) {



        super.onPostExecute(result);



    }


}

package com.triillionaire.dhruv.glocals;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();
    String response = null;
    String result="";
    Context context;

    public Context getContext() {
        return context;
    }

    public HttpHandler() {
    }

    public String makeServiceCall(String... params) {
        String reqType = params[0];
        String reqUrl = params[1];



        if (reqType.equals("POST")) {
            if (reqUrl.equals("login")) {
                try {
                    String name = params[2];
                    String password = params[3];
                    URL url = new URL("http://192.168.1.37/GLocals/login.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "application/json");
                    con.setRequestMethod("POST");

                    JSONObject jobject = new JSONObject();

                    jobject.put("name", name);
                    jobject.put("password", password);


                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jobject.toString());
                    //Log.e("NIGGGGGGGGUGUUU!", jobject.toString()) ;
                    response=jobject.toString();
                    Log.e("NIGGGGGGGGUGUUU!", response) ;
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


                        }
                        br.close();
                        System.out.println("" + sb.toString());



                        return result;

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
            }
            else if (reqUrl.equals("Register")) {
                try {
                    String name = params[2];
                    String email = params[3];
                    String password = params[4];
                    String cno = params[5];
                    URL url = new URL("http://192.168.1.37/GLocals/register.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "application/json");
                    con.setRequestMethod("POST");

                    JSONObject jobject = new JSONObject();

                    jobject.put("name", name);
                    jobject.put("email", email);
                    jobject.put("password", password);
                    jobject.put("contact", cno);


                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jobject.toString());
                    //Log.e("NIGGGGGGGGUGUUU!", jobject.toString()) ;
                    response=jobject.toString();
                    Log.e("register!", response) ;
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


                        }
                        br.close();
                        System.out.println("" + sb.toString());



                        return result;

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
            }
            else if (reqUrl.equals("bookticket")) {
                try {
                    String train_name = params[2];
                    String source = params[3];
                    String destination = params[4];
                    String t_type = params[5];
                    String no_of_ticket = params[6];
                    URL url = new URL("http://192.168.1.37/GLocals/ticket_add.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "application/json");
                    con.setRequestMethod("POST");

                    JSONObject jobject = new JSONObject();

                    jobject.put("train_name", train_name);
                    jobject.put("source", source);
                    jobject.put("destination", destination);
                    jobject.put("t_type", t_type);
                    jobject.put("no_of_ticket", no_of_ticket);


                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jobject.toString());
                    //Log.e("NIGGGGGGGGUGUUU!", jobject.toString()) ;
                    response=jobject.toString();
                    Log.e("bookticket!", response) ;
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


                        }
                        br.close();
                        System.out.println("" + sb.toString());



                        return result;

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
            }



        }

        return response;
    }









    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
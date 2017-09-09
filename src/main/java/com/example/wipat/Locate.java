package com.example.wipat;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
/*
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;*/
import org.json.JSONArray;
import org.json.JSONException;

public class Locate extends Activity {

    String res;
    int c=0;
    int posflag=0;
    ArrayList<String> buildings;
    DatabaseHelper db;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<PositionData> positionsData;
    TextView result1;
    Button locate;
    TextView result2;
    TextView r;
    JSONArray jsonObject;
    int flag;
    public static final int CREDENTIALS_RESULT = 4342; //just make sure it's unique within your activity.


    String classid,lno,sub,topic,rollno;
    StringBuilder current_mac;
    String mac = "0";


    public void btn(View view) {

        Intent intent = new Intent(getApplicationContext(), Scan.class);

        intent.putExtra("isLearning", false);
        startActivityForResult(intent, 0);
    }

    public void checkCredentials() {
        KeyguardManager keyguardManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        Intent credentialsIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            credentialsIntent = keyguardManager.createConfirmDeviceCredentialIntent("Device Password required", "Please enter your device pattern or fingerprint to proceed...");
        }
        Log.d("cred",""+credentialsIntent);
        if (credentialsIntent != null) {
            startActivityForResult(credentialsIntent, CREDENTIALS_RESULT);
        } else {
            //no password needed
            c=1;
        }


    }

    public void onCreate(Bundle saveInstanceState) {
        checkCredentials();
        super.onCreate(saveInstanceState);
        setContentView(R.layout.locate);
        db = new DatabaseHelper();




        try{
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : all) {
                if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = networkInterface.getHardwareAddress();
                Log.d("macbytes",""+macBytes);
                if (macBytes == null) {
                    current_mac.append("00:00:00:00:00:00");
                }

                current_mac = new StringBuilder();
                for (byte b : macBytes) {
                    current_mac.append(String.format("%02X", b));
                }

                if (current_mac.length() > 0) {
                    current_mac.deleteCharAt(current_mac.length() - 1);
                }
            }

            /**************finish finding current mac*************/

        } catch (Exception e) {
            Log.d("Exception",e.toString());
            Toast.makeText(getApplicationContext(),"Exception: "+e,Toast.LENGTH_LONG).show();
        }

        Log.d("CurrentMac",""+current_mac);

        result1 = (TextView) findViewById(R.id.result);
        result2 = (TextView) findViewById(R.id.result2); ///////////////////////////////////
        r=(TextView)findViewById(R.id.textView3);
        r.setVisibility(View.INVISIBLE);

        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, buildings);


        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, buildings);
        // Set The Adapter


        /*********start volley***************/
        String ur="http://192.168.0.101/wipat/api.php";
        StringRequest post = new StringRequest(Request.Method.POST, ur,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonObject = new JSONArray(response);

                            mac = jsonObject.getJSONObject(0).getString("mac");

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        /*    Intent i = new Intent(Locate.this,StudentRegister.class);
                            startActivity(i);*/
                            //e.printStackTrace();
                        }


                        Log.d("macs",current_mac.toString()+" "+mac);
                        if(mac.equals(current_mac.toString()))
                            flag=1;
                        else
                            flag=0;
                        //Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                        Log.d("flag1st",""+flag);
                        Button btnPos=(Button)findViewById(R.id.btnPos);
                        if(flag==0)
                            btnPos.setEnabled(false);
                        else
                            btnPos.setEnabled(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "req 13 Your mobile device isn't registered. Please register via student registration."+error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }

        ) {

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();
                params.put("req","req13");
                params.put("current_mac",current_mac.toString());


                return params;


            }


        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(post);

        /************end volley***************/




        /*********start volley***************/
        String u="http://192.168.0.101/wipat/api.php";
        StringRequest pos = new StringRequest(Request.Method.POST, u,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonObject = new JSONArray(response);
                            classid = jsonObject.getJSONObject(0).getString("classid");
                            rollno= jsonObject.getJSONObject(0).getString("rollno");




                            //nested volley


                            /*********start volley***************/
                            String us = "http://192.168.0.101/wipat/api.php";
                            StringRequest posu = new StringRequest(Request.Method.POST, us,
                                    new Response.Listener<String>() {


                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray jsonObject = new JSONArray(response);
                                                lno = jsonObject.getJSONObject(0).getString("lno");
                                                sub = jsonObject.getJSONObject(0).getString("sub");
                                                topic = jsonObject.getJSONObject(0).getString("topic");
                                                Log.d("rishi", classid + " " + lno + " " + " " + sub + " " + topic);

                                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                            } catch (JSONException e) {
                                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(Locate.this,StartingScreen.class);
                                                startActivity(i);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            // error
                                            // Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                            Log.d("Error.Response", error.toString());
                                        }
                                    }

                            ) {

                                @Override
                                protected Map<String, String> getParams() {


                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("req", "req12");
                                    params.put("classid", classid);


                                    return params;


                                }


                            };
                            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(posu);

                            /************end volley***************/





                        } catch (JSONException e) {
                            e.printStackTrace();
                        } Log.d("response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "req 14 Your mobile device isn't registered. Please register via student registration."+error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }

        ) {

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();
                params.put("req","req14");
                params.put("current_mac",current_mac.toString());


                return params;


            }


        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(pos);

        /************end volley***************/



    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent intent) {
        c=0;
        if (requestCode == CREDENTIALS_RESULT) { if (resultCode == RESULT_OK) {c=1; Log.d("Success"," "+c);} else {  Toast.makeText(getApplicationContext(),"Password verification failed",Toast.LENGTH_LONG).show(); finish();}}
        else {
                // TODO Auto-generated method stub
                if (resultCode == RESULT_OK) {

                    r.setVisibility(View.VISIBLE);


                    final PositionData positionData = (PositionData) intent
                            .getSerializableExtra("PositionData");
                    Log.d("positions data: ", "" + positionData);
                    DatabaseHelper db = new DatabaseHelper();
                    db.deleteReading(positionData.getName(), getApplicationContext());


                    Log.d("flag", "" + flag);
                    if (flag == 1) {


                        String closestPosition = null;
                        final ArrayList<Router> result = new ArrayList<Router>();
                        int a = 5;
                        String url = "http://192.168.0.101/wipat/api.php";
                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {


                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1"))
                                            Toast.makeText(getApplicationContext(), "Friendly wifis aren't configured or readings aren't calibrated. Error. Please Calibrate via admin panel", Toast.LENGTH_LONG).show();
                                        else {

                                            try {
                                                int db1[] = new int[10];
                                                int db2[] = new int[10];
                                                HashMap<String, PositionData> positions = new HashMap<String, PositionData>();

                                                jsonObject = new JSONArray(response);
                                                for (int i = 0; i < jsonObject.length(); i++) {
                                                    result.add(new Router(jsonObject.getJSONObject(i).getString("ssid"), jsonObject.getJSONObject(i).getString("mac_id")));


                                                }
                                                int x = 0;
                                                String routt[] = new String[40];
                                                int mnn[] = new int[3];
                                                mnn[0] = 100;
                                                mnn[1] = 100;
                                                mnn[2] = 100;
                                                for (int i = 0; i < jsonObject.length(); i++) {
                                                    int myval = Integer.parseInt(jsonObject.getJSONObject(i).getString("rssi"));
                                                    String mymac = jsonObject.getJSONObject(i).getString("mac_id");


                                                    int min = 100;

                                                    for (final Map.Entry<String, Integer> e : positionData.getValues().entrySet()) {
                                                        final String s1 = e.getKey();// current mac
                                                        final int s2 = e.getValue(); //current rssi
                                                        Log.d("in loop", "name " + e.getKey() + " value " + e.getValue().toString()); //current rssi
                                                        //  db2[x++]=Integer.parseInt(e.getValue().toString());

                                                        Log.d("comparing", mymac + " with " + s1);


                                                        if (mymac.equals(s1)) {

                                                            Log.d("match found", mymac + "with value " + myval + " with " + s1 + " with value" + s2);

                                                            int sub = Math.abs(s2 - myval);

                                                            if (sub <= mnn[i / 2]) {
                                                                mnn[i / 2] = sub;
                                                                Log.d("min now is", mnn[i / 2] + "for" + jsonObject.getJSONObject(i).getString("position_id") + "for router " + mymac);

                                                                routt[i / 2] = jsonObject.getJSONObject(i).getString("position_id");

                                                            }
                                                        }
                                                        // db1[i]=Integer.parseInt(jsonObject.getJSONObject(i).getString("rssi"));

                                                    }
                                                    x++;

                                                }


                                                int f = 0, s = 0;

                                                //int k=0;
                                                Log.d("jsonob1st", "" + jsonObject.getJSONObject(0).getString("position_id"));
                                                for (int i = 0; i < 3; i++) {
                                                    Log.d("jsonob", "" + jsonObject.getJSONObject(0).getString("position_id"));
                                                    if (jsonObject.getJSONObject(0).getString("position_id").length() == 0)
                                                        Toast.makeText(getApplicationContext(), "One or more friendly wifis is out of range. Please check.", Toast.LENGTH_LONG).show();
                                                    else if (routt[i].equals(jsonObject.getJSONObject(0).getString("position_id"))) {
                                                        Log.d("finally", "Nearest point from router " + i + "is " + routt[i]);
                                                        //k = k + 2;
                                                        f++;
                                                    }
                                                    Log.d("jsonob2nd", "" + jsonObject.getJSONObject(1).getString("position_id"));
                                                    if (jsonObject.getJSONObject(1).getString("position_id").length() == 0)
                                                        Toast.makeText(getApplicationContext(), "One or more friendly wifis is out of range. Please check.", Toast.LENGTH_LONG).show();
                                                    else if (routt[i].equals(jsonObject.getJSONObject(1).getString("position_id"))) {
                                                        Log.d("jsonob2", "" + jsonObject.getJSONObject(1).getString("position_id"));
                                                        Log.d("finallyyyy", "Neasrest point from router " + i + "is " + routt[i]);
                                                        //k = k + 2;
                                                        s++;
                                                    }


                                                }

                                                if (f > s) {
                                                    res = jsonObject.getJSONObject(0).getString("position_id");
                                                    posflag = 1;
                                                } else {
                                                    res = jsonObject.getJSONObject(1).getString("position_id");
                                                    posflag = 0;
                                                }


                                                /*************nested volley request******************************/


                                                if (posflag == 1) {

                                                    /*********start volley***************/
                                                    String uuu = "http://192.168.0.101/wipat/api.php";
                                                    StringRequest posss = new StringRequest(Request.Method.POST, uuu,
                                                            new Response.Listener<String>() {


                                                                @Override
                                                                public void onResponse(String response) {
                                                                    Log.d("inReq8", "req8");
                                                                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    // error
                                                                    Toast.makeText(getApplicationContext(), "req 8 Your mobile device isn't registered. Please register via student registration. " + error.toString(), Toast.LENGTH_LONG).show();
                                                                    Log.d("Error.Response", error.toString());
                                                                }
                                                            }

                                                    ) {

                                                        @Override
                                                        protected Map<String, String> getParams() {


                                                            Map<String, String> params = new HashMap<String, String>();
                                                            params.put("req", "req8");

                                                            params.put("lecture_no", lno);
                                                            ;
                                                            params.put("Class_id", classid);
                                                            params.put("subj_id", sub);


                                                            return params;


                                                        }


                                                    };
                                                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(posss);

                                                    /************end volley***************/


                                                    /*********start volley***************/
                                                    String uu = "http://192.168.0.101/wipat/api.php";
                                                    StringRequest poss = new StringRequest(Request.Method.POST, uu,
                                                            new Response.Listener<String>() {

                                                                @Override
                                                                public void onResponse(String response) {
                                                                    if (response.equals("1"))
                                                                        Toast.makeText(getApplicationContext(), "Attendance cannot be marked more than once.", Toast.LENGTH_LONG).show();
                                                                    else {
                                                                        Log.d("inReq7", "req7");
                                                                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                                                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                                                                        builder.setSmallIcon(R.drawable.notify);
                                                                        builder.setContentTitle("WiPAt: Attendance marked!");
                                                                        builder.setContentText("Your attendance has been successfully marked!");
                                                                        builder.setAutoCancel(true);
                                                                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                                                        notificationManager.notify(2, builder.build());
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    // error
                                                                    Toast.makeText(getApplicationContext(), "req 7 Your mobile device isn't registered. Please register via student registration. " + error.toString(), Toast.LENGTH_LONG).show();
                                                                    Log.d("Error.Response", error.toString());
                                                                }
                                                            }

                                                    ) {

                                                        @Override
                                                        protected Map<String, String> getParams() {


                                                            Map<String, String> params = new HashMap<String, String>();
                                                            params.put("req", "req7");
                                                            params.put("student_id", rollno);
                                                            params.put("lecture_no", lno);
                                                            params.put("topic", topic);
                                                            params.put("attendance", "1");
                                                            params.put("Class_id", classid);
                                                            params.put("subj_id", sub);


                                                            return params;


                                                        }


                                                    };
                                                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(poss);

                                                    /************end volley***************/


                                                } else
                                                    Toast.makeText(Locate.this, "You are outside the class or too far away from the centre. Attendance not marked.", Toast.LENGTH_LONG).show();


                                                /***************end nested volley request******************/


                                                Log.d("Final result is: ", res);
                                                result1.setText("Your position is :  " + res);

                                                System.out.println("Reading done");
                                                positionsData = new ArrayList<PositionData>();
                                                for (Map.Entry<String, PositionData> e : positions.entrySet())
                                                    positionsData.add(e.getValue());


                                                Log.d("locating", result.toString());

                                                //Log.d("jsonobject",jsonObject.getJSONObject(1).getString("ssid"));

                                            } catch (JSONException e) {
                                                Toast.makeText(getApplicationContext(), "One or more friendly wifis is out of range. Please check.", Toast.LENGTH_LONG).show();

                                            }
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Toast.makeText(getApplicationContext(), "Enter correct IP address of server via admin login!!!", Toast.LENGTH_LONG).show();
                                        Log.d("Error.Response", error.toString());
                                    }
                                }

                        ) {

                            @Override
                            protected Map<String, String> getParams() {


                                Map<String, String> params = new HashMap<String, String>();
                                params.put("req", "req6"); //make r1 global
                                //	params.put("p", position_id);


                                return params;


                            }


                        };
                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);


                    } else
                        Toast.makeText(getApplicationContext(), "Your mobile device isn't registered. Please register via student registration.", Toast.LENGTH_LONG).show();

                }
                super.onActivityResult(requestCode, resultCode, intent);
            }



    }


    /***************************************************************************************************************/


}

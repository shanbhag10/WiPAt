package com.example.wipat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRegister extends AppCompatActivity {
    String classid,name,rollno;
    StringBuilder mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"BE-A", "BE-B", "BE-C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: classid="BE-A"; break;
                    case 1: classid="BE-B"; break;
                    case 2: classid="BE-C"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Select a choice!!!",Toast.LENGTH_LONG).show();
            }
        });


        Button btn = (Button) findViewById(R.id.submit);
        final EditText et = (EditText) findViewById(R.id.name);
        final EditText et2 = (EditText) findViewById(R.id.roll);





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentRegister.this , StartingScreen.class);

                name = et.getText().toString();
                rollno=et2.getText().toString();

                try{
                    List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                    for (NetworkInterface networkInterface : all) {
                        if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;

                        byte[] macBytes = networkInterface.getHardwareAddress();
                        if (macBytes == null) {
                            mac.append("00:00:00:00:00:00");
                        }

                        mac = new StringBuilder();
                        for (byte b : macBytes) {
                            mac.append(String.format("%02X", b));
                        }

                        if (mac.length() > 0) {
                            mac.deleteCharAt(mac.length() - 1);
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Exception: "+e,Toast.LENGTH_LONG).show();
                }




                /*********start volley***************/
                String url="http://192.168.0.101/wipat/api.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {


                            @Override
                            public void onResponse(String response) {

                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                //	Toast.makeText(getApplicationContext(), "Enter correct IP address of server via admin login!!!", Toast.LENGTH_LONG).show();
                                Log.d("Error.Response", error.toString());
                            }
                        }

                ) {

                    @Override
                    protected Map<String, String> getParams() {


                        Map<String, String> params = new HashMap<String, String>();
                        params.put("req", "req10"); //make r1 global
                        params.put("rollno",rollno);
                        params.put("name",name);
                        params.put("classid",classid);
                        params.put("mac",mac.toString());
                        //	params.put("p", position_id);



                        return params;


                    }


                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);

                /************end volley***************/


                startActivity(i);
            }
        });
    }




}

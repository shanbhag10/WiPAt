package com.example.wipat;

import android.preference.PreferenceManager;
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

import java.util.HashMap;
import java.util.Map;

public class TeacherDetails extends AppCompatActivity {

    String sub,lno,classid,topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        final EditText ln= (EditText) findViewById(R.id.lec_no);
        final Spinner classi= (Spinner) findViewById(R.id.classID);
        final EditText su= (EditText) findViewById(R.id.subject);
        final EditText top= (EditText) findViewById(R.id.Topic);
        Button btn=(Button)findViewById(R.id.button);
        Button btn2=(Button)findViewById(R.id.stop);

        String[] items = new String[]{"BE-A", "BE-B", "BE-C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        classi.setAdapter(adapter);



        classi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        PreferenceManager.getDefaultSharedPreferences(TeacherDetails.this).edit().putString("classid", classid).commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lno=ln.getText().toString();
                //classid is already set
                sub=su.getText().toString();
                topic=top.getText().toString();
                Log.d("values: ",lno+classid+sub);
                /*Intent intent = new Intent(TeacherDetails.this,Scan.class);
                intent.putExtra("lno",lno);
                intent.putExtra("classid",classid);
                intent.putExtra("sub",sub);*/


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
                        params.put("req", "req9"); //make r1 global
                        params.put("lno",lno);
                        params.put("classid",classid);
                        params.put("sub",sub);
                        params.put("topic",topic);
                        params.put("start","1");
                        //	params.put("p", position_id);



                        return params;


                    }


                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);

                /************end volley***************/

                                Log.d("values: ",lno+classid+sub);
                //startActivity(intent);
            }
        });






        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lno=ln.getText().toString();

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
                        params.put("req", "req11"); //make r1 global
                        params.put("lno",lno);
                        params.put("classid",classid);

                        return params;


                    }


                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);

                /************end volley***************/

                //startActivity(intent);
            }
        });

    }
}

package com.example.wipat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StartingScreen extends Activity {
    private Button learnButton;
    private Button locateButton;
    private Button syncButton;
    Intent intent;


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);

        ActivityCompat.requestPermissions(StartingScreen.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1) ;
        //Toast.makeText(getApplicationContext(), "You need to switch on location services in order for the app to work", Toast.LENGTH_LONG).show();


        String url="http://192.168.0.101/wipat/api.php";



        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {



//alert

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
                params.put("req", "req1"); //make r1 global
                params.put("p",1+"");



                return params;


            }


        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);




    }

    EditText et1,et2;

    Button btn,sub;


    public void onClick(View view) {

        et1=(EditText)findViewById(R.id.username);
        et2=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.submit);
        sub=(Button)findViewById(R.id.sub);
        Button button= (Button) view;


        switch (button.getId()) {
            case R.id.learn_button:
                button.setVisibility(View.INVISIBLE);


                et1.setVisibility(View.VISIBLE);
                et2.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et1.getText().toString().equals("admin") && et2.getText().toString().equals("12345")) {
                            intent = new Intent(StartingScreen.this, Positions.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Incorrect admin username or password",Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.locate_button:
                intent = new Intent(StartingScreen.this, Locate.class);
                startActivity(intent);
                break;
            case R.id.teacher:

                button.setVisibility(View.INVISIBLE);

                final EditText et3=(EditText)findViewById(R.id.user);
                final EditText et4=(EditText)findViewById(R.id.pass);
                et3.setVisibility(View.VISIBLE);
                et4.setVisibility(View.VISIBLE);
                sub.setVisibility(View.VISIBLE);


                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        /*********start volley***************/
                        String uuu = "http://192.168.0.101/wipat/api.php";
                        StringRequest posss = new StringRequest(Request.Method.POST, uuu,
                                new Response.Listener<String>() {


                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")) {
                                            Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_LONG).show();
                                            intent = new Intent(StartingScreen.this, TeacherDetails.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                        Log.d("Error.Response", error.toString());
                                    }
                                }

                        ) {

                            @Override
                            protected Map<String, String> getParams() {


                                Map<String, String> params = new HashMap<String, String>();
                                params.put("req", "req15");
                                params.put("user",et3.getText().toString());
                                params.put("pass",et4.getText().toString());

                                return params;
                            }


                        };
                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(posss);

                        /************end volley***************/


                    }
                });
                break;

            case R.id.register:
                intent = new Intent(StartingScreen.this, StudentRegister.class);
                startActivity(intent);
                break;

            default:
                break;


        }

    }

}

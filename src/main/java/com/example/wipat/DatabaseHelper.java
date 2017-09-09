package com.example.wipat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class DatabaseHelper {

	public StartingScreen start;

	public static final String DATABASE_NAME = "wifips.db";
	public static final String AP_TABLE = "access_points";
	public static final String READINGS_TABLE = "readings";
	public static final String AP_CREATE = "CREATE TABLE 'access_points' "
			+ "('building_id' TEXT NOT NULL ,'ssid' TEXT NOT NULL,'mac_id' TEXT NOT NULL )";
	public static final String READINGS_CREATE = "CREATE TABLE 'readings' ('building_id' TEXT NOT NULL , "
			+ "'position_id' TEXT NOT NULL ,"
			+ " 'ssid' TEXT NOT NULL , 'mac_id' TEXT NOT NULL , 'rssi' INTEGER NOT NULL )";

	private HashMap hp;



	/*public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(AP_CREATE);
		db.execSQL(READINGS_CREATE);
	}*/

	/*
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + AP_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + READINGS_CREATE);
		onCreate(db);
	}*/

	public int deleteReading( final String position_id, Context c) {

		String[] args = new String[] {  position_id };


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
					//	Toast.makeText(getApplicationContext(), "Enter correct IP address of server via admin login!!!", Toast.LENGTH_LONG).show();
						Log.d("Error.Response", error.toString());
					}
				}

		) {

			@Override
			protected Map<String, String> getParams() {


				Map<String, String> params = new HashMap<String, String>();
				params.put("req", "req1"); //make r1 global
				params.put("p", position_id);



				return params;


			}


		};
		MySingleton.getInstance(c.getApplicationContext()).addToRequestQueue(postRequest);


		return 0;

	}


	public boolean deleteBuilding(String building_id) {
	//	SQLiteDatabase db = getWritableDatabase();
		String[] args = new String[] { building_id };
	//	db.delete(AP_TABLE,"building_id=?",args);
	//	db.delete(READINGS_TABLE, "building_id=?", args);
		return true;

	}

	public ArrayList<String> getBuildings() {
	//	SQLiteDatabase db = getReadableDatabase();
	/*	Cursor cursor = db.rawQuery("select distinct building_id from "
				+ READINGS_TABLE, null);
		ArrayList<String> result = new ArrayList<String>();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			result.add(cursor.getString(0));
			cursor.moveToNext();
		}
		return result;*/
return null;
	}

	public ArrayList<Router> getFriendlyWifis(Context c) {



		final ArrayList<Router> result = new ArrayList<Router>();
		 int a=5;
		String url="http://192.168.0.101/wipat/api.php";
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {


					@Override
					public void onResponse(String response) {
Log.d("hiriye",response);
                        try {
                             JSONArray jsonObject = new JSONArray(response);
							for(int i=0;i<jsonObject.length();i++) {
								result.add(new Router(jsonObject.getJSONObject(i).getString("ssid"), jsonObject.getJSONObject(i).getString("mac_id")));
								Log.d("after adding",result.get(i).toString());
							}
							Log.d("result2",result.toString());

							//Log.d("jsonobject",jsonObject.getJSONObject(1).getString("ssid"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //result.add(new Router("",""));




//alert

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
				params.put("req", "req2"); //make r1 global
			//	params.put("p", position_id);



				return params;


			}


		};
		MySingleton.getInstance(c.getApplicationContext()).addToRequestQueue(postRequest);






	/*	ArrayList<Router> result = new ArrayList<Router>();
		System.out.println(building_id);
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select ssid,mac_id from " + AP_TABLE
				+ " where building_id=?", new String[] { building_id });
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			result.add(new Router(cursor.getString(0), cursor.getString(1)));
			cursor.moveToNext();
		}
		return result;*/

		Log.d("result1",result.toString());

	return result;


}

	public void deleteFriendlyWifis(String building_id) {
	/*	SQLiteDatabase db = getWritableDatabase();
		String[] args = new String[] { building_id };
		return db.delete(AP_TABLE, "building_id=?", args);
*/
	}

	public boolean addFriendlyWifis(final ArrayList<Router> wifis, Context c) {

		String url="http://192.168.0.101/wipat/api.php";
		for (int i = 0; i < wifis.size(); i++)
			{
				Log.d("seize wala",wifis.get(i).getSSID());
				final String ssid = wifis.get(i).getSSID();
				final String bssid = wifis.get(i).getBSSID();

				StringRequest postRequest = new StringRequest(Request.Method.POST, url,
						new Response.Listener<String>() {


							@Override
							public void onResponse(String response) {

							//	result.add(new Router("", ""));


//alert

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
						params.put("req", "req3"); //make r1 global
						params.put("ssid", ssid);
						params.put("mid",bssid);
						//	params.put("p", position_id);


						return params;


					}


				};
				MySingleton.getInstance(c.getApplicationContext()).addToRequestQueue(postRequest);


		/*deleteFriendlyWifis(building_id);
		SQLiteDatabase db = getWritableDatabase();
		for (int i = 0; i < wifis.size(); i++) {
			ContentValues cv = new ContentValues();
			cv.put("building_id", building_id);
			cv.put("ssid", wifis.get(i).getSSID());
			cv.put("mac_id", wifis.get(i).getBSSID());
			db.insert(AP_TABLE, null, cv);
		}

		System.out.println("Adding done");*/
			}
		return true;
	}

	public ArrayList<String> getPositions(Context c) {
		final ArrayList<String> result = new ArrayList<String>();


		String url="http://192.168.0.101/wipat/api.php";
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {


					@Override
					public void onResponse(String response) {
					Log.d("res",response);

						//result=response.
						//result.add("");

//alert

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
				params.put("req", "req4"); //make r1 global
			//	params.put("p", position_id);



				return params;


			}


		};
		MySingleton.getInstance(c.getApplicationContext()).addToRequestQueue(postRequest);

		/*SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select distinct position_id from "
				+ READINGS_TABLE + " where building_id=?",
				new String[] { building_id });*/

		/*cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			result.add(cursor.getString(0));
			cursor.moveToNext();
		}*/
		//return result;
		return result;
	}

	public boolean addReadings(PositionData positionData, Context c) {
		Log.v("Just Before db : ", positionData.toString());
		deleteReading(positionData.getName(),c.getApplicationContext());



		//SQLiteDatabase db = getWritableDatabase();
		for (final Map.Entry<String, Integer> e : positionData.getValues().entrySet()) {
			/*ContentValues cv = new ContentValues();
		//	cv.put("building_id", building_id);
			cv.put("position_id", positionData.getName());
			cv.put("ssid",positionData.routers.get(e.getKey()));
			cv.put("mac_id",e.getKey());
			cv.put("rssi", e.getValue());
			Log.v(e.getKey(), e.getValue().toString());
			//db.insert(READINGS_TABLE, null, cv);*/

			final String s1 = positionData.getName();
			final String s2 = positionData.routers.get(e.getKey());
			String url = "http://192.168.0.101/wipat/api.php";


			StringRequest postRequest = new StringRequest(Request.Method.POST, url,
					new Response.Listener<String>() {


						@Override
						public void onResponse(String response) {

							//	result.add(new Router("", ""));


//alert

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
					params.put("req", "req5"); //make r1 global

					params.put("pid", s1);
					params.put("ssid", s2);
					params.put("mid", e.getKey());
					params.put("rssi", e.getValue().toString());
					//	params.put("p", position_id);


					return params;


				}


			};
			MySingleton.getInstance(c.getApplicationContext()).addToRequestQueue(postRequest);

		}



		System.out.println("Adding done");
		return true;

	}

    /*public boolean updateDatabase(JSONArray buildings) throws JSONException {
        Gson gson=new Gson();

        for(int i=0;i<buildings.length();i++){
            JSONObject building=buildings.getJSONObject(i);
            String building_id=building.getString("building_id");

            ArrayList<PositionData> readings= null;
            ArrayList<Router> friendlyWifis=null;



            try {
               Log.d("Readings",building.get("readings").toString());

                readings = gson.fromJson(building.get("readings").toString(),new TypeToken<ArrayList<PositionData>>() {
                }.getType());
                friendlyWifis=gson.fromJson(building.get("friendly_wifis").toString()
                        ,new TypeToken<ArrayList<Router>>() {
                }.getType());
                deleteBuilding(building_id);
                for(int j=0;j<readings.size();j++){
                    addReadings(building.getString("building_id"),readings.get(j));
                }
                addFriendlyWifis(building.getString("building_id"),friendlyWifis);

            } catch (JSONException e) {
                return false;
            }



        }
        return true;

    }*/


	public ArrayList<PositionData> getReadings(Context c) {
		HashMap<String, PositionData> positions = new HashMap<String, PositionData>();
	//	SQLiteDatabase db = getReadableDatabase();
		/*Cursor cursor = db.rawQuery("select distinct * from " + READINGS_TABLE
				+ " where building_id='" + building_id + "'", null);
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			String position_id = cursor.getString(1);
			Router router = new Router(cursor.getString(2), cursor.getString(3));
			Log.v(cursor.getString(2), cursor.getInt(4) + "");
			if (positions.containsKey(position_id)) {

				positions.get(position_id).addValue(router, cursor.getInt(4));
			} else {
				PositionData positionData = new PositionData(
						cursor.getString(1));
				positionData.addValue(router, cursor.getInt(4));
				positions.put(position_id, positionData);
			}
			cursor.moveToNext();

		}
		System.out.println("Reading done");
		ArrayList<PositionData> result = new ArrayList<PositionData>();
		for (Map.Entry<String, PositionData> e : positions.entrySet())
			result.add(e.getValue());*/
		return null;

	}


}

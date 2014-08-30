package com.example.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/**********************************************************************************
//이 Activity는 수정 저장후 require API xx 오류가 뜨는데 Project메뉴 - Clean 한번 해주면 정상으로 돌아옴
**********************************************************************************/
public class MapActivity extends ActionBarActivity {
    phpDown task;
	PictureGet picture;
    GoogleMap map;
    
	private ArrayList<Roomdata> roomDataList;
	private Roomdata roomData;
	
	LatLng location;
    LatLng latlng;
    String lat;
    String lng;
    Context mcontext;
    
    ArrayList<String> LanguageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapactivity);
        mcontext = this;
        
        roomDataList = 	new ArrayList<Roomdata>();
        
        //picture = new PictureGet("http://14.63.219.212/sj/images/");
		//picture.getRemoteImage("oneroom");
		
        
        //초기 이동할 Lat, Lng 좌표.. 수정해야함
        double bundle_lat = 36.632603;
        double bundle_lng = 127.453067;
        
        
        //Thread 돌려서 DB 읽어 오는 부분(DB -> PHP -> json)
        task = new phpDown();
        task.execute("http://14.63.219.212/sj/jsonget.php");
             
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        location = new LatLng(bundle_lat,bundle_lng);
        
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16));
	
        //infowindow(마커 클릭할때 열리는 창)
        map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        //infowindow click listener
        map.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
             Intent intentSubActivity = 	new Intent(getBaseContext(), RoomActivity.class);
           	 intentSubActivity.putExtra("parcel", roomDataList.get(Integer.parseInt(arg0.getTitle())-1));
          	 intentSubActivity.putExtra("image", roomDataList.get(Integer.parseInt(arg0.getTitle())-1).pg.getImage());
		      startActivity(intentSubActivity);
			}
        });
    	
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        //초기 뜨는 팝업창 구현
 
		LanguageList = new ArrayList<String>();
		LanguageList.add("한국어");
		LanguageList.add("English");
		LanguageList.add("中國語");
		LanguageList.add("일본어");
        
		AlertDialog.Builder chooseDlg = new AlertDialog.Builder(MapActivity.this);
		chooseDlg.setTitle("위치선택");
		ArrayAdapter<String> arrayAdt = new ArrayAdapter<String>(MapActivity.this,android.R.layout.select_dialog_item,LanguageList);
		
		chooseDlg.setAdapter(arrayAdt, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(which==0)
					Toast.makeText(mcontext, "한국어선택", Toast.LENGTH_SHORT).show();
				else if(which==1)
					Toast.makeText(mcontext, "영어선택", Toast.LENGTH_SHORT).show();
				else if(which==2)
					Toast.makeText(mcontext, "중국어선택", Toast.LENGTH_SHORT).show();
				else if(which==3)
					Toast.makeText(mcontext, "일본어선택", Toast.LENGTH_SHORT).show();
				}
		});
		chooseDlg.setCancelable(true);
		chooseDlg.show();
		//팝업창구현끝
    }
    private class CustomInfoWindowAdapter implements InfoWindowAdapter{
    	private View view;
    	public CustomInfoWindowAdapter(){
    		view = getLayoutInflater().inflate(R.layout.infowindowactivity,null);
    	}
		@Override
		public View getInfoContents(Marker arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public View getInfoWindow(Marker marker) {
		    //roomData중 어떤건지 검색
			Roomdata rdt = new Roomdata();
			final String marker_index = marker.getTitle();
            
			for(int i=0;i<roomDataList.size();i++){
	            rdt= roomDataList.get(i);	
	            if(marker_index.equals(rdt.getIndex()))
	            	break;
            }
           
			final ImageView image = ((ImageView) view.findViewById(R.id.badge));	 
	    	image.setImageBitmap(rdt.pg.getImage());
            
            final TextView titleUi = ((TextView) view.findViewById(R.id.title));
            final TextView moneyUi = ((TextView) view.findViewById(R.id.money));
               
            titleUi.setText(rdt.getName());
            moneyUi.setText(rdt.getPrice());     
            return view;
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    public class phpDown extends AsyncTask<String, Integer,String>{
   	 @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                      // 연결 url 설정
                      URL url = new URL(urls[0]);
                      // 커넥션 객체 생성
                      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                      // 연결되었으면.
                      if(conn != null){
                         conn.setConnectTimeout(10000);
                         conn.setUseCaches(false);
                         // 연결되었음 코드가 리턴되면.
                         if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                            for(;;){
                                // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.  
                                String line = br.readLine();
                                if(line == null) break;
                                // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                                jsonHtml.append(line + "\n");
                             }
                          br.close();
                       }
                        conn.disconnect();
                     }
                   } catch(Exception ex){
                      ex.printStackTrace();
                   }
                   return jsonHtml.toString();
            
        }
        
        protected void onPostExecute(String str){
            try{
            	JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                int length=ja.length();
                for(int i=0; i<length;i++){
   	                JSONObject jo = ja.getJSONObject(i);
   	                roomData = new Roomdata(jo.getString("index"),jo.getString("price"),jo.getString("name"),jo.getString("phone"),jo.getString("location"),jo.getString("option"),1,2,jo.getString("imgname"));	                
   	                roomDataList.add(roomData);
   	                lat = jo.getString("lat");
   	                lng = jo.getString("lng");
   	                latlng = new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
   	                map.addMarker(new MarkerOptions().title(jo.getString("index")).position(latlng));
                }
                Roomdata rdt = new Roomdata();
        		for(int i=0;i<roomDataList.size();i++){
        		    rdt= roomDataList.get(i);	
        			rdt.pg = new PictureGet("http://14.63.219.212/sj/images/");
        			rdt.pg.getRemoteImage(rdt.getImgname());
        		}
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
   }

}

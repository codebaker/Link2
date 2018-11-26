package com.womenproiot.www.link;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    //상수정의
    static final int REQUEST_CODE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로딩화면 실행
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        //플로딩 추가버튼
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MapFragment mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Intent intent  =null;
        switch (item.getItemId()) {
            case R.id.meetup_reg :
                intent = new Intent(this,MeetupRegActivity.class);
                break;
            case R.id.location_reg :
                intent = new Intent(this,LocationRegActivity.class);
                break;
            case R.id.attendees :
                intent = new Intent(this,AttendeesActivity.class);
                break;
            case R.id.search_places :
                intent = new Intent(this,SearchPlacesActivity.class);
                break;
            case R.id.result_places :
                intent = new Intent(this,ResultPlacesActivity.class);
                break;
            default:return false;
        }
        startActivityForResult(intent,REQUEST_CODE);
        return true;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setSubIcon(LocationOverlay.DEFAULT_SUB_ICON_ARROW);
        locationOverlay.setCircleOutlineWidth(0);
        locationOverlay.setVisible(true);

    }
}

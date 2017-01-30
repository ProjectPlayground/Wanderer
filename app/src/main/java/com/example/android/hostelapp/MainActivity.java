package com.example.android.hostelapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{
    ViewPager mViewPager;
    Timer timer;
    int position =0;
    int amenitiesPosition = 0;
    int roomPosition = 0;
    int locationPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        /** Setting the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));
        pageSwitcher(3);

        /** Setting the Button that's going to show the Order Activity when clicked*/
        Button next = (Button) findViewById(R.id.booknow);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), booknow.class);
                startActivityForResult(myIntent, 0);
            }
        } );
    }

    /** Defining a FragmentPagerAdapter class for controlling the fragments to be shown when user swipes on the screen. */
    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new SampleFragment1();
            } else if (position == 1) {
                return new SampleFragment2();
            } else if (position == 2) {
                return new SampleFragment3();
            }
                else return new SampleFragment4();
            }

            @Override
            public int getCount() {
                // Show 4 total pages.
                return 4;
            }
        }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay in milliseconds
    }

    // this is an inner class...
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a separate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (position > 3) { // In my case the number of pages are 4
                        timer.cancel();
                    } else {
                        mViewPager.setCurrentItem(position++);
                    }
                }
            });

        }
    }

    /** Defining whether or not the info box under the "Amenities" Button should be shown. */
    public void onClickAmenities (View view) {
        amenitiesPosition = amenitiesPosition + 1;
        TextView amenities = (TextView) findViewById(R.id.amenities);
        amenities.setText(getString(R.string.amenitiesText));

        if (amenitiesPosition == 1) {
            amenities.setVisibility(View.VISIBLE);
            amenitiesPosition = -1;
        } else {
            amenities.setVisibility(View.GONE);
        }
    }

    /** Defining whether or not the info box under the "Room" Button should be shown. */
    public void onClickRoom (View view) {
        roomPosition = roomPosition + 1;
        TextView room = (TextView) findViewById(R.id.room);
        room.setText(getString(R.string.roomText));

        if (roomPosition == 1) {
            room.setVisibility(View.VISIBLE);
            roomPosition = -1;
        } else {
            room.setVisibility(View.GONE);
        }
    }

    /** Defining whether or not the info box under the "Location" Button should be shown. */
    public void onClickLocation (View view) {
        locationPosition = locationPosition + 1;
        TextView location = (TextView) findViewById(R.id.location);
        ImageView map = (ImageView) findViewById(R.id.map);
        location.setText(getString(R.string.locationText));

        if (locationPosition == 1) {
            location.setVisibility(View.VISIBLE);
            map.setVisibility(View.VISIBLE);
            locationPosition = -1;
        } else {
            location.setVisibility(View.GONE);
            map.setVisibility(View.GONE);
        }
    }

    /**Opens the Book Now section on BookNow Button Click */
    public void onClickBook(View view) {
        new booknow();
    }



    /** Intents in the Contact Us section */
    public void onClickMap (View view) {
        String mapPosition = "No. 1, Section 2, Xinsheng South Road, Da’an District, Taipei City, Taïwan 106";
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse("geo:0,0?q="+ mapPosition));
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public void onClickPhone (View view) {
        String phoneNumber = "+(00) 123456789";
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (phoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(phoneIntent);
        }
    }

    public void onClickMail (View view){
        String emailAddress= getString(R.string.email);
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:" + emailAddress)); // only email apps should handle this
        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mailIntent);
        }
    }

}

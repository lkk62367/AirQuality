package will.tw.airquality;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import will.tw.airquality.fragment.AirFragment;
import will.tw.airquality.fragment.UvFragment;
import will.tw.airquality.uv.api.UvApi;
import will.tw.airquality.uv.model.Record;
import will.tw.airquality.uv.model.UvReport;


public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    private String uvsitename;

    public static ArrayList<Record> mUVReport;

    private static final int FRG_AIR_POS = 0;
    private static final int FRG_UV_POS = 1;
    private static final int FRG_WEATHER_POS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        uvsysus("{County:"+SplashActivity.Addresscode+"}");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    public class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
//            location = new location(activity);
//            cityname = location.testLocationProvider();
            Log.e("WOWOWOOWOWOWO", AirService.cityname);
//            StationSys("County eq \'"+AirService.cityname+"\'");
            return null;
        }

        protected void onPreExecute(){
            // in main thread
        }


        protected void onProgressUpdate(Void... progress){
            // in main thread
        }

        protected void onPostExecute(Void result){
            // in main thread
        }

        protected void onCancelled(Void result){
            // in main thread
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
//        new MyTask().execute(null, null, null);


    }

    public void notifyFrgamentDataChanged() {
        Fragment airfrg = mSectionsPagerAdapter.getActiveFragment(mViewPager, FRG_AIR_POS);
        if (airfrg instanceof AirFragment) {
            AirFragment airfr = (AirFragment) airfrg;
            airfr.updateData();
            Log.d("William", "AirFragment.updateData()!");
        }

        Fragment uvfrg = mSectionsPagerAdapter.getActiveFragment(mViewPager, FRG_UV_POS);
        if (uvfrg instanceof UvFragment) {
            UvFragment uvfr = (UvFragment) uvfrg;
            uvfr.updateData();
            Log.d("William", "UVFragment.updateData()!");
        }
//
//        Fragment accountfrg = mSectionsPagerAdapter.getActiveFragment(mViewPager, FRG_ACCOUNT_POS);
//        if (accountfrg instanceof AccountFragment) {
//            AccountFragment fr = (AccountFragment) accountfrg;
//            fr.updateData();
//            Log.d(TAG, "AccountFragment.updateData()!");
//        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case FRG_AIR_POS:
                    return AirFragment.newInstance(FRG_AIR_POS, "AirQuality");
                case FRG_UV_POS:
                    return UvFragment.newInstance(FRG_UV_POS, "UVQuality");
//                case FRG_WEATHER_POS:
//                    return
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }


//            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

        public Fragment getActiveFragment(ViewPager mViewPager, int frgAirPos) {
            String name = makeFragmentName(mViewPager.getId(), frgAirPos);
            return getSupportFragmentManager().findFragmentByTag(name);
        }
        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    private class UVSubscriber extends Subscriber<UvReport> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("onRrror",e.toString());
            Log.e("onErroor", "UvSubscriber Error");

        }


        @Override
        public void onNext(UvReport uvReport) {
            ArrayList<Record> uvreports = uvReport.getResult().getRecords();
            String strlat;
            String strlon;
            Double lat;
            Double lon;
            Double nowlat = SplashActivity.lat;
            Double nowlon = SplashActivity.lon;
            Double mindisten = 99999999999999.9;
            for (int i= 0; i<uvreports.size(); i ++){
                strlat = uvreports.get(i).getWGS84Lat().replace(",","").replace(".","");
                strlon = uvreports.get(i).getWGS84Lon().replace(",","").replace(".","");
                lat = Double.valueOf(strlat.substring(0,2)+"."+strlat.substring(2,strlat.length()));
                lon = Double.valueOf(strlon.substring(0,3)+"."+strlon.substring(3,strlon.length()));
                Double sum = (nowlat - lat)*(nowlat - lat)+(nowlon - lon)*(nowlon - lon);
                Double distence = Math.sqrt(sum);
                if (distence<mindisten){
                    uvsitename = uvreports.get(i).getSiteName();
                    mindisten = distence;
                }
                mindisten = Math.min(distence, mindisten);
            }
            Log.e("William UV", uvsitename);

            uvsitenamesysus("{SiteName:"+uvsitename+"}");


        }
    }

    public void uvsysus(String type) {
        final Scheduler newThread = Schedulers.newThread();
        final Scheduler mainThread = AndroidSchedulers.mainThread();
        UVSubscriber subscriber = new UVSubscriber();
        UvApi.findReportByCity(type)
                .subscribeOn(newThread)
                .observeOn(mainThread)
                .subscribe(subscriber);
    }

    private class UVSiteSubscriber extends Subscriber<UvReport> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("onRrror",e.toString());
            Log.e("onErroor", "UvStationSubscriber Error");

        }


        @Override
        public void onNext(UvReport uvReport) {
            ArrayList<Record> uvstationreports = uvReport.getResult().getRecords();
            mUVReport = uvstationreports;
            notifyFrgamentDataChanged();
            Log.e("UVSiteName Finish", mUVReport.get(0).getSiteName());
        }
    }

    public void uvsitenamesysus(String type) {
        final Scheduler newThread = Schedulers.newThread();
        final Scheduler mainThread = AndroidSchedulers.mainThread();
        UVSiteSubscriber subscriber = new UVSiteSubscriber();
        UvApi.findReportByCity(type)
                .subscribeOn(newThread)
                .observeOn(mainThread)
                .subscribe(subscriber);
    }



}

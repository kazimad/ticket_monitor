package com.example.ticket_monitor.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static final String mylog = "mylog";
    public static final int BLUE = Color.BLUE;
    public NetworkInfo networkInfo;
    static TextView numberWindow, numberTicket, window1, window2, window3, window4, window5, ticket1, ticket2, ticket3, ticket4, ticket5, backgroundColor, prepare, prepare1, prepare2, prepare3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberWindow = (TextView) findViewById(R.id.dont_edit_window);
        numberTicket = (TextView) findViewById(R.id.dont_edit_operator);
        window1 = (TextView) findViewById(R.id.window1);
        window2 = (TextView) findViewById(R.id.window2);
        window3 = (TextView) findViewById(R.id.window3);
        window4 = (TextView) findViewById(R.id.window4);
        window5 = (TextView) findViewById(R.id.window5);

        ticket1 = (TextView) findViewById(R.id.ticketw1);
        ticket2 = (TextView) findViewById(R.id.ticketw2);
        ticket3 = (TextView) findViewById(R.id.ticketw3);
        ticket4 = (TextView) findViewById(R.id.ticketw4);
        ticket5 = (TextView) findViewById(R.id.ticketw5);

        backgroundColor = (TextView) findViewById(R.id.backgroundcolor);
        prepare = (TextView) findViewById(R.id.dont_edit_prepare);
        prepare1 = (TextView) findViewById(R.id.prepare1);
        prepare2 = (TextView) findViewById(R.id.prepare2);
        prepare3 = (TextView) findViewById(R.id.prepare3);

        backgroundColor.setBackgroundColor(BLUE);
        isOnline();
        monitorRefresh();
        try {

            while (isOnline() == true) {
                Thread.sleep(5000);
                monitorRefresh();
                Log.d(mylog, " getMonitorViewAsyncTask.execute();");
            }
            if (isOnline()==false) {
                Log.d(mylog, "No connection, end of work");
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            int networkType = networkInfo.getType();
            if ((networkType == connectivityManager.TYPE_WIFI) || networkType == connectivityManager.TYPE_ETHERNET) {
                Log.d(mylog, "Network is");
            }
        } catch (NullPointerException e) {
            Log.d(mylog, "networkInfo.isNOTConnected()");
        }
        return true;
    }
    private void monitorRefresh() {
        GetMonitorViewAsyncTask getMonitorViewAsyncTask = new GetMonitorViewAsyncTask();
        getMonitorViewAsyncTask.execute();
    }

}

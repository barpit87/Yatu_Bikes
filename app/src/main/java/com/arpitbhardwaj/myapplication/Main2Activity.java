package com.arpitbhardwaj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    public void onClickSummary(View view)
    {
        Intent intent=new Intent(this.getApplicationContext(),SummaryScreen.class);
        TextView order=(TextView) view;
        String orderString = order.getText().toString();
        switch (orderString ) {
            case "ROYAL ENFIELD":

                ImageView imageView=(ImageView)findViewById(R.id.imageView2) ;
                actionAfterSelectionForRightMovement(intent,imageView,orderString);
                break;

            case "PULSAR":

                ImageView pulsarImage=(ImageView)findViewById(R.id.imageView3) ;
                actionAfterSelectionForRightMovement(intent,pulsarImage,orderString);


                break;
            case "ACTIVA":

                ImageView activaImage=(ImageView)findViewById(R.id.imageView4) ;

                actionAfterSelectionForLeftMovement(intent,activaImage,orderString);
                break;
            case "AVIATOR":

                ImageView aviatorImage=(ImageView)findViewById(R.id.imageView5) ;
                actionAfterSelectionForLeftMovement(intent,aviatorImage,orderString);
                break;
            case "AVENGER":

                ImageView avengerImage=(ImageView)findViewById(R.id.imageView6) ;
                actionAfterSelectionForRightMovement(intent,avengerImage,orderString);
                break;
            case "DUKE":

                ImageView dukeImage=(ImageView)findViewById(R.id.imageView7) ;
                actionAfterSelectionForRightMovement(intent,dukeImage,orderString);
                break;
            case "FZ 250":

                ImageView fzImage=(ImageView)findViewById(R.id.imageView8) ;
                actionAfterSelectionForRightMovement(intent,fzImage,orderString);
                break;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*Below 3 statements are to set back button in action bar*/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if(getSupportFragmentManager().getBackStackEntryCount()>0)
                    getSupportFragmentManager().popBackStack();
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void actionAfterSelectionForRightMovement(final Intent intent, final ImageView imageView, final String orderString){

        imageView.animate().translationXBy(1000).setDuration(1000);
    /*intent.putExtra("Order", "ROYAL ENFIELD");
    startActivity(intent);*/
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        intent.putExtra("Order", orderString);
                        intent.putExtra("Movement", "right");
                        startActivity(intent);
                        imageView.animate().translationXBy(-1000).setDuration(2000);

                    }
                });
            }
        }, 1000);
        // imageView.animate().translationXBy(-1000).setDuration(2000);

    }



    void actionAfterSelectionForLeftMovement(final Intent intent, final ImageView imageView, final String orderString){

        imageView.animate().translationXBy(-1000).setDuration(1000);

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        intent.putExtra("Order", orderString);
                        intent.putExtra("Movement", "left");
                        startActivity(intent);
                        imageView.animate().translationXBy(1000).setDuration(2000);

                    }
                });
            }
        }, 1000);
        // imageView.animate().translationXBy(-1000).setDuration(2000);

    }
}


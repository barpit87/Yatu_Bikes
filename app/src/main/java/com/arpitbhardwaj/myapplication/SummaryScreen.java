package com.arpitbhardwaj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_screen);
        Intent intent=getIntent();
        String Order=intent.getStringExtra("Order");
        String movement=intent.getStringExtra("Movement");
        ImageView summaryImage=(ImageView)findViewById(R.id.SummaryImage);


            /*Below 3 statements are to set back button in action bar*/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this.getApplicationContext(),intent.getStringExtra("Order"),Toast.LENGTH_SHORT).show();

        switch (Order) {
            case "ROYAL ENFIELD":

                setRates(20,10);

                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.royal_enfield);
                summaryImage.animate().translationXBy(1000f).setDuration(3000);

                break;

            case "PULSAR":
                setRates(15,10);

                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.pulsar);

                break;

            case "ACTIVA":

                setRates(10,7);
                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.activa);

                break;
            case "AVIATOR":

                setRates(10,7);

                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.white_aviator);

                break;
            case "AVENGER":

                setRates(15,10);
                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.aviator);

                break;
            case "DUKE":

                setRates(14,10);
                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.duke);

                break;
            case "FZ 250":

                setRates(14,10);
                summaryImage = (ImageView) findViewById(R.id.SummaryImage);
                summaryImage.setImageResource(R.drawable.fz_250);

                break;
        }

    }


    void setRates(int perHr,int perKm )

    { TextView ratesPerHour=(TextView)findViewById(R.id.ratesPerHour);
        TextView ratesPerKm=(TextView)findViewById(R.id.ratesPerKm);
        String perHrString=String.valueOf(perHr);
        String perKmString=String.valueOf(perKm);
        ratesPerHour.setText(perHrString + " Rs / Hour");
        ratesPerKm.setText(perKmString + " Rs / Km");
    }

    void setImage()
    {

    }

    /* This fuction is for back button (arrow) in action bar*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

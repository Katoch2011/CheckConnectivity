package com.example.priya.checkconnectivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternetConenction();


    }

    private boolean checkInternetConenction() {

        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Oops! Internet not available.");
            builder.setMessage("Connect to Internet and Restart App.");
            builder.setCancelable(true);
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory( Intent.CATEGORY_HOME );
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        return false;
    }
}

package com.example.roger.overuse_avoid;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class BackgroundService extends Service {
    Timer timer=null;
    boolean over =true;
    int sec=0;
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onStart(Intent intent,int startId)
    {
        Toast toast=Toast.makeText(BackgroundService.this,"start",Toast.LENGTH_SHORT);
        toast.show();
        timer=new Timer();
        timer.schedule(timerTask,0,1000);
        super.onStart(intent, startId);
    }
    public void onDestroy() {
        over=false;
        Message message = new Message();
        message.what=0;
        handler.sendMessage(message);
        timer.cancel();
        super.onDestroy();
    }
    private Handler handler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    /*Toast toast1=Toast.makeText(BackgroundService.this,"Overuse",Toast.LENGTH_LONG);
                    toast1.show();*/

                    AlertDialog.Builder builder=new AlertDialog.Builder(BackgroundService.this)
                            .setIcon(R.drawable.exercise)
                            .setTitle("Time to Exercise!")
                            .setMessage("Want to do some exercise?")
                            .setPositiveButton("Sure!", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setClass(BackgroundService.this, Exercise.class);
                                            startActivity(intent);
                                        }
                                    })
                            .setNegativeButton("Not now~", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast toast = Toast.makeText(BackgroundService.this, "Delayed for 15 min...", Toast.LENGTH_LONG);
                                            toast.show();
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    alertDialog.show();
                    break;
                case 0:
                    Toast toast2=Toast.makeText(BackgroundService.this,"stop",Toast.LENGTH_SHORT);
                    toast2.show();
                    break;
            }
        }
    };
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            sec++;
            if(sec>=15)
            {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                sec=0;
            }
        }
    };
}

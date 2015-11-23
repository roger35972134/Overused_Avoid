package com.example.roger.overuse_avoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button stopButten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.start);
        stopButten=(Button)findViewById(R.id.stop);

        startButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent=new Intent(MainActivity.this,BackgroundService.class);
                startService(intent);
            }
        });
        stopButten.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,BackgroundService.class);
                stopService(intent);
            }
        });
    }
}

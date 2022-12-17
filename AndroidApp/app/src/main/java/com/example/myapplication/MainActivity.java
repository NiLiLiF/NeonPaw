package com.example.myapplication;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {
    //"84:CC:A8:5E:70:F2"
    //Экземпляры классов наших кнопок
    ToggleButton redButton;
    ToggleButton greenButton;
    Button button2;
    SeekBar seekBar;
    SeekBar seekBar2;
    SeekBar seekBar3;
    SeekBar seekBar4;
    SeekBar seekBar5;
    SeekBar seekBar6;
    SeekBar seekBar7;
    SeekBar seekBar8;
    ImageView statusIMG;
    EditText adressBT;
    ProgressBar progressBar;
    //Сокет, с помощью которого мы будем отправлять данные на Arduino


    //Эта функция запускается автоматически при запуске приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //"Соединям" вид кнопки в окне приложения с реализацией
        button2 = (Button)findViewById(R.id.button2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar) findViewById(R.id.seekBar7);
        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
        statusIMG = (ImageView) findViewById(R.id.statusIMG);
        adressBT = (EditText)findViewById(R.id.adressBT);
        //Добавлем "слушатель нажатий" к кнопке
        //redButton.setOnClickListener(this);
        //reenButton.setOnClickListener(this);

        bindViews();
        try {
            if(SendToESP32S.clientSocket == null) {
                SendToESP32S.initConnect(this, String.valueOf(adressBT.getText()));
                if(SendToESP32S.clientSocket.isConnected()) {
                    Toast.makeText(getBaseContext(),  "Connected", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(),  "Something wrong!", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e){
            Toast.makeText(getBaseContext(),  "Bluethooth exception", Toast.LENGTH_LONG).show();
        }


        progressBar.setVisibility(View.INVISIBLE);
    }

    private void bindViews() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('A', "" + (180 - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('B', "" + (180 - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('C', "" + (progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('D', "" + (progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('E', "" + (180 - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('F', "" + (180 - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('G', "" + (progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendBT('H', "" + (progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Touch SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "let go SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendBT(char c, String s){
        SendToESP32S.send(c, s);
    }




    @Override
    public void onClick(View v) {
            if (v == button2) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intentMain = new Intent(MainActivity.this ,
                        MainActivity2.class);
                MainActivity.this.startActivity(intentMain);
                progressBar.setVisibility(View.INVISIBLE);
            } else if (v == statusIMG) {
                progressBar.setVisibility(View.VISIBLE);
                SendToESP32S.Connect(String.valueOf(adressBT.getText()));
                if(SendToESP32S.clientSocket.isConnected()) {
                    Toast.makeText(getBaseContext(),  "Connected", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(),  "Something wrong!", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
    }


}
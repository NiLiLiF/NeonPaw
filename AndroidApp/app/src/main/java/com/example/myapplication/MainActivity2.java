package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private boolean EditMode = false;
    private int lastFuncButtonID;

    Button buttonR;
    Button saveButton;
    Button button8;
    Button button5;
    Button button6;
    Button button7;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;
    EditText editText;
    ImageView statusIMG;
    EditText adressBT;
    ProgressBar progressBar;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonR = findViewById(R.id.buttonR);
        saveButton = findViewById(R.id.Save);
        button8 = findViewById(R.id.button8);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        editText = findViewById(R.id.editText);
        statusIMG = findViewById(R.id.statusIMG);
        adressBT = (EditText) findViewById(R.id.adressBT);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
    }

    @Override
    public void onClick(View v) {
        Log.d("Click", String.valueOf(v));
        if (v == buttonR) {
            String s = String.valueOf(editText.getText());
            String chain;
            Log.d("String: ", s);
            for (int i = 0; i < s.length() / 5; i++) {
                chain = "";
                for (int j = 1; j < 4; j++) {
                    chain += s.charAt(i * 5 + j);
                }
                SendToESP32S.send(s.charAt(i * 5), chain);
                Log.d("Chain: ", s.charAt(i * 5) + " " + chain);
            }
        } else if (v == statusIMG) {
            progressBar.setVisibility(View.VISIBLE);
            SendToESP32S.Connect(String.valueOf(adressBT.getText()));
            if (SendToESP32S.clientSocket.isConnected()) {
                Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), "Something wrong!", Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }else if (v == button8) {
            progressBar.setVisibility(View.VISIBLE);
            Intent intentMain = new Intent(MainActivity2.this,
                    MainActivity.class);
            MainActivity2.this.startActivity(intentMain);
            progressBar.setVisibility(View.INVISIBLE);
        } else if(v == saveButton){
            saveText(v, String.valueOf(lastFuncButtonID), String.valueOf(editText.getText()));
        }
    }

    public void editMode(View v) {
        if (v == radioButton) {
            if (!EditMode) {
                EditMode = true;
                saveButton.setVisibility(View.VISIBLE);
                editText.setText("");
                Toast.makeText(getBaseContext(), "Edit Mode ON", Toast.LENGTH_LONG).show();
            } else {
                EditMode = false;
                radioButton.setChecked(false);
                saveButton.setVisibility(View.INVISIBLE);
                editText.setText("");
                Toast.makeText(getBaseContext(), "Edit Mode OFF", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void funcButton(View v) {
        if(EditMode){
            editText.setText("");
            editText.setText(openText(v, String.valueOf(v.getId())));
            lastFuncButtonID = v.getId();
        } else {
            String s = openText(v, String.valueOf(v.getId()));
            String chain;
            Log.d("String: ", s);
            for (int i = 0; i < s.length() / 5; i++) {
                chain = "";
                for (int j = 1; j < 4; j++) {
                    chain += s.charAt(i * 5 + j);
                }
                SendToESP32S.send(s.charAt(i * 5), chain);
                Log.d("Chain: ", s.charAt(i * 5) + " " + chain);
            }
        }


    }




    public void saveText(View view,String FILE_NAME, String text){

        FileOutputStream fos = null;
        try {

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {
            createFile(FILE_NAME);
            //saveText(view, FILE_NAME, text);
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createFile(String filename){
        String outputString = "";

        try {
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(outputString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream inputStream = openFileInput(filename);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String openText(View view, String FILE_NAME){

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            return text;
        }
        catch(IOException ex) {

            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            createFile(FILE_NAME);
            openText(view, FILE_NAME);
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                createFile(FILE_NAME);
                openText(view, FILE_NAME);
            }
        }
        return "error: unknown";
    }

}
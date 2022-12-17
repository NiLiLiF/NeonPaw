package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SendToESP32S {
    public static BluetoothSocket clientSocket;

    public static boolean initConnect(Activity a, String address){


        String enableBT = BluetoothAdapter.ACTION_REQUEST_ENABLE;
        a.startActivityForResult(new Intent(enableBT), 0);
        return Connect(address);
    }

    public static boolean Connect(String address){
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        try{

            BluetoothDevice device = bluetooth.getRemoteDevice(address);
            Method m = device.getClass().getMethod(
                    "createRfcommSocket", new Class[] {int.class});
            clientSocket = (BluetoothSocket) m.invoke(device, 1);
            clientSocket.connect();

            //В случае появления любых ошибок, выводим в лог сообщение
        } catch (IOException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (SecurityException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (InvocationTargetException e) {
            Log.d("BLUETOOTH", e.getMessage());
        }
        return clientSocket.isConnected();
    }

    public static void send(char c, String s){

            if(c == 'T'){
                try {
                    Thread.sleep(Long.parseLong(s)*10);
                    Log.d("Thread", String.valueOf(Long.parseLong(s)*10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    //Получаем выходной поток для передачи данных
                    OutputStream outStream = SendToESP32S.clientSocket.getOutputStream();
                    byte[] value = new byte[]{(byte) c, 48, 48, 48, 59};
                    for (int i = 1; i <= s.length(); i++) {
                        value[4 - i] = (byte) s.charAt(s.length() - i);
                    }
                    Log.d("sendetText", new String(value, "ASCII") + "  " + s);
                    outStream.write(value, 0, value.length);
                } catch (IOException e) {
                    //Если есть ошибки, выводим их в лог
                    Log.d("BLUETOOTH", e.getMessage());
                }
            }

    }


}

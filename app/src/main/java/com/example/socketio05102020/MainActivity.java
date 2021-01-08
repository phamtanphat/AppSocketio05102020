package com.example.socketio05102020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.16.1.68:3000");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.connect();

        mSocket.on("statusconnect", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Object object = args[0];
                    JSONObject jsonObject = new JSONObject(object.toString());
                    String message = jsonObject.getString("message");
                    Log.d("BBB",message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
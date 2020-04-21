package ru.orlovvv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText loginNickname;
    Button loginButton;
    String nickname;
    int token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginNickname = findViewById(R.id.loginEditText);
        loginButton = findViewById(R.id.loginButton);
    }

    public void onClick(View v) {
        nickname = (loginNickname.getText().toString());
        Log.d("mytag", "onClick: " + loginNickname.getText());
        Request registerRequest = new Request("register", nickname, -1);
        SetServerTask task = new SetServerTask(this);
        task.execute(registerRequest);


    }


}

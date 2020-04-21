package ru.orlovvv;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    SetCardsFieldView cardsFieldView;
    TextView gameNickName;
    Button getCardsButton, checkSetButton;
    //ArrayList<Card> cards;
    int token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameNickName = findViewById(R.id.gameNickName);
        getCardsButton = findViewById(R.id.getCards);
        Intent in = getIntent();
        String nickname = in.getStringExtra("nickname");
        token = (in.getIntExtra("token", -1));
        Log.d("mytag", "onCreate: " + nickname + " " + token);
        gameNickName.setText(nickname);
        sendFetchRequest();
        cardsFieldView = findViewById(R.id.setCardsFieldView);



    }


//    public void getCards() {
//        Request getCardsRequest = new Request("fetch_cards", token);
//        SetServerTask task = new SetServerTask(this);
//        task.execute(getCardsRequest);
//
//    }
//
//    public void takeSet() {
//        ArrayList<Card> set = cardsFieldView.checkSet();
//        Log.d("mytag", "takeSet: " + set);
////        if(set != null) {
//            Log.d("mytag", "takeSet: " + set);
//            Request getCardsRequest = new Request("take_set", token, set);
//            Log.d("mytag", "takeSet: " + cardsFieldView.checkSet());
//            SetServerTask task = new SetServerTask(this);
//            task.execute(getCardsRequest);
////        }
//
//    }

//    public void takeSetResponse(Response response) {
//        Log.d("mytag", "takeSetResponse: " + response.status);
//        getCards();
//    }
//
//    public void getCardsResponse(ArrayList<Card> cards) {
//        //Log.d("mytag", "getCardsResponse before: " + cardsFieldView.getCardField());
//        cardsFieldView.setCardField(new CardField(cards));
//       // Log.d("mytag", "getCardsResponse after: " + cardsFieldView.getCardField());
//    }
//
    public void onGetCardsClick(View v) {
//       getCards();
        Log.d("mytag", "onGetCardsClick: " + 1);

    }

    public void onTakeSet(View v) {
//        takeSet();
        ArrayList<Card> set = cardsFieldView.checkSet();

        if (set != null) {
            Log.d("mytag", "onTakeSet: " + set + "SEEEEEEEEEEET");
            Request setRequest = Request.TakeSetRequest(token, set);
            SetServerTask task = new SetServerTask(this);
            task.execute(setRequest);
        }
        //Log.d("mytag", "onGetCardsClick: " + 1 + cardsFieldView.checkSet());

    }

    public void sendFetchRequest() {
        Request fetchRequest = Request.FetchRequest(token);
        SetServerTask task = new SetServerTask(this);
        task.execute(fetchRequest);
    }

    public void response(Response response, String request) {
        if (request.equals("fetch_cards")) {
            cardsFieldView.setCardField(new CardField(response.cards));
        }
        if (request.equals("take_set")) {
            sendFetchRequest();
        }
    }

}

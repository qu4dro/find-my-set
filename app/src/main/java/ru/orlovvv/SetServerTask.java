package ru.orlovvv;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SetServerTask extends AsyncTask<Request, Void, Response> {

    MainActivity mainActivity;
    GameActivity gameActivity;

    //    final GameActivity gameActivity;
//
    public SetServerTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public SetServerTask(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }
//    public SetServerTask(GameActivity activity) {
//        this.gameActivity = activity;
//    }

    public Response requestToSetServer(Request req) {
        Gson gson = new Gson();
        // TODO: указывайте нужный порт
        String API_URL = "http://194.176.114.21:8058";
        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true); // setting POST method
            OutputStream out = urlConnection.getOutputStream();
            // сериализованный объект-запрос пишем в поток
            String outJSON = gson.toJson(req);
            Log.d("mytag", "out: " + outJSON);
            out.write(outJSON.getBytes());

            InputStream stream = urlConnection.getInputStream();
            Response response = gson.fromJson(new InputStreamReader(stream), Response.class);
            return response;

        } catch (ConnectException | MalformedURLException e) {
            Log.e("mytag", "out: " + e.toString());
        } catch (IOException e) {
            Log.d("mytag", "out: " + e.toString());
        }
        return null;

    }

    @Override
    protected Response doInBackground(Request... requests) {
        Request r = requests[0];
        return requestToSetServer(r);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (!(response.token == -1)) {
            Log.d("mytag", "новый пользователь с токеном "+ response.token);
            mainActivity.token = response.token;
            startActivity();

        } else {
            startActivity();
        }


    }

    public void startActivity() {
        Intent intent = new Intent(mainActivity, GameActivity.class);
        intent.putExtra("nickname", mainActivity.nickname);
        intent.putExtra("token", mainActivity.token);
        mainActivity.startActivity(intent);

    }
}

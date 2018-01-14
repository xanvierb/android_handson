package nl.quintor.myhandsonapp.rest;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by xbrouwer on 27-11-17.
 */

public class NewsRestTask extends AsyncTask<String, Void, String>  {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            // Opdracht: Stel de request methode van de HttpURLConnection in op GET.
            // Opdracht: Zorg dat de HttpURLConnection verbinding maakt
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (httpURLConnection.getInputStream()));
            StringBuilder result= new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                result.append(line);
            }
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

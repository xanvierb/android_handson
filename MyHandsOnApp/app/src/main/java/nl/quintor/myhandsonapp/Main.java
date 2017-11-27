package nl.quintor.myhandsonapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import nl.quintor.myhandsonapp.fragment.NewsItemFragment;
import nl.quintor.myhandsonapp.model.NewsItem;
import nl.quintor.myhandsonapp.model.Request;
import nl.quintor.myhandsonapp.rest.NewsRestTask;

public class Main extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        try {
            Gson gson = new Gson();
            String s = new NewsRestTask().execute("https://newsapi.org/v2/top-headlines?apiKey=" + getResources().getString(R.string.apikey) + "&language=NL").get();

            Request obj = gson.fromJson(s, Request.class);

            NewsItemFragment newsItemFragment = (NewsItemFragment) getFragmentManager().findFragmentById(R.id.fragment1);

            if(obj != null && obj.getArticles().isEmpty()){
                newsItemFragment.setNewsTitle(obj.getArticles().get(0).getTitle());
                newsItemFragment.setNewsDescription(obj.getArticles().get(0).getDescription());
                newsItemFragment.setNewsAuthor(obj.getArticles().get(0).getAuthor());
                String urlToImage = obj.getArticles().get(0).getUrlToImage();
                newsItemFragment.setNewsImage(urlToImage );

                DateFormat format = new SimpleDateFormat("yyyy-M-d H:m:s", Locale.ENGLISH);
                String toParse = obj.getArticles().get(0).getPublishedAt().substring(0,19).replace('T', ' ');
                Date startDate = format.parse(toParse);
                Date endDate   = new Date(System.currentTimeMillis()); // Set end date

                long duration  = endDate.getTime() - startDate.getTime();

                long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
                long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
                long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);

                newsItemFragment.setNewsAge(fancyDuration(diffInSeconds, diffInMinutes, diffInHours));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private String fancyDuration(long diffInSeconds, long diffInMinutes, long diffInHours){
        if(diffInHours>0){
            return diffInHours + " uur";
        } if(diffInMinutes>0){
            return diffInMinutes + " min";
        } else {
            return diffInSeconds + " sec";
        }
    }


}

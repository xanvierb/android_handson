package nl.quintor.myhandsonapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import nl.quintor.myhandsonapp.fragment.NewsItemFragment;
import nl.quintor.myhandsonapp.model.NewsItem;

public class Main extends AppCompatActivity implements NewsItemFragment.OnListFragmentInteractionListener {
    private List<NewsItem> newsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(NewsItem item) {

    }
}

package nl.quintor.myhandsonapp.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import nl.quintor.myhandsonapp.R;
import nl.quintor.myhandsonapp.fragment.NewsItemFragment;
import nl.quintor.myhandsonapp.fragment.NewsItemFragment2;
import nl.quintor.myhandsonapp.fragment.NewsItemFragment2.OnListFragmentInteractionListener;
import nl.quintor.myhandsonapp.model.Article;
import nl.quintor.myhandsonapp.model.NewsItem;
import nl.quintor.myhandsonapp.model.Request;
import nl.quintor.myhandsonapp.rest.NewsRestTask;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NewsItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder> {

    private final List<NewsItem> mValues = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener = null;


    public NewsItemRecyclerViewAdapter(NewsItemFragment2 newsItemFragment2, OnListFragmentInteractionListener mListener) {
        try {
            Gson gson = new Gson();
            String s = new NewsRestTask().execute("https://newsapi.org/v2/top-headlines?apiKey=" + newsItemFragment2.getActivity().getResources().getString(R.string.apikey) + "&language=NL").get();



            Request obj = gson.fromJson(s, Request.class);

            NewsItemFragment2 newsItemFragment = newsItemFragment2;

            if(obj != null && !obj.getArticles().isEmpty()){
                for(Article article : obj.getArticles()){
                    NewsItem newsItem = new NewsItem();
                    newsItem.setNewsAuthor(article.getAuthor());
                    newsItem.setNewsAge(getAgeFromDate(article.getPublishedAt()));
                    newsItem.setNewsDescription(article.getDescription());
                    newsItem.setNewsTitle(article.getTitle());
                    newsItem.setImageUrl(article.getUrlToImage());
                    newsItem.setNewsUrl(article.getUrl());
                    mValues.add(newsItem);
                }


//
//                newsItemFragment.setNewsTitle(obj.getArticles().get(0).getTitle());
//                newsItemFragment.setNewsDescription(obj.getArticles().get(0).getDescription());
//                newsItemFragment.setNewsAuthor(obj.getArticles().get(0).getAuthor());
//                String urlToImage = obj.getArticles().get(0).getUrlToImage();
//                newsItemFragment.setNewsImage(urlToImage );
//

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitleView.setText(mValues.get(position).getNewsTitle());
        holder.mDescriptionView.setText(mValues.get(position).getNewsDescription());
        holder.mAuthorView.setText(mValues.get(position).getNewsAuthor());
        holder.mAgeView.setText(mValues.get(position).getNewsAge());
        setNewsImage(mValues.get(position).getImageUrl(), holder.mContentImage);

        holder.mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.example.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mValues.get(position).getNewsUrl()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final TextView mAuthorView;
        public final TextView mAgeView;
        public final ImageView mContentImage;

        public ViewHolder(View view) {
            super(view);

            mTitleView = (TextView) view.findViewById(R.id.newsTitle);
            mDescriptionView = (TextView) view.findViewById(R.id.newsDescription);
            mAuthorView = (TextView) view.findViewById(R.id.newsAuthor);
            mAgeView = (TextView) view.findViewById(R.id.newsAge);
            mContentImage = (ImageView) view.findViewById(R.id.imageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }

    private String getAgeFromDate(String date){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-M-d H:m:s", Locale.ENGLISH);
            String toParse = date.substring(0,19).replace('T', ' ');
            Date startDate = format.parse(toParse);
            Date endDate   = new Date(System.currentTimeMillis()); // Set end date

            long duration  = endDate.getTime() - startDate.getTime();

            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);

            return fancyDuration(diffInSeconds, diffInMinutes, diffInHours);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
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

    private void setNewsImage(String url, ImageView newsImageView){
        new NewsItemRecyclerViewAdapter.DownloadImageTask(newsImageView).execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            if(!urldisplay.contains("http") && urldisplay.startsWith("//")){
                urldisplay += "http:" + urldisplay;
            }
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}

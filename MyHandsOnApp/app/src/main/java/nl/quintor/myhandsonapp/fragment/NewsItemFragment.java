package nl.quintor.myhandsonapp.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.net.Uri;

import java.io.InputStream;

import nl.quintor.myhandsonapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsItemFragment extends Fragment {
    private TextView newsTitleView;
    private TextView newsDescriptionView;
    private TextView newsAuthorView;
    private TextView newsAgeView;
    private ImageView newsImageView;

    private OnFragmentInteractionListener mListener;

    public NewsItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsItem.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsItemFragment newInstance(String param1, String param2) {
        NewsItemFragment fragment = new NewsItemFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_news_item, container, false);
        // Inflate the layout for this fragment

        newsTitleView = (TextView) retView.findViewById(R.id.newsTitle);
        newsDescriptionView = (TextView) retView.findViewById(R.id.newsDescription);
        newsAuthorView = (TextView) retView.findViewById(R.id.newsAuthor);
        newsAgeView = (TextView) retView.findViewById(R.id.newsAge);
        newsImageView = (ImageView) retView.findViewById(R.id.imageView);

        return retView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String setNewsTitle(String newTitle){
        String returnString = newsTitleView.getText().toString();
        newsTitleView.setText(newTitle);
        return returnString;
    }

    public String setNewsDescription(String newDescription){
        String returnString = newsDescriptionView.getText().toString();
        newsDescriptionView.setText(newDescription);
        return returnString;
    }

    public String setNewsAuthor(String newAuthor){
        String returnString = newsAuthorView.getText().toString();
        newsAuthorView.setText(newAuthor);
        return returnString;
    }

    public String setNewsAge(String newAge){
        String returnString = newsAgeView.getText().toString();
        newsAgeView.setText(newAge);
        return returnString;
    }

    public void setNewsImage(String url){
        new DownloadImageTask(newsImageView).execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
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

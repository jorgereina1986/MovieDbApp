package jorgereina.com.moviedbapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MovieResultsActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private String movieUrl = "https://api.themoviedb.org/3/search/movie?query=avengers&api_key=fe321b50d58f46c550723750263ad677";
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_results);

        new MovieTask().execute(movieUrl);
    }

    private class MovieTask extends AsyncTask<String, Void, List<Movie>> {

        HttpsURLConnection connection = null;
        URL url = null;
        BufferedReader reader = null;
        String movieJsonStr;
        List<Movie> movieList = new ArrayList<>();

        @Override
        protected List<Movie> doInBackground(String... params) {
            try {
                url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer= new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                if (buffer.length() == 0){
                    return null;
                }

                movieJsonStr = buffer.toString();

                JSONObject root = new JSONObject(movieJsonStr);
                JSONArray resultsArray = root.getJSONArray("results");
                for (int i = 0; i < resultsArray.length() ; i++) {

                    JSONObject movieObj = resultsArray.getJSONObject(i);
                    Movie movie = new Movie();

                    movie.setMovieTitle(movieObj.getString("title"));
                    movie.setMovieOverview(movieObj.getString("overview"));
                    movie.setYear(movieObj.getString("release_date"));
                    movie.setMoviePoster(movieObj.getString("poster_path"));
                    movie.setMovieBackdrop(movieObj.getString("backdrop_path"));

                    movieList.add(movie);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movieList;
        }

        @Override
        protected void onPostExecute(List<Movie> results) {
            super.onPostExecute(results);

            recyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mAdapter = new MovieAdapter(getApplicationContext(), results);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);

            if (results != null){

                mAdapter.notifyDataSetChanged();



                //adding touch event to recyclerView
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Toast.makeText(getApplicationContext(), "onClick " + position, Toast.LENGTH_SHORT).show();

                        Movie movie = movieList.get(position);

                        Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                        intent.putExtra("title", movie.getMovieTitle());
                        intent.putExtra("overview", movie.getMovieOverview());
                        intent.putExtra("backdrop", movie.getMovieBackdrop());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                        Toast.makeText(getApplicationContext(), "onLongClick " + position, Toast.LENGTH_SHORT).show();
                    }
                }));

            }
        }
    }

    //creating TouchListenr to recycler view
    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;


        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child =recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (child != null && clickListener != null){
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                    super.onLongPress(e);
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)){

                clickListener.onClick(child, rv.getChildPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}

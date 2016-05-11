package jorgereina.com.moviedbapp;

//public class MovieResultsActivity extends AppCompatActivity {
//
////    private RecyclerView movieRecyclerView;
////    private RecyclerView.LayoutManager layoutManager;
////    public static final String API_KEY ="fe321b50d58f46c550723750263ad677";
////    private String movieUrl = "https://api.themoviedb.org/3/search/movie?query=avengers&api_key=fe321b50d58f46c550723750263ad677";
////    private MovieAdapter movieAdapter;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_movie_results);
////
////        MovieTask task = new MovieTask();
////        task.execute();
////
////
////        Intent getQuerySearchIntent = getIntent();
////        String searchQuery = getQuerySearchIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
////    }
////
////    private class MovieTask extends AsyncTask<String, String, String> {
////
////        HttpsURLConnection conn;
////        URL url = null;
//////        @Override
//////        protected String doInBackground(String... params) {
//////
//////            if (params.length == 0){
//////                return null;
//////            }
//////
//////            HttpsURLConnection connection = null;
//////            BufferedReader reader = null;
//////
//////            String title;
//////            String overview;
//////            String poster_path;
//////            List<String> movieInfo = new ArrayList<>();
//////
//////            try {
//////                URL url = new URL(movieUrl);
//////                connection = (HttpsURLConnection) url.openConnection();
//////
//////                InputStream stream = new BufferedInputStream(connection.getInputStream());
//////                reader = new BufferedReader(new InputStreamReader(stream));
//////                StringBuilder builder = new StringBuilder();
//////
//////                String inputString;
//////                while ((inputString = reader.readLine())!= null){
//////                    builder.append(inputString);
//////                }
//////
//////
//////            } catch (MalformedURLException e) {
//////                e.printStackTrace();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            } finally {
//////                if (connection != null) {
//////                    connection.disconnect();
//////                }
//////                if (reader != null) {
//////                    try {
//////                        reader.close();
//////                    } catch (final IOException e) {
//////                        return null;
//////                    }
//////                }
//////            }
//////
//////            return movieInfo;
//////        }
////
////        @Override
////        protected String doInBackground(String... params) {
////            try {
////
////                // Enter URL address where your json file resides
////                // Even you can make call to php file which returns json data
////                url = new URL(movieUrl);
////
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////                return e.toString();
////            }
////            try {
////
////                // Setup HttpURLConnection class to send and receive data from php and mysql
////                conn = (HttpsURLConnection) url.openConnection();
////                //conn.setReadTimeout(READ_TIMEOUT);
////                //conn.setConnectTimeout(CONNECTION_TIMEOUT);
////                conn.setRequestMethod("GET");
////                conn.connect();
////
////                // setDoOutput to true as we receive data from json file
////               // conn.setDoOutput(true);
////
////            } catch (IOException e1) {
////                e1.printStackTrace();
////                return e1.toString();
////            }
////
////            try {
////
////                int response_code = conn.getResponseCode();
////
////                // Check if successful connection made
////                if (response_code == HttpURLConnection.HTTP_OK) {
////
////                    // Read data sent from server
////                    InputStream input = conn.getInputStream();
////                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
////                    StringBuilder result = new StringBuilder();
////                    String line;
////
////                    while ((line = reader.readLine()) != null) {
////                        result.append(line);
////                    }
////
////                    // Pass data to onPostExecute method
////                    return (result.toString());
////
////                } else {
////
////                    return ("unsuccessful");
////                }
////
////            } catch (IOException e) {
////                e.printStackTrace();
////                return e.toString();
////            } finally {
////                conn.disconnect();
////            }
////
////
////        }
////
////        @Override
////        protected void onPostExecute(String result) {
////
////            List<Movies> data = new ArrayList<>();
////
////            //parse json
////
////            JSONObject root = null;
////            try {
////                root = new JSONObject(result);
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            JSONArray results = null;
////            try {
////                results = root.getJSONArray("results");
////                for (int i = 0; i < results.length(); i++) {
////                    Movies movieData = new Movies("lol","lol2");
////                    JSONObject obj = results.getJSONObject(i);
////
////                    movieData.movieTitle = obj.getString("title");
////                    movieData.movieDetails = obj.getString("overview");
////                    data.add(movieData);
////
////                }
////
////                // setup recyclerview
////                movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
////                movieRecyclerView.setHasFixedSize(true);
////                layoutManager = new LinearLayoutManager(getApplicationContext());
////                movieRecyclerView.setLayoutManager(layoutManager);
////                movieRecyclerView.setAdapter(movieAdapter);
////
////            } catch (JSONException e) {
////                    e.printStackTrace();
////            }
////
////
////        }
////    }
//
//
//}

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_results);

        recyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);

        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        new MovieTask().execute(movieUrl);

        //prepareMovieData();

    }

    /**
     * RecyclerView works with Dummy data;
     */

//    private void prepareMovieData() {
//        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Inside Out", "Animation, Kids & Family","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Shaun the Sheep", "Animation","2000");
//        movieList.add(movie);
//
//        movie = new Movie("The Martian", "Science Fiction & Fantasy","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Mission: Impossible Rogue Nation", "Action","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Up", "Animation","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Star Trek", "Science Fiction","2000");
//        movieList.add(movie);
//
//        movie = new Movie("The LEGO Movie", "Animation","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Iron Man", "Action & Adventure","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Aliens", "Science Fiction","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Chicken Run", "Animation","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Back to the Future", "Science Fiction","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Goldfinger", "Action & Adventure","2000");
//        movieList.add(movie);
//
//        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy","2000");
//        movieList.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//    }

/**
 * TODO: Movie info not loading into RecyclerView. FIX THIS!!
 */


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
            mAdapter.notifyDataSetChanged();

        }
    }
}

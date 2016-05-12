package jorgereina.com.moviedbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView overview;
    private ImageView backdrop;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        title = (TextView) findViewById(R.id.movie_title_details_tv);
        overview = (TextView) findViewById(R.id.movie_overview_details_tv);
        backdrop = (ImageView) findViewById(R.id.movie_backdrop_details_tv);
        baseUrl = "http://image.tmdb.org/t/p/w1280/";

        Intent intent = getIntent();
        String titleStr = intent.getStringExtra("title");
        String overviewStr = intent.getStringExtra("overview");
        String imageStr = intent.getStringExtra("backdrop");

        title.setText(titleStr);
        overview.setText(overviewStr);
        Picasso.with(this).load(baseUrl+imageStr).placeholder(R.mipmap.ic_launcher).fit().into(backdrop);


    }
}

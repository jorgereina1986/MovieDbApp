package jorgereina.com.moviedbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.jorgereina.moviedbapp.MESSAGE";
    EditText searchText;
    Button searchButton;
    String searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSearchQuery();
            }
        });


    }

    public void sendSearchQuery() {
        Intent intent = new Intent(this, MovieResultsActivity.class);
        searchText = (EditText) findViewById(R.id.search_et);
        searchInput = searchText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, searchInput);
        startActivity(intent);
    }

}

package jorgereina.com.moviedbapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c4q-jorgereina on 5/9/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    // constructor
    public MovieAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, year;

        public String thumbnail;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.movie_title_tv);
            description = (TextView) view.findViewById(R.id.movie_description_tv);
            year = (TextView) view.findViewById(R.id.movie_year_tv);
            //thumbnail = (TextView) view.findViewById();
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getMovieTitle());
        holder.description.setText(movie.getMovieOverview());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
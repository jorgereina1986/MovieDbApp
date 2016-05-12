package jorgereina.com.moviedbapp;

/**
 * Created by c4q-jorgereina on 5/6/16.
 */
public class Movie {

    public String movieTitle;
    public String movieOverview;
    public String moviePoster;
    public String year;
    private String movieBackdrop;

    public String getMovieBackdrop() {
        return movieBackdrop;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public void setMovieBackdrop(String movieBackdrop) {
        this.movieBackdrop = movieBackdrop;
    }
}



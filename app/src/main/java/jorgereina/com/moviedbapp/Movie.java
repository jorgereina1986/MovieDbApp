package jorgereina.com.moviedbapp;

/**
 * Created by c4q-jorgereina on 5/6/16.
 */
public class Movie {

    public String movieTitle;
    public String movieOverview;
    public String thumbnail;
    public String year;

//    public Movie(String movieTitle, String movieDetails, String year) {
//        this.movieTitle = movieTitle;
//        this.movieOverview = movieDetails;
//        this.year = year;
//    }


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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}



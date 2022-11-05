package model;

public class Song extends Audio {
    private String album;
    private double value;
    private int soldTimes;
    private Genre genre;

    public Song(String name, String url, double duration, String album, double value, Genre genre){
        super(name, url, duration);
        this.album = album;
        this.value = value;
        soldTimes = 0;
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

  
    public int getSoldTimes() {
        return soldTimes;
    }

    public void setSoldTimes(int soldTimes) {
        this.soldTimes = soldTimes;
    }

    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}

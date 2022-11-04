package model;

public class Song extends Audio {

    private Genre genre;
    private String album;
    private double value;
    private int soldTimes;

    public Song(String url, String name, int duration,int genreSelection, String album, double value) {
        super(url, name, duration);
        setGenre(genreSelection);
        this.album = album;
        this.value = value;
        soldTimes = 0;
        
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

    public void setGenre(int genreSelection) {
        switch (genreSelection) {
            
            case 1:
                genre = Genre.ROCK;
                break;

            case 2:
                genre = Genre.POP;
                break;

            case 3:
                genre = Genre.TRAP;
                break;
            
            case 4:
                genre = Genre.HOUSE;
                break;
        }
    }
}

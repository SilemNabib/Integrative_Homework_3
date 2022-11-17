package model;

import java.time.LocalDate;

public class BoughtSong {
    private LocalDate date;
    private Song song;
    
    public BoughtSong(Song song) {
        date = LocalDate.now();
        this.song = song;
    }
}

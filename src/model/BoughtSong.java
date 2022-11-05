package model;

import java.time.LocalDate;

public class BoughtSong {
    private LocalDate date;
    private Audio song;
    
    public BoughtSong(Audio song) {
        date = LocalDate.now();
        this.song = song;
    }
}

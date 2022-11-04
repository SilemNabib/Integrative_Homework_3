package model;

public class Standard extends Consumer {
 
    private final int MAX_SONGS;
    private final int MAX_PLAYLIST;

    public Standard (String nickName, String id) {
        
        super(nickName, id);

        MAX_SONGS = 100;
        MAX_PLAYLIST = 20;
    }

    
}

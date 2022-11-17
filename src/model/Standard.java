package model;

public class Standard extends Consumer {
    private final int playlistAmountMax;
    private final int playlistSongsMax;
    private int tempListenedSongs;

    public Standard (String nickname, String id) {
        super(nickname, id);

        playlistAmountMax = 20;
        playlistSongsMax = 100;
        tempListenedSongs = 0;

    }

    public int getTempListenedSongs() {
        return tempListenedSongs;
    }

    public void setTempListenedSongs(int tempListenedSongs) {
        this.tempListenedSongs = tempListenedSongs;
    }

    /**
     *
     */
    public boolean addPlaylist(Playlist newPlaylist){
        if (playlists.size()<playlistAmountMax) {

            playlists.add(newPlaylist);
            return true;

        } else {
            
            return false;
        }
        
        
    }

}

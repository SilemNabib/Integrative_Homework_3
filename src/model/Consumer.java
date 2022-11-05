package model;

import java.util.ArrayList;

public abstract class Consumer extends User {
    protected ArrayList<Playlist> playlists;
    protected ArrayList<BoughtSong> boughtSongs; //! NO SE ESTA USANDO

    private double songTotalTime;
    private double podcastTotalTime;
    private ArrayList<Genre> listenedGenre; //! NO SE ESTA USANDO
    private ArrayList<Category> listenedCategory; //! NO SE ESTA USANDO

    public Consumer(String nickname, String id){
        super(nickname, id);

        songTotalTime = 0;
        podcastTotalTime = 0;
        listenedGenre = new ArrayList<Genre>();
        listenedCategory = new ArrayList<Category>();
    }

    public double getSongTotalTime() {
        return songTotalTime;
    }

    public void setSongTotalTime(double songTotalTime) {
        this.songTotalTime = songTotalTime;
    }

  
    public double getPodcastTotalTime() {
        return podcastTotalTime;
    }

    public void setPodcastTotalTime(double podcastTotalTime) {
        this.podcastTotalTime = podcastTotalTime;
    }


    public boolean addPlaylist(Playlist newPlaylist){
        playlists.add(newPlaylist);
        return true;
    }
    
    public String showPlaylists() {
        String message = "";
        
        for (int i = 0; i<playlists.size() ; i++){

            message = (i+1)+". "+playlists.get(i).getName()+"\n";

        }
        
        return message;
    }


    public boolean deleteSongFromPlaylist(int playlistChosen, int songToDelete) {
        return playlists.get(playlistChosen).deleteSong(songToDelete);
    }

    public boolean addSongToPlaylist(int playlistChosen, Audio newPlaylistSong) {
        return playlists.get(playlistChosen).addSong(newPlaylistSong);
    }

 
   

}

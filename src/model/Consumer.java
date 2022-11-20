package model;

import java.util.ArrayList;

public abstract class Consumer extends User {
    protected ArrayList<Playlist> playlists;
    protected ArrayList<BoughtSong> boughtSongs;

    private double songTotalTime;
    private double podcastTotalTime;
    private ArrayList<Integer> listenedGenre;
    private ArrayList<Integer> listenedCategory;

    private ArrayList<Artist> listenedArtists;
    private ArrayList<Integer> countListenedArtists;
    private ArrayList<ContentCreator> listenedContCreator;
    private ArrayList<Integer> countListenedContCreator;

    public Consumer(String nickname, String id){
        super(nickname, id);

        songTotalTime = 0;
        podcastTotalTime = 0;

        playlists = new ArrayList<Playlist>();
        boughtSongs = new ArrayList<BoughtSong>();

        listenedGenre = new ArrayList<Integer>();
        listenedGenre.add(0);
        listenedGenre.add(0);
        listenedGenre.add(0);
        listenedGenre.add(0);
        listenedCategory = new ArrayList<Integer>();
        listenedCategory.add(0);
        listenedCategory.add(0);
        listenedCategory.add(0);
        listenedCategory.add(0);

        listenedArtists = new ArrayList<Artist>();
        countListenedArtists = new ArrayList<Integer>();
        listenedContCreator = new ArrayList<ContentCreator>();
        countListenedContCreator = new ArrayList<Integer>();
    }

    public ArrayList<Playlist> getPlaylist() {
        return playlists;
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

    public ArrayList<Integer> getListenedGenre() {
        return listenedGenre;
    }

    public ArrayList<Integer> getListenedCategory() {
        return listenedCategory;
    }


    public ArrayList<Artist> getListenedArtists() {
        return listenedArtists;
    }

    public void setListenedArtists(Artist artistData) {
        listenedArtists.add(artistData);
        countListenedArtists.add(1);
    }

    public ArrayList<Integer> getCountListenedArtists() {
        return countListenedArtists;
    }

    public void setCountListenedArtists(int position) {
        countListenedArtists.set(position, countListenedArtists.get(position)+1);
    }

    public ArrayList<ContentCreator> getListenedContCreator() {
        return listenedContCreator;
    }

    public void setListenedContCreator(ContentCreator contCreatorData) {
        listenedContCreator.add(contCreatorData);
        countListenedContCreator.add(1);
    }

    public ArrayList<Integer> getCountListenedContCreator() {
        return countListenedContCreator;
    }

    public void setCountListenedContCreator(int position) {
        countListenedContCreator.set(position, countListenedContCreator.get(position)+1);
    }

    public boolean addPlaylist(Playlist newPlaylist){
        playlists.add(newPlaylist);
        return true;
    }

    public String showPlaylists() {
        String message = "";

        for (int i = 0; i<playlists.size() ; i++){
            message += (i+1) + ". " + playlists.get(i).getName() + "\n";

        }

        return message;
    }

    public boolean deleteAudioFromPlaylist(int playlistChosen, int songToDelete) {
        return playlists.get(playlistChosen).deleteSong(songToDelete);
    }

    public boolean addAudioToPlaylist(int playlistChosen, Audio newPlaylistSong) {
        return playlists.get(playlistChosen).addSong(newPlaylistSong);

    }

    public String sharePlaylist(int playlistIndex) {
        return playlists.get(playlistIndex).generateId();
    }

    public boolean playAudio(Audio playedAudio) {
        if (playedAudio instanceof Song) {
            Song audioData = (Song) playedAudio;

            songTotalTime += playedAudio.getDuration();

            for (int i = 0; i < Genre.values().length-1; i++) {
                if (Genre.values()[i].equals(audioData.getGenre())){
                    listenedGenre.set(i,listenedGenre.get(i)+1);
                }
            }

        }

        if (playedAudio instanceof Podcast) {
            Podcast audioData = (Podcast) playedAudio;

            podcastTotalTime += playedAudio.getDuration();

            for (int i = 0; i < Category.values().length-1; i++) {
                if (Category.values()[i].equals(audioData.getCategory())){
                    listenedCategory.set(i,listenedCategory.get(i)+1);
                }
            }
        }

        return true;
    }

    public boolean buySong(BoughtSong newBoughtSong) {
        boughtSongs.add(newBoughtSong);
        return true;
    }


}

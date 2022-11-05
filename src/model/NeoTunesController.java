package model;

import java.util.ArrayList;

public class NeoTunesController {
    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController(){
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
    }

    public User createConsumer(int consumerType, String nickname, String id) {
        User newUser = null;

        switch (consumerType) {
            case 1:
                newUser = new Standard(nickname, id);
                break;

            case 2:
                newUser = new Premium(nickname, id);
                break;
        }

        return newUser;
    }

    public User createProducer(int producerType, String nickname, String id, String name, String url) {
        User newUser = null;

        switch (producerType) {
            case 1:
                newUser = new Artist(nickname, id, name, url);
                break;

            case 2:
                newUser = new ContentCreator(nickname, id, name, url);
                break;
        }

        return newUser;
    }

    public boolean addUser(User newUser) {
        users.add(newUser);
        return true;
    }

    public Audio createSong(String name, String url, double duration, String album, double value, int genreChoice){

        Genre genre = null;

        switch (genreChoice) {
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

        Audio newAudio = new Song(name, url, duration, album, value, genre);

        return newAudio;
    }

    public Audio createPodcast(String name, String url, double duration, String description, int categoryChoice){

        Category category = null;

        switch (categoryChoice) {
            case 1:
                category = Category.POLITICS;
                break;

            case 2:
                category = Category.ENTERTAINMENT;
                break;

            case 3:
                category = Category.VIDEOGAMES;
                break;

            case 4:
                category = Category.FASHION;
                break;
        }

        Audio newAudio = new Podcast(name, url, duration, description, category);

        return newAudio;
    }

    public boolean addAudio(int option, Audio newAudio) {
        audios.add(newAudio);

        Producer tempUser = (Producer) users.get(option);
        tempUser.addAudioToProducer(newAudio);
        users.set(option, tempUser);

        return true;  
    }

    public boolean checkIfIdExists(String id){

        for (int i = 0; i<users.size(); i++) {

            if (id.equalsIgnoreCase(users.get(i).getId())){
                return false;
            }
        }
        
        return true;
    }

    public Playlist createPlaylist(String name, String id) {
        Playlist newPlaylist = new Playlist(name, id);

        return newPlaylist;
    }

    public boolean addPlaylistToConsumer(int chosenConsumer, Playlist newPlaylist) {
        Consumer tempUser = (Consumer) users.get(chosenConsumer);
        
        if (tempUser.addPlaylist(newPlaylist)) {
            users.set(chosenConsumer, tempUser);
            return true;

        } else {
            return false;
            
        }

    }

    public String showArtists(){
        String message = "";
        
        for (int i = 0; i<users.size(); i++){
            if (users.get(i) instanceof Artist){
               message  += ((i+1)+". "+users.get(i).getNickname()+"\n"); 
            }
        }

        return message;
    }

    public String showAudios(){
        String message = "";

        for (int i = 0; i<audios.size(); i++){
            message += (i+1)+". "+audios.get(i).getName()+" "+audios.get(i).getDuration()+"\n";
        }

        return message;
    }

    public String showContentCreators(){
        String message = "";
        
        for (int i = 0; i<users.size(); i++){
            if (users.get(i) instanceof ContentCreator){
               message  += ((i+1)+". "+users.get(i).getNickname()+"\n"); 
            }
        }

        return message;
    }

    public String showPlaylists(int indexConsumer) {
        Consumer tempUser = (Consumer) users.get(indexConsumer);
         
        String message = tempUser.showPlaylists();
       
        return message;
    }

    public String showConsumers(){
        String message = "";
        
        for (int i = 0; i<users.size(); i++){
            if (users.get(i) instanceof Consumer){
               message  += ((i+1)+". "+users.get(i).getNickname()+"\n"); 
            }
        }

        return message;
    }

    public String showAudios(int indexConsumer, int indexPlaylist) {
        Consumer tempUser = (Consumer) users.get(indexConsumer);
         
        String message = tempUser.getPlaylist().get(indexPlaylist).audioToString();
       
        return message;
    }

    
}

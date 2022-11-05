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
}

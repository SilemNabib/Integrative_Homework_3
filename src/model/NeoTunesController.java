package model;

import java.util.ArrayList;

public class NeoTunesController {
    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController(){
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean addUser(User newUser) {
        users.add(newUser);

        return true;
    }


    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public boolean addAudio(int option, Audio newAudio) {
        audios.add(newAudio);

        Producer tempUser = (Producer)users.get(option);
        tempUser.addAudio(newAudio);

        users.set(option, tempUser);

        return true;
    }

    public boolean validateId(String userId) {

        for(int i = 0; i<users.size(); i++) {
            
            if (userId.equalsIgnoreCase(users.get(i).getId())) {
                
                return false;
            }
        }

        return true;
    }

    public User createUserConsumer(String nickName, String id, int option) {
        User newUser = null;
        
        switch (option) {
            case 1: //Standard

                newUser = new Standard(nickName, id); 
                break;

            case 2: //Premium

                newUser = new Premium(nickName, id);
                break;
        }

        return newUser;
    }

    public User createUserProducer(String nickName, String id, String name, String url, int option) {
        User newUser = null;
        
        switch (option) {
            case 1: //Artist

                newUser = new Artist(nickName, id, name, url); 
                break;

            case 2: //ContentCreator

                newUser = new ContentCreator(nickName, id, name, url);
                break;
        }

        return newUser;
    }

    public Audio createAudio(String url, String name, int duration,int genreSelection, String album, double value) {
        Audio newAudio = new Song(url, name, duration, genreSelection, album, value);

        return newAudio;
    }

    public Audio createAudio(String url, String name, int duration, int categoryOption, String description) {
        Audio newAudio =  new Podcast(url, name, duration, categoryOption, description);

        return newAudio;
    }


    public boolean validateUserProducer (User userData) {
        return (userData instanceof Producer);
    }

    public boolean validateUserContentCreator (User userData) {
        return (userData instanceof ContentCreator);
    }

    public boolean validateUserArtist (User userData) {
        return (userData instanceof Artist);
    }

}

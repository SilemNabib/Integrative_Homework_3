package model;

import java.util.ArrayList;

public class NeoTunesController {
    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController(){
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
    }


    public boolean createConsumer(int consumerType, String nickname, String id) {
        User newUser = null;

        switch (consumerType) {
            case 1:
                newUser = new Standard(nickname, id);
                break;

            case 2:
                newUser = new Premium(nickname, id);
                break;
        }

        return addUser(newUser);
    }

    public boolean createProducer(int producerType, String nickname, String id, String name, String url) {
        User newUser = null;

        switch (producerType) {
            case 1:
                newUser = new Artist(nickname, id, name, url);
                break;

            case 2:
                newUser = new ContentCreator(nickname, id, name, url);
                break;
        }

        return addUser(newUser);
    }

    public boolean addUser(User newUser) {
        users.add(newUser);
        return true;
    }

    public boolean createSong(int chosenConsumer, String name, String url, double duration, String album, double value, Genre genre){

        Audio newAudio = new Song(name, url, duration, album, value, genre);

        return addAudioToArtist(chosenConsumer, newAudio);
    }

    public boolean createPodcast(int chosenConsumer, String name, String url, double duration, String description, Category category){

        Audio newAudio = new Podcast(name, url, duration, description, category);

        return addAudioToContCreator(chosenConsumer, newAudio);
    }

    public boolean addAudio(Audio newAudio) {
        audios.add(newAudio);

        return true;
    }
    public boolean checkIfIdExists(String id){

        for (User x : users) {
            if (id.equalsIgnoreCase(x.getId())){
                return true;
            }
        }
        
        return false;
    }

    public boolean addAudioToArtist(int chosenConsumer, Audio newAudio){

        if (users.get(chosenConsumer) instanceof Artist){
            ((Artist) users.get(chosenConsumer)).addAudioToProducer(newAudio);
            addAudio(newAudio);

            return true;
        }

        return false;
    }

    public boolean addAudioToContCreator(int chosenConsumer, Audio newAudio){

        if (users.get(chosenConsumer) instanceof ContentCreator){
            ((ContentCreator) users.get(chosenConsumer)).addAudioToProducer(newAudio);
            addAudio(newAudio);

            return true;
        }

        return false;
    }
    public boolean createPlaylist(int chosenConsumer, String name, String id) {

        Playlist newPlaylist = new Playlist(name, id);

        return addPlaylistToConsumer(chosenConsumer, newPlaylist);
    }

    public boolean addPlaylistToConsumer(int chosenConsumer, Playlist newPlaylist) {

        if (users.get(chosenConsumer) instanceof Consumer){
            return ((Consumer) users.get(chosenConsumer)).addPlaylist(newPlaylist);
        }

        return false;
    }

    public boolean addAudioToPlaylist(int playListChosen, int consumerChosen, int newPlaylistAudio) {
        Consumer tempUser = (Consumer) users.get(consumerChosen);

        boolean status = tempUser.addAudioToPlaylist(playListChosen, audios.get(newPlaylistAudio));
        users.set(consumerChosen, tempUser);

        return status;
    }

    public boolean deleteAudioToPlaylist(int playListChosen, int consumerChosen, int newPlaylistAudio) {
        Consumer tempUser = (Consumer) users.get(consumerChosen);

        tempUser.deleteAudioFromPlaylist(playListChosen, newPlaylistAudio);
        users.set(consumerChosen, tempUser);

        return true;
    }

    public String sharePlaylist(int consumerIndex, int playlistIndex) {
        Consumer tempUser = (Consumer) users.get(consumerIndex);

        return tempUser.sharePlaylist(playlistIndex);
    }

    public boolean buySong(int consumerIndex, int songIndex) {
        BoughtSong newBoughtSong =  new BoughtSong((Song) audios.get(songIndex));

        int tempInt = ((Song) audios.get(songIndex)).getSoldTimes();
        ((Song) audios.get(songIndex)).setSoldTimes(tempInt+1);

        return ((Consumer) users.get(consumerIndex)).buySong(newBoughtSong);
    }

    public String playAudio(int consumerIndex, int audioIndex) {
        String message = "The Audio cannot be played";
        boolean exists = false;
        int count = 0;

        if (((Consumer) users.get(consumerIndex)).playAudio(audios.get(audioIndex))) {

            if (audios.get(audioIndex) instanceof Song) {
                Artist tempUser = (Artist) findAudiosProducer(audios.get(audioIndex));

                for (Artist artistX : ((Consumer) users.get(consumerIndex)).getListenedArtists()) {
                    if (artistX.equals(tempUser)) {
                        ((Consumer) users.get(consumerIndex)).setCountListenedArtists(count);
                        message = "Playing Song: " + audios.get(audioIndex).getName() + "\n";
                        exists = true;

                    }
                    count++;
                }

                if (!exists) {
                    ((Consumer) users.get(consumerIndex)).setListenedArtists(tempUser);
                }
            }

            if (audios.get(audioIndex) instanceof Podcast) {
                ContentCreator tempUser = (ContentCreator) findAudiosProducer(audios.get(audioIndex));

                for (ContentCreator artistX : ((Consumer) users.get(consumerIndex)).getListenedContCreator()) {
                    if (artistX.equals(tempUser)) {
                        message = "Playing Podcast: " + audios.get(audioIndex).getName() + "\n";
                        ((Consumer) users.get(consumerIndex)).setCountListenedContCreator(count);
                        exists = true;

                    }
                    count++;
                }

                if (!exists) {
                    ((Consumer) users.get(consumerIndex)).setListenedContCreator(tempUser);
                }
            }
        }

        if (users.get(consumerIndex) instanceof Standard) {
            int tempVar = ((Standard) users.get(consumerIndex)).getTempListenedSongs();
            ((Standard) users.get(consumerIndex)).setTempListenedSongs(tempVar+1);

            if (((Standard) users.get(consumerIndex)).getTempListenedSongs() == 2) {
                ((Standard) users.get(consumerIndex)).setTempListenedSongs(0);
                message += "Playing ad \n ";

            }
        }



        return message;
    }

    public User findAudiosProducer(Audio audioData) {
        for (User x : users) {
            if (x instanceof Producer) {
                Producer tempUser = (Producer) x;

                for (Audio y : tempUser.getAudios()){
                    if (audioData.equals(y)){
                        return x;
                    }
                }
            }

        }

        return null;
    }

    public String showArtists(){
        String message = "";

        for (int i = 0; i< users.size(); i++){
            if (users.get(i) instanceof Artist){
               message  += ((i+1)+". "+ users.get(i).getNickname()+"\n");
            }
        }

        return message;
    }

    public String showAudios(int audioTypeToShow){
        String message = "";

        if (audioTypeToShow == 1) {
            for (int i = 0; i<audios.size(); i++){
                if (audios.get(i) instanceof Song) {
                    message += (i+1)+". "+audios.get(i).getName()+" "+audios.get(i).getDuration()+"\n";
                }
            }
        }

        if (audioTypeToShow == 2) {
            for (int i = 0; i<audios.size(); i++){
                if (audios.get(i) instanceof Podcast) {
                    message += (i+1)+". "+audios.get(i).getName()+" "+audios.get(i).getDuration()+"\n";
                }
            }
        }

        return message;
    }

    public String showContentCreators(){
        String message = "";

        for (int i = 0; i< users.size(); i++){
            if (users.get(i) instanceof ContentCreator){
                message  += ((i+1)+". "+ users.get(i).getNickname()+"\n");
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

        for (int i = 0; i< users.size(); i++){
            if (users.get(i) instanceof Consumer){
                message  += ((i+1)+". "+ users.get(i).getNickname()+"\n");
            }
        }

        return message;
    }

    public String showAudios(int indexConsumer, int indexPlaylist) {
        Consumer tempUser = (Consumer) users.get(indexConsumer);

        String message = tempUser.getPlaylist().get(indexPlaylist).audioToString();

        return message;
    }

    public String showSongGenre() {
        String message = "";

        for (int i = 0; i<Genre.values().length; i++) {
            message += (i+1) + ". " + Genre.values()[i];
        }

        return message;
    }

    public String showPodcastCategory() {
        String message = "";

        for (int i = 0; i<Category.values().length; i++) {
            message += (i+1) + ". " + Category.values()[i];
        }

        return message;
    }

    
}



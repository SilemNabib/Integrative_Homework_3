package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public String reportAudioReproductions() {
        String report = "";
        int[] countGenreReproductions = new int[Genre.values().length];
        countGenreReproductions[0] = 0;
        countGenreReproductions[1] = 0;
        countGenreReproductions[2] = 0;
        countGenreReproductions[3] = 0;

        int[] countCategoryReproductions = new int[Category.values().length];
        countCategoryReproductions[0] = 0;
        countCategoryReproductions[1] = 0;
        countCategoryReproductions[2] = 0;
        countCategoryReproductions[3] = 0;

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                Genre tempGenre = ((Song) audios.get(i)).getGenre();

                if (tempGenre.equals(Genre.ROCK)) {
                    countGenreReproductions[0] += audios.get(i).getReproductedTimes();
                }
                if (tempGenre.equals(Genre.POP)) {
                    countGenreReproductions[1] += audios.get(i).getReproductedTimes();
                }
                if (tempGenre.equals(Genre.TRAP)) {
                    countGenreReproductions[2] += audios.get(i).getReproductedTimes();
                }
                if (tempGenre.equals(Genre.HOUSE)) {
                    countGenreReproductions[3] += audios.get(i).getReproductedTimes();
                }
            }
        }

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Podcast) {
                Category tempCategory = ((Podcast) audios.get(i)).getCategory();

                if (tempCategory.equals(Category.POLITICS)) {
                    countCategoryReproductions[0] += audios.get(i).getReproductedTimes();
                }
                if (tempCategory.equals(Category.ENTERTAINMENT)) {
                    countCategoryReproductions[1] += audios.get(i).getReproductedTimes();
                }
                if (tempCategory.equals(Category.VIDEOGAMES)) {
                    countCategoryReproductions[2] += audios.get(i).getReproductedTimes();
                }
                if (tempCategory.equals(Category.FASHION)) {
                    countCategoryReproductions[3] += audios.get(i).getReproductedTimes();
                }
            }
        }

        report += "Genre";
        for (int i = 0; i<Genre.values().length;i++){
            report += Genre.values()[i] + " reproductions: " + countGenreReproductions[i] + "\n";
        }

        report += "Category";
        for (int i = 0; i<Category.values().length;i++){
            report += Category.values()[i] + " reproductions: " + countCategoryReproductions[i] + "\n";
        }

        return report;
    }

    public ArrayList<Integer> findMostListenedGenre(){
        ArrayList<Integer> countGenreReproductions = new ArrayList<Integer>();

        for (int i = 0; i<Genre.values().length;i++) {
            countGenreReproductions.add(0);
        }

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                Genre tempGenre = ((Song) audios.get(i)).getGenre();
                int audioReproductions = audios.get(i).getReproductedTimes();

                if (tempGenre.equals(Genre.ROCK)) {
                    countGenreReproductions.set(0, countGenreReproductions.get(0)+audioReproductions);
                }
                if (tempGenre.equals(Genre.POP)) {
                    countGenreReproductions.set(1, countGenreReproductions.get(1)+audioReproductions);
                }
                if (tempGenre.equals(Genre.TRAP)) {
                    countGenreReproductions.set(2, countGenreReproductions.get(2)+audioReproductions);
                }
                if (tempGenre.equals(Genre.HOUSE)) {
                    countGenreReproductions.set(3, countGenreReproductions.get(3)+audioReproductions);
                }
            }
        }

        return countGenreReproductions;
    }

    public String reportMostListenedGenre(){
        ArrayList<Integer> countGenreReproductions = findMostListenedGenre();
        ArrayList<Integer> organizedArray = countGenreReproductions;
        organizedArray.sort(Comparator.reverseOrder());

        int genrePosition = 0;

        for (int i = 0; i<countGenreReproductions.size(); i++) {
            if (organizedArray.get(0) == countGenreReproductions.get(i)) {
                genrePosition = i;
            }
        }

        String report = "The most listened genre in Neotunes is " + Genre.values()[genrePosition] + " with" + organizedArray.get(0) + " reproductions";

        return report;
    }

    public ArrayList findMostListenedCategory(){
        ArrayList<Integer> countCategoryReproductions = new ArrayList<Integer>();

        for (int i = 0; i<Genre.values().length;i++) {
            countCategoryReproductions.add(0);
        }

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Podcast) {
                Category tempGenre = ((Podcast) audios.get(i)).getCategory();
                int audioReproductions = audios.get(i).getReproductedTimes();

                if (tempGenre.equals(Category.POLITICS)) {
                    countCategoryReproductions.set(0, countCategoryReproductions.get(0)+audioReproductions);
                }
                if (tempGenre.equals(Category.ENTERTAINMENT)) {
                    countCategoryReproductions.set(1, countCategoryReproductions.get(1)+audioReproductions);
                }
                if (tempGenre.equals(Category.VIDEOGAMES)) {
                    countCategoryReproductions.set(2, countCategoryReproductions.get(2)+audioReproductions);
                }
                if (tempGenre.equals(Category.FASHION)) {
                    countCategoryReproductions.set(3, countCategoryReproductions.get(3)+audioReproductions);
                }
            }
        }

        return countCategoryReproductions;
    }

    public String reportMostListenedCategory(){
        ArrayList<Integer> countCategoryReproductions = findMostListenedCategory();
        ArrayList<Integer> organizedArray = countCategoryReproductions;
        organizedArray.sort(Comparator.reverseOrder());

        int genrePosition = 0;

        for (int i = 0; i<countCategoryReproductions.size(); i++) {
            if (organizedArray.get(0) == countCategoryReproductions.get(i)) {
                genrePosition = i;
            }
        }

        String report = "The most listened Category in Neotunes is " + Category.values()[genrePosition] + " with" + organizedArray.get(0) + " reproductions";

        return report;
    }

    public ArrayList<Artist> sortArtists() {
        ArrayList<Artist> artists = new ArrayList<Artist>();

        for (int i = 0; i<users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                artists.add((Artist) users.get(i));
            }
        }

        Collections.sort(artists, Comparator.comparing(Producer::getReproductedTimes));

        return artists;
    }

    public ArrayList<ContentCreator> sortContCreators() {
        ArrayList<ContentCreator> contentCreators = new ArrayList<ContentCreator>();

        for (int i = 0; i<users.size(); i++) {
            if (users.get(i) instanceof ContentCreator) {
                contentCreators.add((ContentCreator) users.get(i));
            }
        }

        Collections.sort(contentCreators, Comparator.comparing(Producer::getReproductedTimes));

        return contentCreators;
    }

    public String showArtistTop() {
        String report = "Top Artist\n";
        int count = 0;

        ArrayList<Artist> artists = sortArtists();

        for (int i = 0; i<artists.size() && count<5; i++) {
            report += (i+1) + ". " + artists.get(i).getName() + "Reproductions: " + artists.get(i).getReproductedTimes() + "\n";
            count++;
        }

        return report;
    }

    public String showContCreatorTop() {
        String report = "Top Content Creator\n";
        int count = 0;

        ArrayList<ContentCreator> contCreator = sortContCreators();

        for (int i = 0; i<contCreator.size() && count<5; i++) {
            report += (i+1) + ". " + contCreator.get(i).getName() + "Reproductions: " + contCreator.get(i).getReproductedTimes() + "\n";
            count++;
        }

        return report;
    }

    public ArrayList sortSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                songs.add((Song) audios.get(i));
            }
        }

        Collections.sort(songs, Comparator.comparing(Audio::getReproductedTimes));

        return songs;
    }

    public ArrayList sortPodcasts() {
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

        for (int i = 0; i<audios.size(); i++) {
            if (audios.get(i) instanceof Podcast) {
                podcasts.add((Podcast) audios.get(i));
            }
        }

        Collections.sort(podcasts, Comparator.comparing(Audio::getReproductedTimes));

        return podcasts;
    }

    public String showAudiosTop() {
        String report = "Top Songs \n";
        int count = 0;

        ArrayList<Song> songs = sortSongs();

        for (int i = 0; i<songs.size() && count<10; i++) {
            report += (i+1) + ". " + songs.get(i).getName() + "Genre: " + songs.get(i).getGenre() + "Reproductions: "+ songs.get(i).getReproductedTimes() + "\n";
            count++;
        }

        count = 0;

        ArrayList<Podcast> podcasts = sortPodcasts();

        for (int i = 0; i<podcasts.size() && count<10; i++) {
            report += (i+1) + ". " + podcasts.get(i).getName() + "Category: " + podcasts.get(i).getCategory() + "Reproductions: "+ podcasts.get(i).getReproductedTimes() + "\n";
            count++;
        }

        return report;
    }

    public String reportGenreSoldSong() {
        String report = "";

        double[] genreTotalPrice = new double[Genre.values().length];
        int[] totalSolds = new int[Genre.values().length];

        ArrayList<Song> songs = sortSongs();

        for (Song x : songs) {

            if(x.getGenre().equals(Genre.ROCK)) {
                genreTotalPrice[0] += x.getValue()* x.getSoldTimes();
                totalSolds[0] += x.getSoldTimes();
            }

            if(x.getGenre().equals(Genre.POP)) {
                genreTotalPrice[1] += x.getValue()* x.getSoldTimes();
                totalSolds[1] += x.getSoldTimes();
            }

            if(x.getGenre().equals(Genre.TRAP)) {
                genreTotalPrice[2] += x.getValue()* x.getSoldTimes();
                totalSolds[2] += x.getSoldTimes();
            }

            if(x.getGenre().equals(Genre.HOUSE)) {
                genreTotalPrice[3] += x.getValue()* x.getSoldTimes();
                totalSolds[3] += x.getSoldTimes();
            }
        }

        for (int i = 0; i<genreTotalPrice.length ; i++) {
            report += Genre.values()[i] + "Total solds: " + totalSolds[i] + "Total Sold: " + genreTotalPrice[i] + "\n";
        }

        return report;
    }

    public Song findMostSoldSong() {
        ArrayList<Song> songs = sortSongs();
        Collections.sort(songs, Comparator.comparing(Song::getSoldTimes));

        return songs.get(0);
    }

    public String reportMostSoldSong(Song mostSoldSong) {
        String report = mostSoldSong.getName() + "Total Solds Number: " + mostSoldSong.getSoldTimes() + "Total Sold: " + (mostSoldSong.getSoldTimes()*mostSoldSong.getValue());

        return report;
    }
}



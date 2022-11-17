package ui;

import java.util.Scanner;

import model.NeoTunesController;
import model.Genre;
import model.Category;

public class NeoTunesManager {

    NeoTunesController controller;
    private Scanner sc;

    private Genre songGenres[];
    private Category podcastCategories[];

    public NeoTunesManager() {
         controller = new NeoTunesController();
         sc = new Scanner(System.in);

         songGenres = Genre.values();
         podcastCategories = Category.values();
    }

    public static void main(String[] args){

        NeoTunesManager manager = new NeoTunesManager();
        System.out.println("|-| Welcome To NeoTunes");

        int menuChoice = 0;

        do{
            menuChoice = manager.showMenu();
            manager.menu(menuChoice);

        } while(menuChoice != 0);

    }

    public int showMenu(){
        int menuChoice = 0;

        System.out.print("""
                |-| Menu
                1. Reg Users
                2. Reg Audio
                3. Create Playlist
                4. Edit Playlist
                5. Share Playlist
                0. Close menu
                """);

        System.out.print("Selection: ");
        menuChoice = sc.nextInt();
        sc.nextLine();

        return menuChoice;
    }
    public void menu(int menuChoice){
        switch (menuChoice) {
            case 0 -> System.out.println("Closing Menu...");
            case 1 -> System.out.println(createUser());
            case 2 -> System.out.println(createAudio());
            case 3 -> System.out.println(createPlaylist());
            case 4 -> System.out.println(editPlaylist());
            case 5 -> System.out.println(sharePlaylist());
            default -> System.out.println("Option out of range.");
        }
    }

    public String createUser(){
        System.out.println("|-| Register User");

        String message = "";

        String nickname;
        String id;
        int option;

        System.out.print("nickName: ");
        nickname = sc.nextLine();

        System.out.print("Id: ");
        id = sc.nextLine();

        if (!controller.checkIfIdExists(id)){

            System.out.print("Consumer or Producer [1 or 2]: ");
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    int consumerType;

                    System.out.println("Consumer User");

                    System.out.print("Standard or Premium [1 or 2]: ");
                    consumerType = sc.nextInt();
                    sc.nextLine();

                    controller.createConsumer(consumerType, nickname, id);

                    message = "The user has successfully registered";
                    break;

                case 2:
                    int producerType;
                    String name;
                    String url;

                    System.out.println("Producer User");

                    System.out.print("Name: ");
                    name = sc.nextLine();

                    System.out.print("Url: ");
                    url = sc.nextLine();

                    System.out.print("Arists or Content Creator [1 or 2]: ");
                    producerType = sc.nextInt();
                    sc.nextLine();

                    controller.createProducer(producerType, nickname, id, name, url);

                    message = "The user has successfully registered";
                    break;
            }

        } else {
            message = "A user with this id already exists";
        }

        return message;

    }

    public String createPlaylist() {
        System.out.println("|-| Register Playlist");
        String message = "";

        String name;
        String id;

        int userSelection;

        System.out.println(controller.showConsumers());
        System.out.print("Select user (according to index): ");
        userSelection = sc.nextInt();
        sc.nextLine();
        userSelection--;

        System.out.print("Type the name of the playlist: ");
        name = sc.nextLine();

        System.out.print("Type Id: ");
        id = sc.nextLine();

        if (controller.createPlaylist(userSelection, name, id)){
            message = "The Playlist has successfully registered";

        } else {
            message = "The playlist was not added, the user reached the limit of existing playlists";

        }

        return message;
    }

    public String createAudio(){
        String message = "";

        int option;
        int userSelection;
        String name;
        String url;
        double duration;

        System.out.println("Register Audio");

        System.out.print("You want to add a song or podcast [1 or 2]: ");
        option = sc.nextInt();
        sc.nextLine();

        switch(option) {
            case 1:
                String album;
                double value;
                int genreChoice;

                System.out.println("Register Song");

                System.out.println(controller.showArtists());
                System.out.print("Select User: ");
                userSelection = sc.nextInt();
                userSelection--;
                sc.nextLine();

                System.out.print("Song name: ");
                name = sc.nextLine();

                System.out.print("Url: ");
                url = sc.nextLine();

                System.out.print("Duration: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.print("Album: ");
                album = sc.nextLine();

                System.out.print("Price: ");
                value = sc.nextDouble();
                sc.nextLine();

                System.out.println(showSongGenres());
                System.out.print("Choose genre: ");
                genreChoice = sc.nextInt();
                sc.nextLine();

                controller.createSong(userSelection, name, url, duration, album, value, songGenres[genreChoice-1]);

                message = "The song was successfully added";
                break;

            case 2:
                String description;
                int categorySelection;

                System.out.println("Register Podcast");

                System.out.println(controller.showContentCreators());
                System.out.print("Select User: ");
                userSelection = sc.nextInt();
                sc.nextLine();
                userSelection--;

                System.out.print("Podcast name: ");
                name = sc.nextLine();

                System.out.print("Url: ");
                url = sc.nextLine();

                System.out.print("Duration: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.print("Description: ");
                description = sc.nextLine();

                System.out.println(showPodcastCategories());
                System.out.print("Choose category: ");
                categorySelection = sc.nextInt();
                sc.nextLine();

                controller.createPodcast(userSelection, name, url, duration, description, podcastCategories[categorySelection-1]);

                message = "The Podcast was successfully added";
                break;

            default:
                message = "Option out of range";
                break;
        }

        return message;
    }

    public String editPlaylist() {
        String message = "";

        int consumerSelection;
        int playlistSelection;
        int actionSelection;
        int audioSelection;
        int audioToShow;

        System.out.println("|-| Edit Playlist");

        System.out.println(controller.showConsumers());
        System.out.print("Select user (According to index): ");
        consumerSelection = sc.nextInt();
        consumerSelection--;
        sc.nextLine();

        System.out.print("Add or Delete audio [1 or 2]: ");
        actionSelection = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.showPlaylists(consumerSelection));
        System.out.print("Select playlist (According to index): ");
        playlistSelection = sc.nextInt();
        playlistSelection--;
        sc.nextLine();

        switch(actionSelection) {
            case 1:
                System.out.println("|-| Adding audio to playlist");

                System.out.print("Add song or podcast [1 or 2]: ");
                audioToShow = sc.nextInt();
                sc.nextLine();

                System.out.println(controller.showAudios(audioToShow));
                System.out.print("Select audio to add (According to index): ");
                audioSelection = sc.nextInt();
                audioSelection--;
                sc.nextLine();

                controller.addAudioToPlaylist(playlistSelection, consumerSelection, audioSelection);
                message = "The audio was successfully added";

                break;

            case 2:
                System.out.println("Removing audio from playlist");

                System.out.println(controller.showAudios(consumerSelection, playlistSelection));
                System.out.print("Select Audio to remove (According to index): ");
                audioSelection = sc.nextInt();
                audioSelection--;
                sc.nextLine();

                controller.deleteAudioToPlaylist(playlistSelection, consumerSelection, audioSelection);
                message = "The audio was successfully removed";

                break;
        }

        return message;
    }

    public String sharePlaylist() {
        String message = "";
        int userSelection;
        int playlistSelection;

        System.out.println("Share PlayList");

        System.out.println(controller.showConsumers());
        System.out.print("Select consumer: ");
        userSelection = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.showPlaylists(userSelection-1));
        playlistSelection = sc.nextInt();
        sc.nextLine();

        message = controller.sharePlaylist(userSelection-1, playlistSelection-1);

        return message;
    }

    public String showSongGenres() {
        String message = "";

        for (int i = 0; i<songGenres.length; i++) {
            message += (i + 1) + ". " + songGenres[i] + "\n";
        }

        return message;
    }

    public String showPodcastCategories() {
        String message = "";

        for (int i = 0; i< podcastCategories.length; i++) {
            message += (i + 1) + ". " + podcastCategories[i] + "\n";
        }

        return message;
    }

}

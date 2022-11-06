package ui;

import java.util.Scanner;

import model.NeoTunesController;

public class NeoTunesManager {
    NeoTunesController neotunesObj;

    public Scanner sc = new Scanner(System.in);

    public NeoTunesManager() {
         neotunesObj = new NeoTunesController();
    }

    public static void main(String[] args){
        NeoTunesManager system = new NeoTunesManager();
        
        system.menu();
    }

    public void menu(){
        String message = "";
        int option = 0;

        while(option != 5) {
                System.out.println("MENU");
                System.out.println("1. Agregar usuario");
                System.out.println("2. Agregar audio");
                System.out.println("3. Crear playlist");
                System.out.println("4. Editar playlist");
                System.out.println("5. Cerrar menu");
                System.out.print("Seleccion: ");
                option = sc.nextInt();
                sc.nextLine();

        switch(option) {
            case 1:
                message = createUser();
                break;
            
            case 2:
                message = createAudio();
                break;

            case 3:
                message = createPlaylist();
                break;

            case 4:
                message = editPlaylist();
                break;

            case 5:
                message = "Cerrando menu";
                break;

            default:
                message = "Opcion fuera de rango";
                break;
        }                
        
            System.out.println(message);
        }
        
    }

    public String createUser(){
        String message = "";

        String nickname;
        String id;
        int option;
        
        System.out.println("Agregando usuario");

        System.out.print("nickName: ");
        nickname = sc.nextLine();

        System.out.print("Id: ");
        id = sc.nextLine();

        if (neotunesObj.checkIfIdExists(id)){
            
            System.out.print("Consumidor o Productor (1 o 2): ");
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    int consumerType;

                    System.out.println("Agregando consumidor");

                    System.out.print("Estandar o premium (1 o 2): ");
                    consumerType = sc.nextInt();
                    sc.nextLine();

                    neotunesObj.addUser(neotunesObj.createConsumer(consumerType, nickname, id));
                    
                    message = "Se registro con exito el usuario consumidor";
                    break;

                case 2:
                    int producerType;
                    String name;
                    String url;

                    System.out.println("Agregando productor");

                    System.out.print("Nombre: ");
                    name = sc.nextLine();

                    System.out.print("Url: ");
                    url = sc.nextLine();

                    System.out.print("Artista o creador de contenido (1 o 2): ");
                    producerType = sc.nextInt();
                    sc.nextLine();

                    neotunesObj.addUser(neotunesObj.createProducer(producerType, nickname, id, name, url));

                    message = "Se registro con exito el usuario productor";
                    break;
            }
        
        } else {
            message = "Ya existe un usuario con ese id";
        }

        return message;

    }

    public String createPlaylist() {
        String message = "";

        String name;
        String id;

        int userSeleciton;

        System.out.println("Creando playlist");

        System.out.println(neotunesObj.showConsumers());
        System.out.print("Seleccione el usuario (segun el indice): ");
        userSeleciton = sc.nextInt();
        sc.nextLine();
        userSeleciton--;

        System.out.print("Digite el nombre de la playlist: ");
        name = sc.nextLine();

        System.out.print("Digite el id: ");
        id = sc.nextLine();

        if (neotunesObj.addPlaylistToConsumer(userSeleciton, neotunesObj.createPlaylist(name, id))){
            message = "La playlist se agrego exitosamente";

        } else {
            message = "La playlist no se agregp, el usuario alcanzo el limite de playlist existentes";

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

        System.out.println("Agregando audio");

        System.out.print("Desea agregar una cancion o podcast (1 o 2): ");
        option = sc.nextInt();
        sc.nextLine();

        switch(option) {
            case 1:
                String album;
                double value;
                int genreSelection;

                System.out.println("Agregando cancion");

                System.out.println(neotunesObj.showArtists());
                System.out.print("Seleccione el usuario: ");
                userSelection = sc.nextInt();
                sc.nextLine();
                userSelection--;

                System.out.print("Nombre de la cancion: ");
                name = sc.nextLine();

                System.out.print("Url: ");
                url = sc.nextLine();

                System.out.print("Duracion: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.print("Album: ");
                album = sc.nextLine();

                System.out.print("Precio: ");
                value = sc.nextDouble();
                sc.nextLine();

                System.out.println("1. Rock");
                System.out.println("2. Pop");
                System.out.println("3. Trap");
                System.out.println("4. House");
                System.out.print("Seleccione un genero: ");
                genreSelection = sc.nextInt();
                sc.nextLine();

                neotunesObj.addAudio(userSelection, neotunesObj.createSong(name, url, duration, album, value, genreSelection));

                message = "La cancion se agrego exitosamente";
                break;
            
            case 2:
                String description;
                int categorySelection;

                System.out.println("Agregando Podcast");

                System.out.println(neotunesObj.showContentCreators());
                System.out.print("Seleccione el usuario: ");
                userSelection = sc.nextInt();
                sc.nextLine();
                userSelection--;

                System.out.print("Nombre del podcast: ");
                name = sc.nextLine();

                System.out.print("Url: ");
                url = sc.nextLine();

                System.out.print("Duracion: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.print("Descripcion: ");
                description = sc.nextLine();

                System.out.println("1. Politics");
                System.out.println("2. Entertainment");
                System.out.println("3. Videogames");
                System.out.println("4. Fashion");
                System.out.print("Digite la categoria: ");
                categorySelection = sc.nextInt();
                sc.nextLine();

                neotunesObj.addAudio(userSelection, neotunesObj.createPodcast(name, url, duration, description, categorySelection));
                
                message = "Se agrego el podcast exitosamente";
                break;

            default:
                message = "opcion fuera de rango";
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

        System.out.println("Editando Playlist");

        System.out.println(neotunesObj.showConsumers());

        System.out.print("Seleccione el usuario (segun el indice): ");
        consumerSelection = sc.nextInt();
        consumerSelection--;
        sc.nextLine();

        System.out.print("Agregar o eliminar audio (1 o 2)");
        actionSelection = sc.nextInt();
        sc.nextLine();

        System.out.println(neotunesObj.showPlaylists(consumerSelection));
        System.out.print("Seleccione la playlist (segun el indice): ");
        playlistSelection = sc.nextInt();
        playlistSelection--;
        sc.nextLine();


        switch(actionSelection) {
            case 1:
                System.out.println("Agregando audio a la playlist");

                System.out.println(neotunesObj.showAudios());
                System.out.print("Seleccione el audio que se va a agregar (segun el indice): ");
                audioSelection = sc.nextInt();
                audioSelection--;
                sc.nextLine();

                neotunesObj.addAudioToPlaylist(playlistSelection, consumerSelection, audioSelection);
                message = "El audio se ha agregado con exito";

                break;

            case 2:
                System.out.println("Eliminando audio de la playlist");    

                System.out.println(neotunesObj.showAudios(consumerSelection, playlistSelection));
                System.out.print("Seleccione el audio que se va a eliminar (segun el indice): ");
                audioSelection = sc.nextInt();
                audioSelection--;
                sc.nextLine();

                neotunesObj.deleteAudioToPlaylist(playlistSelection, consumerSelection, audioSelection);
                message = "El audio se ha eliminado con exito";

                break;
        }

        return message;
    }


}

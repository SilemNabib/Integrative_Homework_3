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

        
    }

    public void menu(){

    }

    public String createUser(){
        String message = "";

        String nickname;
        String id;
        int option;
        
        System.out.println("nickName: ");
        nickname = sc.nextLine();

        System.out.println("Id: ");
        id = sc.nextLine();

        if (neotunesObj.checkIfIdExists(id)){
            
            System.out.println("Consumidor o Productor (1 o 2): ");
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    int consumerType;

                    System.out.println("Estandar o premium (1 o 2): ");
                    consumerType = sc.nextInt();
                    sc.nextLine();

                    neotunesObj.addUser(neotunesObj.createConsumer(consumerType, nickname, id));
                    
                    message = "Se registro con exito el usuario consumidor";
                    break;

                case 2:
                    int producerType;
                    String name;
                    String url;

                    System.out.println("Nombre: ");
                    name = sc.nextLine();

                    System.out.println("Url: ");
                    url = sc.nextLine();

                    System.out.println("Artista o creador de contenido (1 o 2): ");
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


        System.out.println(neotunesObj.showConsumers());
        System.out.println("Seleccione el usuario (segun el indice): ");
        userSeleciton = sc.nextInt();
        sc.nextLine();
        userSeleciton--;

        System.out.println("Digite el nombre de la playlist: ");
        name = sc.nextLine();

        System.out.println("Digite el id: ");
        id = sc.nextLine();

        if (neotunesObj.addPlaylistToConsumer(userSeleciton, neotunesObj.createPlaylist(name, id))){
            message = "La playlist se agrego exitosamente";

        } else {
            message = "La playlist no se agregp, el usuario alcanzon el limite de playlist existentes";

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

        System.out.println("Desea agregar una cancion o podcast (1 o 2)");
        option = sc.nextInt();
        sc.nextLine();

        switch(option) {
            case 1:
                String album;
                double value;
                int genreSelection;

                System.out.println(neotunesObj.showArtists());
                System.out.println("Seleccione el usuario: ");
                userSelection = sc.nextInt();
                sc.nextLine();
                userSelection--;

                System.out.println("Nombre de la cancion: ");
                name = sc.nextLine();

                System.out.println("Url: ");
                url = sc.nextLine();

                System.out.println("Duracion: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.println("Album: ");
                album = sc.nextLine();

                System.out.println("Precio: ");
                value = sc.nextDouble();
                sc.nextLine();

                System.out.println("1. Rock");
                System.out.println("2. Pop");
                System.out.println("3. Trap");
                System.out.println("4. House");
                System.out.println("Seleccione un genero: ");
                genreSelection = sc.nextInt();
                sc.nextLine();

                neotunesObj.addAudio(userSelection, neotunesObj.createSong(name, url, duration, album, value, genreSelection));

                message = "La cancion se agrego exitosamente";
                break;
            
            case 2:
                String description;
                int categorySelection;

                System.out.println(neotunesObj.showContentCreators());
                System.out.println("Seleccione el usuario: ");
                userSelection = sc.nextInt();
                sc.nextLine();
                userSelection--;

                System.out.println("Nombre del podcast: ");
                name = sc.nextLine();

                System.out.println("Url: ");
                url = sc.nextLine();

                System.out.println("Duracion: ");
                duration = sc.nextDouble();
                sc.nextLine();

                System.out.println("Descripcion: ");
                description = sc.nextLine();

                System.out.println("1. Politics");
                System.out.println("2. Entertainment");
                System.out.println("3. Videogames");
                System.out.println("4. Fashion");
                System.out.println("Digite la categoria: ");
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


}

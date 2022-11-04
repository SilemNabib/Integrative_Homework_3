package ui;

import java.util.Scanner;

import model.NeoTunesController;


public class NeoTuneManager {
    private NeoTunesController objModel;
    
    public static Scanner sc = new Scanner(System.in);

    public NeoTuneManager() {
        objModel = new NeoTunesController();
    }
    public static void main(String[] args) {
        NeoTuneManager systemManager = new NeoTuneManager();
        systemManager.registerUser();
        systemManager.registerUser();
        systemManager.registerAudio();


    }

    public void registerUser() {
        String nickName;
        String id;
        int option;

        System.out.println("Nickname: ");
        nickName = sc.nextLine();

        System.out.println("Id: ");
        id = sc.nextLine();

        System.out.println("Consumidor o productor (1 o 2): ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1: //Consumer
                
                System.out.println("Estandar o premium (1 o 2): ");
                option = sc.nextInt();
                sc.nextLine();
                
                objModel.addUser(objModel.createUserConsumer(nickName, id, option));
                break;


            case 2: //Producer
                String name;
                String url;

                System.out.println("Nombre: ");
                name = sc.nextLine();
                
                System.out.println("Url: ");
                url = sc.nextLine();

                System.out.println("Artistas o creador de contenido (1 o 2): ");
                option = sc.nextInt();
                sc.nextLine();

                objModel.addUser(objModel.createUserProducer(nickName, id, name, url, option));
                break;
        }

    }

    public void registerAudio() {
        int option;

        String url;
        String name;
        int duration;
            
        
        for (int i = 0; i<objModel.getUsers().size(); i++){

            if (objModel.validateUserProducer(objModel.getUsers().get(i))) {

                System.out.println((i+1)+". "+objModel.getUsers().get(i).getNickName());
            }
        }

        System.out.println("Seleccione el usuario (segun el indice): ");
        option = sc.nextInt();
        sc.nextLine();
        option--;
        
        if (objModel.validateUserArtist(objModel.getUsers().get(option))) {
            String album;
            double value;
            int genreSelection;

            
            System.out.println("Creando Cancion");
            
            System.out.println("Nombre de la cancion: ");
            name = sc.nextLine();

            System.out.println("Duracion de la cancion: ");
            duration = sc.nextInt();
            sc.nextLine();

            System.out.println("Url a la portada: ");
            url = sc.nextLine();

            System.out.println("1. Rock");
            System.out.println("2. Pop");
            System.out.println("3. Trap");
            System.out.println("4. House");
            System.out.println("Seleccione la categoria");
            genreSelection = sc.nextInt();
            sc.nextLine();
            

            System.out.println("Nombre del album: ");
            album = sc.nextLine();

            System.out.println("Valor de la cancion: ");
            value = sc.nextInt();
            sc.nextLine();

            objModel.addAudio(option ,objModel.createAudio(url, name, duration, genreSelection, album, value));
            

        } else if (objModel.validateUserContentCreator(objModel.getUsers().get(option))) {
            
            int categoryOption;
            String description;

            System.out.println("Creando PodCast");

            System.out.println("Nombre del PodCast: ");
            name = sc.nextLine();

            System.out.println("Duracion del PodCast: ");
            duration = sc.nextInt();
            sc.nextLine();

            System.out.println("Url a la portada: ");
            url = sc.nextLine();

            System.out.println("1. Politics");
            System.out.println("2. Entertaiment");
            System.out.println("3. Videogames");
            System.out.println("4. Fashion");
            System.out.println("Seleccione una categoria: ");
            categoryOption = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Descripcion del podcast: ");
            description = sc.nextLine();

            objModel.addAudio(option, objModel.createAudio(url, name, duration, categoryOption, description));
        }
    }
}

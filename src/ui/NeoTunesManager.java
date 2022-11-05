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
        system.createUser();
        system.createUser();
    }

    public void menu(){

    }

    public void createUser(){
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
                    break;
            }
        
        } else {
            System.out.println("Ya existe un usuario con ese id");
        }



    }
}

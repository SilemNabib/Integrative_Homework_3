package model;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Audio> audios;

    private String name;
    private String id;

    public Playlist(String name, String id) {
        audios = new ArrayList<Audio>();

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**Description:
     * @param
     * @return
     */
    public boolean addSong(Audio newSong){
        audios.add(newSong);

        return true;
    }

    public boolean deleteSong(int deleteSong) {
        audios.remove(deleteSong);

        return true;
    }

    public String toString() {
        String message = "";

        message += "Nombre: "+name+"\nId: "+id;

        return message;
    }

    public String audioToString() {
        String message = "";

        for (int i = 0; i<audios.size(); i++){
            message += (i+1)+". "+audios.get(i).getName()+"\n";
        }

        return message;
    }

}

package model;

import java.util.ArrayList;

public abstract class Producer extends User {
    
    private ArrayList<Audio> audios;
    private String name;
    private String url;
    private int totalTime;
    private int totalNumber;


    public Producer(String nickName, String id, String name, String url) {

        super(nickName, id);

        this.name = name;
        this.url = url;
        totalTime = 0;
        totalNumber = 0;

        audios = new ArrayList<Audio>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }


    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }


    public ArrayList<Audio> getAudios(){
        return audios;
    }

    public boolean addAudio(Audio newAudio) {
        audios.add(newAudio);
        return true;
    }

    
    public String toString(){
        String message = "";
        
        message += super.toString() + "Nombre: "+name+"\n";

        return message;
    }

}

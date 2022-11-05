package model;

import java.util.ArrayList;

public abstract class Producer extends User  {
    private ArrayList<Audio> audios;

    private String name;
    private String url;
    private int reproductedTimes;
    private double listenedTime;

    public Producer(String nickname, String id, String name, String url){
        super(nickname, id);
        this.name = name;
        this.url = url;
        
        audios = new ArrayList<Audio>();
        reproductedTimes = 0;
        listenedTime = 0;
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

   
    public int getReproductedTimes() {
        return reproductedTimes;
    }

    public void setReproductedTimes(int reproductedTimes) {
        this.reproductedTimes = reproductedTimes;
    }

  
    public double getListenedTime() {
        return listenedTime;
    }

    public void setListenedTime(double listenedTime) {
        this.listenedTime = listenedTime;
    }

    public boolean addAudioToProducer(Audio newAudio) {
        audios.add(newAudio);
        return true;
    }
}

package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Consumer extends User {
    
    private int totalTimeSong;
    private int totalTimePodcast;
    private Map<String, Integer> genre;
    private Map<String, Integer> category;
    
    public Consumer(String nickName, String id) {
        super(nickName, id);
        totalTimeSong = 0;
        totalTimePodcast = 0;

        genre = new HashMap<String, Integer>();
        category = new HashMap<String, Integer>();
    }


    public int getTotalTimeSong() {
        return totalTimeSong;
    }

    public void setTotalTimeSong(int totalTimeSong) {
        this.totalTimeSong = totalTimeSong;
    }


    public int getTotalTimePodcast() {
        return totalTimePodcast;
    }

    public void setTotalTimePodcast(int totalTimePodcast) {
        this.totalTimePodcast = totalTimePodcast;
    }


    public Map<String, Integer> getGenre() {
        return genre;
    }

    public void setGenre(Map<String, Integer> genre) {
        this.genre = genre;
    }


    public Map<String, Integer> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Integer> category) {
        this.category = category;
    }

}

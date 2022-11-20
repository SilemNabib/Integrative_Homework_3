package model;

public abstract class Audio implements Playable{
    private String name;
    private String url;
    private double duration;
    private int reproductedTimes;

    public Audio (String name, String url, double duration) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        reproductedTimes = 0;
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

    
    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }


    public int getReproductedTimes() {
        return reproductedTimes;
    }

    public void setReproductedTimes(int reproductedTimes) {
        this.reproductedTimes = reproductedTimes;
    }

    @Override
    public void play() {
        reproductedTimes++;
    }
}

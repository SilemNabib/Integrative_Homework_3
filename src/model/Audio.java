package model;

public abstract class Audio {
    private String url;
    private String name;
    private int totalNumber;
    private int duration;

    public Audio(String url, String name, int duration) {
        this.url = url;
        this.name = name;
        totalNumber = 0;
        this.duration = duration;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString(){
        String message = "";

        message += "Nombre: "+name+"\nDuracion: "+duration+"\n";

        return message;
    }
}

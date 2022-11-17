package model;

import java.time.LocalDate;

public abstract class User {
    private String nickname;
    private String id;
    private LocalDate date;

    public User (String nickname, String id) {
        this.nickname = nickname;
        this.id = id;
        date = LocalDate.now();

    }
    

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

}
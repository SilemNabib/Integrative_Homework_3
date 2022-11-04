package model;

public abstract class User {

    private String nickName;
    private String id;

    public User (String nickName, String id){
        
        this.nickName = nickName;
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String userToString() {
        String message = "";

        message += "Nombre de usuario: "+nickName+"\nIdentification: "+id+"\n";

        return message;
    }
}
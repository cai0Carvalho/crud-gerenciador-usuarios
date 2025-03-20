package main.java.model;

import java.util.Objects;

public class Users {
    private int id;
    private String username;
    private String email;

    //contrutores
    public Users(){}
    public Users(String username, String email){
        this.username = username;
        this.email = email;
    }

    //Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername(){ return username; }
    public void setUsername(String username){this.username = username; }
    
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public String toString(){
        return "User{ id= "+ id +  
                ", name= "+ username + '\''+  
                ", email" + email + '\'' + '}'; 
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(username, users.username) && Objects.equals(email, users.email);
    }
    @Override
    public int hashCode(){ return Objects.hash(id, username, email); }
}

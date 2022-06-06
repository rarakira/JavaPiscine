package edu.school21.chat.models;

import java.util.List;

public class User {
    private Integer id;
    private String login;
    private String pass;
    private List<Chatroom> listOfAllChats;
    private List<Chatroom> listOfUserChats;

    public User(Integer id, String login, String pass, List<Chatroom> listOfAllChats, List<Chatroom> listOfUserChats) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.listOfAllChats = listOfAllChats;
        this.listOfUserChats = listOfUserChats;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login +
                "\', password='" + pass +
                "\', listOfAllChats=" + listOfAllChats +
                ", listOfUserChats=" + listOfUserChats +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Chatroom> getListOfAllChats() {
        return listOfAllChats;
    }

    public void setListOfAllChats(List<Chatroom> listOfAllChats) {
        this.listOfAllChats = listOfAllChats;
    }

    public List<Chatroom> getListOfUserChats() {
        return listOfUserChats;
    }

    public void setListOfUserChats(List<Chatroom> listOfUserChats) {
        this.listOfUserChats = listOfUserChats;
    }
}
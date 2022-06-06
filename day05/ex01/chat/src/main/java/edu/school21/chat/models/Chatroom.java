package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private Integer id;
    private String chatName;
    private User owner;
    private List<Message> listOfMessages;

    public Chatroom(Integer id, String chatName, User owner, List<Message> listOfMessages) {
        this.id = id;
        this.chatName = chatName;
        this.owner = owner;
        this.listOfMessages = listOfMessages;
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
        return "Chatroom{" +
                "id=" + id +
                ", chatName='" + chatName +
                "', owner=" + owner +
                ", listOfMessages=" + listOfMessages +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return chatName;
    }

    public void setName(String chatName) {
        this.chatName = chatName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getListOfMessages() {
        return listOfMessages;
    }

    public void setListOfMessages(List<Message> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
}

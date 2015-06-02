package com.mlaffan.giftpal.sqlite.db;

/**
 * Created by Mark on 06/05/2015.
 */
public class Idea {

    private int _id;
    private String idea;

    public Idea(){
    }

    public Idea(String idea){
        this.idea = idea;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}

package com.example.ryo.bowlingscore;

import java.util.ArrayList;
import java.util.Date;

public class Score {
    private int gameId;
    private Date date;
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public int getGameId() {
        return gameId;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void addArrayList(int score) {
        this.arrayList.add(score);
    }

    public void deleteArrayList(int position) {
        this.arrayList.remove(position);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}

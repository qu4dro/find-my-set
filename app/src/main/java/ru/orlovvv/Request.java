package ru.orlovvv;

import java.util.ArrayList;

public class Request {
    String action, nickname;
    int token;
    ArrayList<Card> cards;


    public Request(String action, int token) {
        this.action = action;
        this.token = token;
    }

    public Request(String action, String nickname) {
        this.action = action;
        this.nickname = nickname;
    }

    public static Request RegisterRequest(String nickname, int token) {
        return new Request("register", nickname, token);
    }

    public static Request FetchRequest(int token) {
        return new Request("fetch_cards", token);
    }

    public static Request TakeSetRequest(int token, ArrayList<Card> cards) {
        return new Request("take_set", token, cards);
    }

    public Request(String action, int token, ArrayList<Card> cards) {
        this.action = action;
        this.cards = cards;
        this.token = token;
    }

    public Request(String action, String nickname, int token) {
        this.action = action;
        this.nickname = nickname;
        this.token = token;
    }

}

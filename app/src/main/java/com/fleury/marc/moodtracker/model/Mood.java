package com.fleury.marc.moodtracker.model;

public class Mood {

    // ON STOCK :
    // - L'humeur du jour
    // - Le potentiel commentaire
    // A minuit on récupère ces infos puis on vide les variables

    private int mood;
    private String comment;

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

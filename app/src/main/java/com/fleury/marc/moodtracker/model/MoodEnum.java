package com.fleury.marc.moodtracker.model;

public enum MoodEnum {

    Sad(0), Disappointed(1), Normal(2), Happy(3), SuperHappy(4);

    private int mood;

    MoodEnum(int mood) {
        this.mood = mood;
    }

    public int getMood() {
        return mood;
    }
}

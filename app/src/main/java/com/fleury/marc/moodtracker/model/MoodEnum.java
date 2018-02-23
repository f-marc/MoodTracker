package com.fleury.marc.moodtracker.model;

public enum MoodEnum {

    SAD(0), DISAPPOINTED(1), NORMAL(2), HAPPY(3), SUPERHAPPY(4);

    private int mood;

    MoodEnum(int mood) {
        this.mood = mood;
    }

    public int getMood() {
        return mood;
    }
}

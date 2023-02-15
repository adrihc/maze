package com.liceu.maze.services;

public class CalculateTime {
    public int seconds(long result){
        int seconds = 0;
        for (long i = result; i >= 1000 ; i = i - 1000) {
            seconds++;
        }
        return seconds;
    }

    public int minutes(int seconds){
        int minutes = 0;
        for (int i = seconds; i >=60 ; i = i - 60) {
            minutes++;
        }
        return minutes;
    }

    public int hores(int minutes){
        int hores = 0;
        for (int i = minutes; i >= 60 ; i = i-60) {
            hores++;
        }
        return hores;
    }
}

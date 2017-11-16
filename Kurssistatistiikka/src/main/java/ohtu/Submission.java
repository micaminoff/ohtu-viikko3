package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private int hours;
    private ArrayList<Integer> exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public int getHours() {
        return this.hours;
    }
    
    public void setExercises(ArrayList exersises) {
        this.exercises = exersises;
    }
    public ArrayList getExercises() {
        return this.exercises;
    }

    @Override
    public String toString() {
        String ex = "";
        for (int e : this.exercises) {
            ex += e + " ";
        }
        return "Week " + this.week + ": Excercises done: " +
                this.exercises.size() + ", Duration: " + this.hours
                + ", Excercises completed: " + ex;
    }
    
}
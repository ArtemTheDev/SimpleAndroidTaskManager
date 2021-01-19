package com.artemthedev.simpleandroidtaskmanager.DB;

public class NoteDate {
    public int year, month, day, hours, min, sec;

    public NoteDate (int year, int month, int day, int hours, int min, int sec) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.min = min;
        this.sec = sec;
    }

    @Override
    public String toString() {
        return String.format( "%s-%s-%s-%s-%s-%s", this.year, addZeros(2, Integer.toString(this.month)), addZeros(2, Integer.toString(this.day)), addZeros(2, Integer.toString(this.hours)),
                addZeros(2, Integer.toString(this.min)), addZeros(2, Integer.toString(this.sec)));
    }

    public String getYear() {
        return buildString(0, 3);
    }

    private String buildString (int from, int to) {
        StringBuilder result = new StringBuilder();
        for (int i = from; i <= to; i++) {
            result.append(this.toString().charAt(i));
        }
        return result.toString();
    }

    private String addZeros (int len, String s) {
        int LenDifference = len - s.length();
        StringBuilder builder = new StringBuilder();
        while (LenDifference > 0) {
            LenDifference--;
            builder.append("0");
        }
        return (builder.toString() + s);
    }
}

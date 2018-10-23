package com.test.enumeration;


import java.util.EnumSet;

public enum Season {
    SPRING("春天", 1), SUMMER("夏天", 2), FALL("秋天", 3), WINTER("冬天", 4);


    private String name;
    private int ordinal;

    Season(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }
}

class Main{
    public static void main(String[] args) {
        EnumSet<Season> seasonsSet = EnumSet.allOf(Season.class);

        for (Season season: seasonsSet) {
            System.out.println(season);
        }
    }
}

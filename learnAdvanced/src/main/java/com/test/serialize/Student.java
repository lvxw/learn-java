package com.test.serialize;

import java.io.Serializable;

public class Student extends Person  implements Serializable {
    private static final long serialVersionUID = 1L;

    private static String id = "5420114874123822410";
    double score;
    private transient String initDate;

    public Student(String name, int age, double score, String initDate) {
       super(name,age);
        this.score = score;
        this.initDate = initDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("score=").append(score);
        sb.append(", initDate='").append(initDate).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}

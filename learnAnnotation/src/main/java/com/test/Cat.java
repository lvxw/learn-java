package com.test;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@interface ValidateInt {
    int maxLength();
    int minLength();
}

public class Cat {

    private String name;

    @ValidateInt(minLength = 0, maxLength = 150)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws IllegalAccessException {
        Cat cat = new Cat();
        cat.setName("楼楼");
        cat.setAge(11);

        Class<? extends Cat> clazz = cat.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                ValidateInt annotation = field.getDeclaredAnnotation(ValidateInt.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    int value = field.getInt(cat);
                    if (value < annotation.minLength() || value > annotation.maxLength()) {
                        System.out.println("Error");
                    } else{
                        System.out.println("OK");
                    }
                }
            }
        }
    }

}

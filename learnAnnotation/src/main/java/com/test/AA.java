package com.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    enum Priority {LOW, MEDIUM, HIGH}
    enum Status {STARTED, NOT_STARTED}
    String author() default "lvxw";
    Priority priority() default Priority.LOW;
    Status status() default Status.NOT_STARTED;
}


public class AA{
    @Todo(priority = Todo.Priority.HIGH, author = "zs")
    public void incompleteMethod(){
        System.out.println("应用注解");
    }

    public static void main(String[] args) throws Exception{
        Class<AA> aCls = AA.class;
        Method method = aCls.getDeclaredMethod("incompleteMethod", null);

        Todo annotation = method.getAnnotation(Todo.class);
        if(annotation != null){
            System.out.println(annotation.author());
            System.out.println(annotation.priority());
            System.out.println(annotation.status());
        }
    }
}




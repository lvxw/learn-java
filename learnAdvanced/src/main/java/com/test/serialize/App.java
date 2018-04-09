package com.test.serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class App {
    public static void main(String[] args) throws  Exception{
        String path = "./tmp/object";
        Person person = new Person();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(person);
    }
}

package com.test.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App {

    /**
     * 序列化
     * @param person
     * @param path
     * @throws Exception
     */
    private static void serialize(Student person, String path) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(person);
        oos.close();
    }

    /**
     * 反序列化
     * @param path
     * @throws Exception
     */
    private static Object deserialize(String path) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
    public static void main(String[] args) throws Exception {
        String path = "./tmp/object";
        Student student = new Student("zhangsan",23,99.5,"20180410");

        System.out.println(student);
        serialize(student,path);
        Student deStudent = (Student) deserialize(path);
        System.out.println(deStudent);
    }
}

package com.test.genericParadigm;


class MyUtil{
    public static <T> int CountEleNum(T[] arr, T ele){
        int count = 0;
        for (T e: arr) {
            if(ele == e){
                count+=1;
            }
        }
        return  count;
    }
}

class MyUtil2{
    public static <T extends Comparable<T>> int CountEleNum(T[] arr, T ele){
        int count = 0;
        for (T e: arr) {
            if(ele.compareTo(e) >0){
                count+=1;
            }
        }
        return  count;
    }
}

class Person implements Comparable{
    private String name;
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

    @Override
    public int compareTo(Object o) {
        Person o1 = (Person) o;
        return o1.age - this.age;
    }
}

public class App3 {
    public static void main(String[] args) {
        Person[] arr = {new Person(), new Person(), new Person(), new Person()};
        int count = MyUtil2.CountEleNum(arr, new Person());
        System.out.println(count);
    }
}

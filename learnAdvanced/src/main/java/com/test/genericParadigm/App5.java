package com.test.genericParadigm;

import java.util.ArrayList;
import java.util.List;

class Fruit{

}
class Apple extends Fruit{

}
class Orange extends Fruit{

}
class Banana{

}



public class App5 {
    public static void main(String[] args) {
        List<? extends Fruit> li = new ArrayList<>();
//        li.add(new Apple());
//        Fruit fruit = li.get(0);

        List<? super Fruit> li2 = new ArrayList<>();
        li2.add(new Apple());
        Object object = li2.get(0);

    }


}

package databaseproject;

import io.cucumber.java.sl.In;

import java.util.*;

public class odev2 {


    public static void main(String[] args) {

        List<Integer> myList = Arrays.asList( 4, 6, 5, -10, 8, 5, 20);


       myMethod(myList,10);

    }

    static void myMethod(List <Integer>check, Integer b){
        List<Integer>myList = new ArrayList<>(check);
        for (int i=0;i<myList.size();i++) {
            if(myList.contains(b-myList.get(i))){
                System.out.println(myList.get(i)+" and "+ (b-myList.get(i)));
                myList.remove(Integer.valueOf(myList.get(i)));
            }
        }
    }

    }


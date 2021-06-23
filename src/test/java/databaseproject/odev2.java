package databaseproject;

import io.cucumber.java.sl.In;

import java.util.*;

public class odev2 {


    public static void main(String[] args) {

        List<Integer> myList = Arrays.asList( 4, 6, 5, -10, 8, 5, 20);


       myMethod(myList,10);

    }

    static void myMethod(List <Integer>check, Integer b){
        for (Integer a: check) {
            if(check.contains(b-a)){
                System.out.println(a+" and "+ (b-a));
            }
        }
    }

    }


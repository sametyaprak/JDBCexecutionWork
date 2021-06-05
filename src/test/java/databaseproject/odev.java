package databaseproject;

import java.util.Arrays;
import java.util.Scanner;

public class odev {


    public static void main(String[] args) {

        //ipek();
        System.out.print(isArmstrong(155));
        //System.out.print(isArmstrong2(153));

    }
     static boolean  isArmstrong (int number){
        int numberTest = number;
        boolean result = false;
        int sum = 0;
        int digit = 0;
        while (number > 0) {
            digit = number%10;
            sum  += (int)Math.pow(digit,3);
            number = number / 10;
        }
        if(sum==numberTest) result=true;
        return result;
    }
    static void ipek(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Armstrong sayı olup olmadığını kontrol etmek için lütfen bir sayı giriniz ");
        int sayi=scan.nextInt();
        int kupToplam=0;
        int sayiBozulmasin=sayi;
        int kontrol=0;
        while (sayiBozulmasin>0) {
            kontrol=sayiBozulmasin%10;//3 //5 //1
//       kupToplam= kupToplam+kontrol*kontrol*kontrol;
            kupToplam+=(int) Math.pow(kontrol, 3);
            sayiBozulmasin=sayiBozulmasin/10;
        }
        if(sayi==kupToplam) {
            System.out.println(sayi+" bir Armstrong sayıdır");
        }else {
            System.out.println(sayi+" bir Armstrong sayı değildir");
        }
    }


    static public boolean isArmstrong2(Integer num) {
        int[] numbers = Integer.toString(num).chars().map(t -> t-'0').toArray();
        int sum = Arrays.stream(numbers).map(t-> (int)            Math.pow(t,3)).reduce(Integer::sum).getAsInt();
        return num==sum;
    }

}


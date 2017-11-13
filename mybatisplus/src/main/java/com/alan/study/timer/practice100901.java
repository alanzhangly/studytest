package com.alan.study.timer;

import java.util.Random;
import java.util.Scanner;
public class practice100901 {

//    public static void main(String[] args) {
//        System.out.println("Enter a letter grade: ");
//        Scanner input = new Scanner(System.in);
//        String letter =input.next();
//        int level = 0;
////        char A = 4;char B = 3;char C = 2;char D = 1;char F = 0;
//        switch (letter){
//            case "A" : level  = 4; break;
//            case "B" : level  = 3; break;
//            case "C" : level  = 2; break;
//            case "D" : level  = 1; break;
//            case "F" : level  = 0; break;
//
//            default:
//                System.out.println("无效输出");
//                return;
//        }
//        System.out.println("The numeric value for grade "+letter+" is "+level);
//
//    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double integer = input.nextDouble();
        double n1 = 0; double n2 = 0; double sum1 = 0; double sum2 = 0;

        while(integer != 0){
            if(integer > 0) {
                double in1 = input.nextDouble();
                n1 = n1 +1;
                sum1 += integer;
            }
            if(integer < 0) {
                double in2 = input.nextDouble();
                n2 = n2 +1;
                sum2 += integer;
            }
            integer = input.nextDouble();
        }

        System.out.println("The number of positives is "+n1);
        System.out.println("The number of negatives is "+n2);
        System.out.println("The total is "+(sum1+sum2));
        System.out.println("The average is "+(sum1+sum2)/(n1+n2));
        input.close();

    }

    }


package com.alan;

/**
 * Created by Ke Zhang on 2017/10/24.
 */
public class Test {

    class MyThread extends Thread{
        @Override
        public void run() {
            for (int i= 0; i<100;i++){
                System.out.println(this.getName() +": " + i);
            }
        }
    }

    public static void main(String[] args){
        Test test = new Test();
//        MyThread t1 =  new MyThread();

    }
}

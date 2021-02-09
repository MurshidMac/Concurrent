package com.westminster.models;

import java.util.Random;

public class STUDENT_PROCESS extends COMMON_PROCESS implements Runnable {

    private SHARED_LASER_PRINTER shared_laser_printer;
    Random randomObj=new Random();

    public STUDENT_PROCESS(String threadName, ThreadGroup group, SHARED_LASER_PRINTER printer) {
        super(threadName, group);
        this.shared_laser_printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Document doc = new Document(this.getName(), "DOCUMENT "+i + i, RandomPrintPageCounterGenerator());
            this.shared_laser_printer.printDocument(doc);
            System.out.println(this);

            try {
                java.lang.Thread.sleep(RandomThreadSleepTimeGenerator());

            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "STUDENT_PROCESS{" +
                "shared_laser_printer=" + shared_laser_printer +
                '}';
    }

    public int RandomPrintPageCounterGenerator(){
        int randomPageCount=this.randomObj.nextInt(25 - 10 + 1) + 10;
        return randomPageCount;

    }

    public int RandomThreadSleepTimeGenerator(){
        int randomThreadSleepTime=this.randomObj.nextInt(2000 - 1000 + 1) + 1000;
        return randomThreadSleepTime;
    }

}


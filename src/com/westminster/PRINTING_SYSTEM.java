package com.westminster;

import com.westminster.models.SHARED_LASER_PRINTER;
import com.westminster.models.STUDENT_PROCESS;

/**
 *
 *
 */

public class PRINTING_SYSTEM {

    public static void main(String[] args) {

        ThreadGroup STUDENT_SET = new ThreadGroup("STUDENT_PROCESS_SET_THREAD_GROUP");
        ThreadGroup TECHNICIAN_SET = new ThreadGroup("TECHNICIAN_PROCESS_SET_THREAD_GROUP");

        SHARED_LASER_PRINTER cannon_SHARED_laser_printer = new SHARED_LASER_PRINTER("IP-CG-24","CANNON_LASER_PRINTER", STUDENT_SET, null);

        STUDENT_PROCESS one = new STUDENT_PROCESS("one", STUDENT_SET, cannon_SHARED_laser_printer);
        STUDENT_PROCESS two = new STUDENT_PROCESS("two", STUDENT_SET, cannon_SHARED_laser_printer);
        STUDENT_PROCESS three = new STUDENT_PROCESS("three", STUDENT_SET, cannon_SHARED_laser_printer);
        STUDENT_PROCESS four = new STUDENT_PROCESS("four", STUDENT_SET, cannon_SHARED_laser_printer);

//        Student student2 = new Student("B", studentGroup, cannon_SHARED_laser_printer);
//        Student student3 = new Student("C", studentGroup, cannon_SHARED_laser_printer);
//        Student student4 = new Student("D", studentGroup, cannon_SHARED_laser_printer);

        Technician paperTechnician = new PaperTechnician("T1",technicianGroup, cannon_SHARED_laser_printer);
        Technician tonerTechnician = new TonerTechnician("T2",technicianGroup, cannon_SHARED_laser_printer);

        // INITIATE PROCESS OF FOUR
        Thread firstStudent = new Thread(one);
        firstStudent.start();
        Thread secondStudent = new Thread(two);
        secondStudent.start();
        Thread thirdStudent = new Thread(three);
        thirdStudent.start();
        Thread fourthStudent = new Thread(four);
        fourthStudent.start();

//        one.start();
//        two.start();
//        three.start();
//        four.start();

        paperTechnician.start();
        tonerTechnician.start();

        // wait for all the threads to complete
        try {
            firstStudent.join();
            secondStudent.join();
            thirdStudent.join();
            fourthStudent.join();
            paperTechnician.join();
            tonerTechnician.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }


    }
}

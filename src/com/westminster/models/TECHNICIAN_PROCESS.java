package com.westminster.models;

public class TECHNICIAN_PROCESS extends COMMON_PROCESS {
    protected SHARED_LASER_PRINTER shared_laser_printer;

    TECHNICIAN_PROCESS(String name, ThreadGroup group, SHARED_LASER_PRINTER printerset) {
        super(name, group);
        this.shared_laser_printer = printerset;
    }
}

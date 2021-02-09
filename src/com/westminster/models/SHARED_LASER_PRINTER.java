package com.westminster.models;
import com.westminster.interfaces.ServicePrinter;


public class SHARED_LASER_PRINTER implements ServicePrinter{

    private String laserPrinterId;
    private String manufactureName ;
    private int paperCountLevel;
    private int tonerInkLevel;
    private int documentPrintedCounts = 0;
    private ThreadGroup laszerAllowedGroup;

    public SHARED_LASER_PRINTER(String id, String name, ThreadGroup group, ServicePrinter custom){
        this.laserPrinterId = id;
        this.manufactureName = name;
        if(custom == null){
            this.paperCountLevel = ServicePrinter.Full_Paper_Tray;
            this.tonerInkLevel = ServicePrinter.Full_Toner_Level;
        }else {
            this.paperCountLevel = custom.Full_Paper_Tray;
            this.tonerInkLevel = custom.Full_Toner_Level;
        }
        this.laszerAllowedGroup = group;
    }

    private boolean hasCheckPaperCount(int documentPages) {
        return paperCountLevel >= documentPages || tonerInkLevel >= documentPages;
    }


    @Override
    public synchronized void printDocument(Document document) {
        System.out.println("INITIATING LASER PRINTING");
        int printingCount = document.getNumberOfPages();
        while (!hasCheckPaperCount(printingCount)) {
            try {
                System.out.println("NEED PRINTING RESOURCE");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("START PRINTING DOCUMENT");
        paperCountLevel -= printingCount;
        tonerInkLevel -= printingCount;
        documentPrintedCounts += 1;

        System.out.println("PRINTING " + document.getUserID() + "Document " + document.getDocumentName() + " Number" + document.getNumberOfPages());
        this.toString();
        System.out.println("DONE");
        notifyAll();

    }

    private boolean checkTonerInkLevel() {
        return tonerInkLevel > ServicePrinter.Minimum_Toner_Level;
    }

    private boolean currentlyBlocked() {
        return laszerAllowedGroup.activeCount() > 0;
    }


    @Override
    public synchronized void replaceTonerCartridge() {
        System.out.println("REPLACE TONER REQUEST");
        while (checkTonerInkLevel()) {
            try {
                if (currentlyBlocked()) {
                    System.out.println("WAITING FOR TONER");
                    wait(5000);
                } else {
                    System.out.println("STUDENT EXIT");
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!checkTonerInkLevel()) {
            System.out.println("REPLACING TONER");
            tonerInkLevel = ServicePrinter.Full_Toner_Level;
            System.out.println("DONE TONER");
        }

        System.out.println("TONER REPLACE REQUEST DONE");
        notifyAll();
    }

    private boolean extraPapersInTray() {
        return paperCountLevel + ServicePrinter.SheetsPerPack > ServicePrinter.Full_Paper_Tray;
    }

    @Override
    public synchronized void refillPaper() {
        System.out.println("PAPER REPLACE REQUEST");
        while (extraPapersInTray()) {
            try {
                if (currentlyBlocked()) {
                    System.out.println("WAIT FOR PAPER");
                    wait(5000);
                } else {
                    System.out.println("STUDENT EXIT");
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!extraPapersInTray()) {
            System.out.println("REPLACE PAPERS");
            tonerInkLevel += ServicePrinter.SheetsPerPack;
            System.out.println("PAPER REPLACE DONE");
            this.toString();
        }
        System.out.println("REPLACE PAPER PROCESS DONE");
        notifyAll();
    }

    @Override
    public String toString() {
        return "LASER_PRINTER{" +
                "laserPrinterId='" + laserPrinterId + '\'' +
                ", manufactureName='" + manufactureName + '\'' +
                ", paperCountLevel=" + paperCountLevel +
                ", tonerInkLevel=" + tonerInkLevel +
                ", documentPrintedCounts=" + documentPrintedCounts +
                '}';
    }
}

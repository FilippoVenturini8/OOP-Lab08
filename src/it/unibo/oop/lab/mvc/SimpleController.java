package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class SimpleController implements Controller {
    private String toPrint;
    private final List<String> printHistory;

    public SimpleController() {
        this.printHistory = new ArrayList<>();
    }

    /**
     * @param s string that will be printed
     */
    public void nextPrint(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("The string can't be null!");
        }
        this.toPrint = s;
        this.printHistory.add(s);
    }

    /**
     * @return return the next string will be printed
     */
    public String getNextPrint() {
        return this.toPrint;
    }

    /**
     * @return list of all the string printed
     */
    public List<String> getPrintHystory() {
        return this.printHistory;
    }

    /**
     * @exception if the string is unset throws exception
     */
    public void printString() {
        if (this.toPrint == null) {
            throw new IllegalStateException("The string to print is unset!");
        }
        System.out.println(this.toPrint);
    }

}

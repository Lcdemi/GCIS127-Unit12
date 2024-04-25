package unit12.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Timer implements Runnable {
    private String name;
    private List<Integer> list;
    private int numberOfValues;

    public Timer(String name, List<Integer> list, int numberOfValues) {
        this.name = name;
        this.list = list;
        this.numberOfValues = numberOfValues;
    }

    public static void getSystemTime(String name, List<Integer> list, int numberOfValues) {
        long startSystemTime = System.nanoTime();
        for(int i = 0; i < numberOfValues; i++) {
            list.add(null);
        }
        long finalSystemTime = System.nanoTime();
        System.out.println("Filled " + name + " with " + String.format("%,d in %,d nanoseconds.", numberOfValues, finalSystemTime-startSystemTime));
    }

    @Override
    public void run() {
        getSystemTime(name, list, numberOfValues);
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> vector = new Vector<>();
        Timer arrayCounter = new Timer("ArrayList", arrayList, 100000);
        Thread arrayThread = new Thread(arrayCounter);
        Timer vectorCounter = new Timer("Vector", vector, 100000);
        Thread vectorThread = new Thread(vectorCounter);
        arrayThread.start();
        vectorThread.start();
    }
}

/*
 * 1. Create a new Java class named "Timer.java" in the practicum package. 
   Define a static method that declares parameters for a String name, a 
   List<Integer>, and a numberOfValues.  
    a. Use the static System.nanoTime() method to get the current system time. 
    b. Use a loop to add the specified numberOfValues to the list. 
    c. Use the static System.nanoTime() method to compute the elapsed time 
       in nanoseconds. 
    d. Print a message indicating how long it took to fill the list in the 
       format "Filled <name> with <numberOfValues> in <elapsed time> 
       nanoseconds." Hint: you can use String.format("%,d", number) to include 
       commas in large numbers for readability.
    e. Define a main method with the appropriate signature and use it to 
       create a thread that times an java.util.ArrayList and a second thread 
       that times a java.util.Vector with the same number of values. The 
       threads must run concurrently. Depending on the speed of your processor, 
       you may need to use a somewhat large number of values, e.g. 100,000,000. 
       What do you see?
    f. Read the online documentation for both classes and use your class 
       JavaDoc comment to explain why you think there is a difference in the 
       time it takes to fill the lists with the same number of values.
 */
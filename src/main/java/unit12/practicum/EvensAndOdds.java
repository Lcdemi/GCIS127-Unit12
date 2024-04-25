package unit12.practicum;

public class EvensAndOdds implements Runnable {
    private Object lock;
    private int start;

    public EvensAndOdds(int start, Object lock) {
        this.start = start;
        this.lock = lock;
    }
    
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        EvensAndOdds odds = new EvensAndOdds(1, obj);
        EvensAndOdds evens = new EvensAndOdds(2, obj);
        Thread oddThread = new Thread(odds);
        Thread evenThread = new Thread(evens);
        oddThread.start();
        evenThread.start();
    }

    @Override
    public void run() {
        while(start < 101) {
            synchronized(lock) {
                System.out.println(start);
                start += 2;
                lock.notify();
                if(start > 100) {
                    break;
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
}

/*
 * 2. Create a new Java class named "EvensAndOdds.java" and define a main 
   method with the appropriate signature. 
    a. Start a thread that prints all of the odd numbers from 1 to 99.
    b. Start a second thread that prints all of the even numbers from 2 to 100.
    c. Using only wait(), and notify()/notifyAll() make sure that the threads 
       are guaranteed to print all of the numbers from 1 to 100 in order.
    d. The program should not hang after all of the numbers have printed.
    e. You may create additional classes to solve this problem, but you are 
       not required to do so.
    f. Hints: 
        i) The threads will need to share a lock that they can use to wait 
           and notify each other.
        ii) The main method must guarantee that the odds thread starts 
            and prints its first number before starting the evens thread.
 */

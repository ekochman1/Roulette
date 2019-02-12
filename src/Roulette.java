import java.util.Random;
import java.util.Scanner;
import java.util.*;


 public class Roulette {

     static double totalwager = 0.0;
     static double winnings = 0.0;

     public static void main(String[] args) {

         long startTime = System.nanoTime();
         //Scanner
         Scanner input = new Scanner(System.in);
         System.out.println("Enter number of threads you want to run: ");
         //User Input
         int num_thread = input.nextInt();

         //thread count at max
         int n = num_thread;
         int runtotal = 1000000;
         int runs = runtotal/n;
         int extra = runtotal%n;
         for(int j = 0; j< runs; j++) {
             for (int i = 0; i < n; i++) {
                 //Random generator between 0-36
                 totalwager += 10.0;
                 Wheel object = new Wheel(10);

                 object.start();
                 try {
                     object.join();
                 } catch (InterruptedException e) {
                 }

             }
         }
         for (int k = 0; k < extra ; k++) {
             //Random generator between 0-36
             totalwager += 10.0;
             Wheel object = new Wheel(10);

             object.start();
             try {
                 object.join();
             } catch (InterruptedException e) {
             }

         }
         long endTime = System.nanoTime();
         long timeElapsed = endTime - startTime;
         System.out.println(timeElapsed);
         System.out.println("Total waged: " + totalwager);
         System.out.println("Total won: " + winnings);
         double RTP = (winnings/totalwager * 100.0);
         System.out.printf("RTP: " + RTP);
     }


     static class Wheel extends Thread {
         int wager;
         static int slotnumber;

         Wheel(int wager) {
             this.wager = wager;

         }

         public void run() {
             Random generator = new Random();
             slotnumber = generator.nextInt(37);
             //Thread and Number output
             //System.out.println("Thread " + (Thread.currentThread().getId() - 11) + ": " + slotnumber);
             if (slotnumber < 18) {
                 winnings += 10.0*2;
             }

         }

     }
 }


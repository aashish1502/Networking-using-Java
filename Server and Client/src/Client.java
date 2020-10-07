/*
 * This is the Client code for an Asynchronous Java server Client.
 * In this we use Datagram Sockets to send packets of data through both server and client.
 * I aim to create this to explore the features of java sockets.
 * Made by - HighTide <github> www.github.com/aashish/1502.
 * */


import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        DatagramSocket cs = new DatagramSocket(9991);
        InetAddress ip = InetAddress.getByName("192.168.43.170");

        System.out.println("The Client is running...");
        System.out.println("The Client is Online....");

        System.out.println("-----------------------------------");
        System.out.println("Welcome!");
        System.out.println("-----------------------------------");
        System.out.println("To close the Connection type bye.");

        Thread csend;

        csend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner sc = new Scanner(System.in);

                    while (true) {
                        synchronized (this) {
                            byte[] sd = new byte[1000];
                            sd = sc.nextLine().getBytes();

                            DatagramPacket sp = new DatagramPacket(sd,sd.length,ip,8080);

                            cs.send(sp);

                            String msg = new String(sd);
                            //System.out.println("Person B says: "+ msg);
                            // exit condition
                            if (msg.toLowerCase().trim().equals("bye")) {
                                System.out.println("Person B "
                                        + "exiting... ");
                                break;
                            }


                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("An exception occured!");
                    e.printStackTrace();
                }
            }
        });

        Thread crecieve;
        crecieve = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (this) {
                            byte[] rd = new byte[1000];
                            DatagramPacket sp1 = new DatagramPacket(rd,rd.length);
                            cs.receive(sp1);
                            String message = (new String(rd)).trim();
                            System.out.println();
                            System.out.println("Person B (" + sp1.getPort() + "): " +message );

                            if (message.toLowerCase().trim().equals("bye")) {
                                System.out.println("The Connection was ended by Person A.");
                                break;
                            }

                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("An exception Occured");
                    e.printStackTrace();
                }
            }
        });

        csend.start();
        crecieve.start();

        csend.join();
        crecieve.join();


    }

}
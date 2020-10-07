/*
* This is the Server code for an Asynchronous Java server Client.
* In this we use Datagram Sockets to send packets of data through both server and client.
* I aim to create this to explore the features of java sockets.
* Made by - HighTide <github> www.github.com/aashish/1502.
* */

import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException{

        DatagramSocket ss = new DatagramSocket(8080);
        InetAddress ip = InetAddress.getByName("192.168.43.170");
        System.out.println(ip);

        System.out.println("Running Chat Server sider...");
        System.out.println("The Server is up....");

        System.out.println("-----------------------------------");
        System.out.println("Welcome!");
        System.out.println("-----------------------------------");
        System.out.println("To close the Connection type bye.");

        Thread ssend;
        ssend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner sc = new Scanner(System.in);

                    while (true) {
                        synchronized (this) {
                            byte[] sd = new byte[1000];

                            sd = sc.nextLine().getBytes();

                            DatagramPacket sp = new DatagramPacket(sd, sd.length, ip, 9991);

                            ss.send(sp);
                            String message = new String(sd);
                            //System.out.println("The server says: " + message);

                            if ((message).equals("bye")) {
                                System.out.println("Server"
                                        + " exiting... ");
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

        Thread srecieve ;
        srecieve = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        synchronized (this) {
                            byte[] rd = new byte[1000];
                            DatagramPacket sp1 = new DatagramPacket(rd, rd.length);
                            ss.receive(sp1);

                            String message = (new String(rd)).trim();
                            System.out.println();
                            System.out.println("Person A (" + sp1.getPort() + "): " +message );

                            if(message.toLowerCase().trim().equals("bye")) {
                                System.out.println("The Connection was ended by Person B.");
                                break;
                            }

                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("An Exception has occured!");
                    e.printStackTrace();
                }

            }
        });

        ssend.start();
        srecieve.start();

        ssend.join();
        srecieve.join();

    }

}


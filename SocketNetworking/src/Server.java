/*
 * This is a Server side Socket Programing code
 * The Server listens to the port for any input steams
 * then it reads and output the stream to the display.
 * Made by : HighTide <github> www.github.com/aashish1502
 * */

import java.net.*;
import java.io.*;

public class Server
{
    //initialize socket and input stream 
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    // constructor with port 
    public Server(int port)
    {

        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");


            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));


            String line = "";


            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");


            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
} 
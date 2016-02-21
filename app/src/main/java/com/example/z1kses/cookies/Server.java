package com.example.z1kses.cookies;

/**
 * Created by Z1kseS on 22.02.2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);

        while (true) {

            System.out.println("Waiting for clients...");
            Socket client = serverSocket.accept();
            System.out.println(client.toString());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));

            String outputLine = "";
            Scanner sc = new Scanner(System.in);

            while (!outputLine.equals("печеньки")) {
                outputLine = sc.nextLine();
                out.println(outputLine);
                out.flush();
            }
            out.close();
        }
    }

    public static void main(String[] args) {
        int portNumber = 4444;

        try {
            Server socketServer = new Server(portNumber);
            socketServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
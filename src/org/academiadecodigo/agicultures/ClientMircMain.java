package org.academiadecodigo.agicultures;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMircMain implements Runnable{

    private Socket serverSocket;
    private Scanner sc = new Scanner(System.in);

    private BufferedReader in;
    private BufferedWriter out;
    private static int port;
    private static String ip;

    public ClientMircMain()  {
        System.out.printf("Which server do you want to connect to?");
        ip = sc.nextLine();
        System.out.printf("Insert Port:");
        port = sc.nextInt();
        try {
            serverSocket = new Socket(ip,port);
            out = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread();
        thread.start();
    }
    public static void main(String[] args) {
            new ClientMircMain();
    }

    @Override
    public void run() {
        String msg;
        try {
            while (serverSocket.isConnected()) {
                msg = in.readLine();
                System.out.printf(msg);
            }
        }catch (IOException e) {

        }
    }
}

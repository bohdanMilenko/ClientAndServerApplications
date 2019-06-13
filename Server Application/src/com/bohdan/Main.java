package com.bohdan;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(5000)){
            while (true){
                Socket newSocket = serverSocket.accept();
                SessionCreator session = new SessionCreator(newSocket);
                session.start();
            }

        }catch (IOException e){
            System.out.println("Server Exception is: " + e.getMessage());
        }
    }
}

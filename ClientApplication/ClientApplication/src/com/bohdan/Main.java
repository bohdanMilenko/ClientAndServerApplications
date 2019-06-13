package com.bohdan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

            try(Socket clientSocket = new Socket("127.0.0.1",5000)){
                clientSocket.setSoTimeout(5000);
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter responseSender = new PrintWriter(clientSocket.getOutputStream(),true);

                Scanner scanner = new Scanner(System.in);
                String stringToServer;
                String responseFromServer;

                do{
                    System.out.println("Please enter a string below:");
                    stringToServer = scanner.nextLine();
                    responseSender.println(stringToServer);
                    if(!stringToServer.equalsIgnoreCase("quit")){
                        responseFromServer= responseReader.readLine();
                        System.out.println(responseFromServer);
                    }

                }while (!stringToServer.equalsIgnoreCase("quit"));



            }
            catch (SocketTimeoutException e){
                System.out.println("Socket timeout: " + e.getMessage());
            }
            catch (IOException e){
                System.out.println("Server Exception is: " + e.getMessage());
            }

    }
}

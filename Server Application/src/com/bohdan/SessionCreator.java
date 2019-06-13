package com.bohdan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SessionCreator extends  Thread{

    private Socket socket;

    public SessionCreator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Server is connected");
            while (true){
                String inputString = inputReader.readLine();
                if(inputString.equalsIgnoreCase("quit")){
                    System.out.println("Disconnection from server...");
                    break;
                }
                outputWriter.println("Server sends a response: " + inputString);
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                socket.close();
            }catch (IOException e){

            }
        }
    }
}

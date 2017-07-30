package com.karekent.ballgame;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Server
{
   public static void main(String args[]) throws Exception
   {
         DatagramSocket serverSocket = new DatagramSocket(9876);
         String data = "/serd:123456/mert:425256/thth:424526/cryg:960516/hane:123456/tomb:234526/ertd:345326/";
         byte[] receiveData = new byte[1024];
         byte[] sendData = new byte[1024];
            
         while(true)
         {
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             ServerResponder serverResponder = new ServerResponder(serverSocket,receivePacket);
             data = serverResponder.receive(data);
       
             serverResponder.send(data);
         }
      }

   
}
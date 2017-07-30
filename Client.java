package com.karekent.ballgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class Client
{
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket clientSocket = null;
    Random rd = new Random();
    InetAddress IPAddress = null;
    private int DATA_LENGTH = 12;
    private byte[] sendData = new byte[DATA_LENGTH];
    private byte[] receiveData = new byte[1024];
    private String receiveGameData = null;
    private String sendGameData = null;
    private long ping  = 0;
	public Client(){
		
	new Thread()
	{
		@SuppressWarnings({ "unused", "resource" })
		public void run()
		{
			
	try {
		clientSocket = new DatagramSocket();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		IPAddress = InetAddress.getByName("localhost");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
      while(true)
      {
    	  long mls = System.currentTimeMillis();
    	  sendData = sendGameData.getBytes();
    	 
    	  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
    	  try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    	  try {
			clientSocket.receive(receivePacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  ping = (System.currentTimeMillis()-mls);
    	  
    	  receiveGameData = new String(receivePacket.getData());
    	  
      }
		}
	}.start();
   }
	public String getReceiveGameData() {
		return receiveGameData;
	}
	public void setReceiveGameData(String receiveGameData) {
		this.receiveGameData = receiveGameData;
	}
	public String getSendGameData() {
		return sendGameData;
	}
	public long getPing() {
		return ping;
	}
	public void setPing(long ping) {
		this.ping = ping;
	}
	public void setSendGameData(String sendGameData) {
		this.sendGameData = sendGameData;
	}
}

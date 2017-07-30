package com.karekent.ballgame;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ServerResponder implements Runnable{

	DatagramSocket socket = null;
	DatagramPacket packet = null; 
	private final byte DATA_LENGTH = 12;
	private String data = "";
	byte[] receiveData = new byte[DATA_LENGTH];
	byte[] sendData = new byte[1024];
	StringMethod str = new StringMethod();
	
	public ServerResponder(DatagramSocket socket,DatagramPacket packet){
		this.socket = socket;
		this.packet = packet;
	}
	public String receive(String data)
	{
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sentence = new String(packet.getData());
		String username = sentence.substring(1,5);
		
        int index = str.divideMethod(data,username,"/", ":");
        data = str.changeStringPart(data,sentence.substring(0,DATA_LENGTH),index,index+DATA_LENGTH-1);
        return data;
	}
	public void send(String data) throws IOException
	{
        InetAddress IPAddress = packet.getAddress();
        int port = packet.getPort();
        sendData = data.getBytes();
        DatagramPacket sendPacket =
        new DatagramPacket(sendData, sendData.length, IPAddress, port);
        socket.send(sendPacket);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

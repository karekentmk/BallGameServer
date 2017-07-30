package com.karekent.ballgame;

import java.util.HashMap;


public class UserDataDecoder {

	private HashMap<String,Ball> hashMap;
	private String id = "";
	public final int DATA_LENGTH = 14;
	public final int USERID_LENGTH = 4;
	public final int USERDATA_LENGTH = 6;
	public final String firstIndex = "/";
	public final String lastIndex = ":";
	public final int CORDINATE_DATA_LENGTH = 3;
	public UserDataDecoder(HashMap<String,Ball> hashMap,String id)
	{
		this.hashMap = hashMap;
		this.id = id;
	}
	public void updateUsersData(String data,Ball myBall)
	{
		String[] userData =  data.split(firstIndex);
		for(int i = 1;i < userData.length;i++)
		{
			int index = userData[i].indexOf(lastIndex);
			if(index != -1)
			{
				String userId = userData[i].substring(0,index);
				String userMainData = userData[i].substring(index+1,userData[i].length());
				if(userId.equals(id))
				{
					hashMap.put(id,myBall);
				}
				else
				{
					Ball ball = new Ball();
					int x = Integer.parseInt(userMainData.substring(0,3));
					int y = Integer.parseInt(userMainData.substring(3,6));
					ball.setX(x);
					ball.setY(y);
					hashMap.put(userId,ball);
				}
			}
		}
		
	}
	public String sendData()
	{
		String cordinateX = "" + hashMap.get(id).getX();
		String cordinateY = "" + hashMap.get(id).getY();
		int xLength = Integer.toString(hashMap.get(id).getX()).length();
		int yLength = Integer.toString(hashMap.get(id).getY()).length();
		if(xLength != CORDINATE_DATA_LENGTH )
		{
			for(int i=0; i < CORDINATE_DATA_LENGTH - xLength;i++)
			{
				cordinateX = "0" + cordinateX; 
			}
		}
		if(yLength !=CORDINATE_DATA_LENGTH)
		{
			for(int i=0; i < CORDINATE_DATA_LENGTH - yLength;i++)
			{
				cordinateY = "0" + cordinateY; 
			}
		}
	
		return firstIndex + id + lastIndex + cordinateX+cordinateY;
	}
	
	
}

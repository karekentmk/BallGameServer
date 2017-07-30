package com.karekent.ballgame;


public class StringMethod {

	public StringMethod()
	{
		
	}
	public int divideMethod(String data,String findingValue,String firstIndex,String lastIndex)
	{
		String tempValue = firstIndex + findingValue + lastIndex;
		int index = data.indexOf(tempValue);
		return index;
	}
	public String changeStringPart(String data,String changingValue,int firstIndex,int lastIndex)
	{
		String temp = data.substring(0, firstIndex)+changingValue+data.substring(lastIndex+1,data.length());
		return temp;
	}
	
}

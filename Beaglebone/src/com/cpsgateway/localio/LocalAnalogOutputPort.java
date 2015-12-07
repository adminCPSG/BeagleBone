package com.cpsgateway.localio;

public class LocalAnalogOutputPort {
	
	private String LAOPortName;
	public int currentValue;
	public int prevValue;
	
	public LocalAnalogOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {	
		LAOPortName = portName + ":";
		System.out.println(LAOPortName + " Instantiated");
		currentValue = 0;
		prevValue = 0;	
	}
	
	public int getValue() {
		//System.out.println("Info: " + LAOPortName + " getValue = " + currentValue);
		return currentValue;
	}
	
	public void setValue() {
		this.setValue(currentValue);
	}
		
	public boolean setValue(int value) {
		//System.out.println("Info: " + LAOPortName + " setValue = " + value);
		prevValue = currentValue;
		currentValue = value;
		return true;
	}
}

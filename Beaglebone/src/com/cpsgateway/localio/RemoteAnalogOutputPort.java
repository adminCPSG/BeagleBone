package com.cpsgateway.localio;


public class RemoteAnalogOutputPort {
	
	private String RAOPortName;
	public int currentValue;
	public int prevValue;
	
	public RemoteAnalogOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		RAOPortName = portName + ":";
		currentValue = 0;
		prevValue = 0;
	}
	
	public int getValue() {
		System.out.println("Info: " + RAOPortName + currentValue);
		return currentValue;
	}
	
	public void setValue() {
		this.setValue(currentValue);
	}
		
	public void setValue(int value) {
		//System.out.println("Info: " + RAOPortName + value);
		prevValue = currentValue;
		currentValue = value;
	}
}

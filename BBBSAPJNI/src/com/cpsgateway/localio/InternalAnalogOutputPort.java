package com.cpsgateway.localio;


public class InternalAnalogOutputPort {
	
	private String IAOPortName;
	public int currentValue;
	public int prevValue;
	
	public InternalAnalogOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		IAOPortName = portName + ":";
		currentValue = 0;
		prevValue = 0;
	}
		
	public int getValue() {
		//System.out.println("Info: " + IAOPortName + currentValue);
		return currentValue;
	}
	
	public void setValue() {
		this.setValue(currentValue);
	}

	public void setValue(int value) {
		//System.out.println("Info: " + IAOPortName + value);
		prevValue = currentValue;
		currentValue = value;
	}
}

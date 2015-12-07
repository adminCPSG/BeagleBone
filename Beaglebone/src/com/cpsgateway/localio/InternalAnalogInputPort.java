package com.cpsgateway.localio;


public class InternalAnalogInputPort {
	
	private String IAIPortName;
	public int currentValue;
	public int prevValue;
	
	public InternalAnalogInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		IAIPortName = portName + ":";
		currentValue = 0;
		prevValue = 0;
	}
	
	
	public int getValue() {
		//System.out.println("Info: " + IAIPortName + currentValue);
		prevValue = currentValue;
		return currentValue;
	}
}

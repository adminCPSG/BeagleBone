package com.cpsgateway.localio;


public class RemoteAnalogInputPort {
	
	private String RAIPortName;
	public int currentValue;
	public int prevValue;
	
	public RemoteAnalogInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		RAIPortName = portName + ":";
		currentValue = 0;
		prevValue = 0;
	}
	
	
	public int getValue() {
		//System.out.println("Info: " + RAIPortName + currentValue);
		prevValue = currentValue;
		return currentValue;
	}
}

package com.cpsgateway.localio;

public class RemoteDigitalOutputPort {
	
	private String RDOPortName;
	public boolean currentValue;
	public boolean prevValue;
	
	public RemoteDigitalOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		
		RDOPortName = portName + ":";
		getValue();
		currentValue = false;
		prevValue = false;
	}
	
	
	public boolean getValue() {
		prevValue = currentValue;
		return currentValue;
	}
	
	public void setValue() {
		this.setValue(currentValue);
	}
		
	public void setValue(boolean value) {
		//System.out.println("Info: " + RDOPortName + value);
		prevValue = currentValue;
		currentValue = value;
	}
}

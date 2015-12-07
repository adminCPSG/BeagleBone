package com.cpsgateway.localio;

public class InternalDigitalOutputPort {
	
	private String IDOPortName;
	public boolean currentValue;
	public boolean prevValue;
	
	public InternalDigitalOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		
		IDOPortName = portName + ":";
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
		//System.out.println("Info: " + IDOPortName + value);
		prevValue = currentValue;
		currentValue = value;
	}
}

package com.cpsgateway.localio;

public class RemoteDigitalInputPort {
	
	private String RDIPortName;
	public boolean currentValue;
	public boolean prevValue;
	
	public RemoteDigitalInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		
		RDIPortName = portName + ":";
		getValue();
		currentValue = false;
		prevValue = false;
	}
	
	
	public boolean getValue() {
		//System.out.println("Info: " + RDIPortName + currentValue);
		prevValue = currentValue;
		return currentValue;
	}
}

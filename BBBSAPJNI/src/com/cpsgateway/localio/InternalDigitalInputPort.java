package com.cpsgateway.localio;

public class InternalDigitalInputPort {
	
	private String IDIPortName;
	public boolean currentValue;
	public boolean prevValue;
		
		public InternalDigitalInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
			
			IDIPortName = portName + ":";
			getValue();
			currentValue = false;
			prevValue = false;
		}
		
		
		public boolean getValue() {
			//System.out.println("Info: " + IDIPortName + currentValue);
			prevValue = currentValue;
			return currentValue;
		}
}

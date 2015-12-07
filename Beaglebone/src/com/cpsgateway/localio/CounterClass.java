package com.cpsgateway.localio;

public class CounterClass {
	public String counterName;
	public int counterNumber;
	private int counterVal, setValue;
	private boolean prevState;
	
	public CounterClass(String ctrName) {
		counterName = ctrName;
		String subWords[] = ctrName.split("_");
		if(subWords[1] != null)
			counterNumber = Integer.valueOf(subWords[1]);
		counterVal = 0;
	}
	
	public boolean setCounter(int setpoint) {
		setValue = setpoint;
		counterVal = 0;
		return true;
	}
	
	public boolean execute(boolean state) {
		if(state == true) {
			if(prevState == false ) {
				counterVal++;
			}
		}
		prevState = state;
		System.out.format("%s : counterValue = %d : setValue = %d\n", counterName, counterVal, setValue);
		if(counterVal >= setValue)
			return true;
		else
			return false;
	}
}

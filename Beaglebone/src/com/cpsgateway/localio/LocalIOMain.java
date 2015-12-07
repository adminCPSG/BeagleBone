package com.cpsgateway.localio;


public class LocalIOMain {


	public static void main(String[] args) {
		
		LocalIOInit.LocalIOInitialize();
		
		LocalIOInit.localDIPorts.get(0).getValue();
		LocalIOInit.localDIPorts.get(1).getValue();
		LocalIOInit.localDIPorts.get(2).getValue();
		LocalIOInit.localDIPorts.get(3).getValue();
		
		
		LocalIOInit.localDOPorts.get(0).getValue();
		LocalIOInit.localDOPorts.get(1).getValue();
		LocalIOInit.localDOPorts.get(2).getValue();
		LocalIOInit.localDOPorts.get(3).getValue();
		
		boolean value0 = false, prevValue0=false;
		boolean value1 = false, prevValue1=false;
		
		//while(true) {
			value0 = LocalIOInit.localDIPorts.get(0).getValue();
			int adcval = LocalIOInit.localAIPorts.get(0).getValue();
			System.out.println("ADC Value = " + Integer.toString(adcval));
			
			value1 = LocalIOInit.localDIPorts.get(1).getValue();
			if((value0 == true) && (prevValue0 == false)) {
				LocalIOInit.localDOPorts.get(3).setValue(true);
			}
			if((value1 == true) && (prevValue1 == false)) {
				LocalIOInit.localDOPorts.get(3).setValue(false);
			}
			
			prevValue0 = value0;
			prevValue1 = value1;
			
		//}
	}
}

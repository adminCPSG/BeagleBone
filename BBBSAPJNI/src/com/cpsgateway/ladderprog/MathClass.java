package com.cpsgateway.ladderprog;

public class MathClass {
	
	public static boolean fnMath(LadderConst.PortTypes fnName, String[] param, boolean booleanValue) {
		int intValue1 = 0;
		int intValue2 = 0;
		int result = 0;
		
		if(booleanValue == false)
			return false;

		intValue1 = LadderProgExecution.getIntValueFromIO(param[0]);
		intValue2 = LadderProgExecution.getIntValueFromIO(param[1]);
		System.out.println("Info: Math Function:" + intValue1 + "/" + intValue2 + "/" + param[2]);
		switch(fnName) {
			case ADD:
				result = intValue1 + intValue2;
				break;
			case MINUS:
				result = intValue1 - intValue2;
				break;
			case MULTIPLY:
				result = intValue1 * intValue2;
				break;
			case DIVIDE:
				result = intValue1 / intValue2;
				break;
			default:
				return(false);
		}
		LadderProgExecution.setIntValueToIO(param[2], result);
		return(true);
	}
}

package com.cpsgateway.ladderprog;

public class CompareClass {
	
	public static boolean fnComparator(LadderConst.PortTypes fnName, String[] param, boolean booleanValue) {
		int intValue1 = 0;
		int intValue2 = 0;
		
		if(booleanValue == false)
			return false;
		intValue1 = LadderProgExecution.getIntValueFromIO(param[0]);			
		intValue2 = LadderProgExecution.getIntValueFromIO(param[1]);

		System.out.println("Info: Comparator Function:" + intValue1 + "/" + intValue2);
		switch(fnName) {
			case EQUAL:
				return(intValue1 == intValue2);
			case GREATER:
				return(intValue1 > intValue2);
			case LESSTHAN:
				return(intValue1 < intValue2);
			case GREATEROREQUAL:
				return(intValue1 >= intValue2);
			case LESSTHANOREQUAL:
				return(intValue1 <= intValue2);
			default:
			return(false);
		}
	}

}

package com.cpsgateway.ladderprog;

public class LadderConst {
	
	//Opcode Constants
	public static enum Opcodes {
			END,
			ER,
			LD,
			LDN,
			ST,
			STN,
			AND,
			ANDN,
			OR,
			ORN
		}
	
	
	//Port Type Constants
	public static enum PortTypes {
			CONST,
			LDI, RDI, IDI,
			LDO, RDO, IDO,
			LAI, RAI, IAI,
			LAO, RAO, IAO,
			TMR, CTR,
			ADD, MINUS, MULTIPLY, DIVIDE,
			EQUAL, GREATER, LESSTHAN, GREATEROREQUAL, LESSTHANOREQUAL,
			SAPIN, SAPOUT,
			PWM
		}

	//Data Type Constants
	public static enum DataTypes {
			BOOLEAN_TYPE,
			INT_TYPE,
			FLOAT_TYPE,
			TMR_TYPE,
			CTR_TYPE
		}
	
	//Timer Modes
	public static enum timerModes {
		ON,
		OFF,
		CUM
	}
}

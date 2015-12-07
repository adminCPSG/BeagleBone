package com.cpsgateway.ladderprog;

import com.cpsgateway.localio.CounterClass;
import com.cpsgateway.localio.TimerClass;

public class LadderStatement {
	LadderConst.Opcodes opcode;
	LadderConst.PortTypes portType;
	int portNo;
	FnSapOut fnSapOut;
	TimerClass timer;
	CounterClass counter;
	String param[];
	
	LadderStatement(LadderConst.Opcodes opcodeVal) {
		opcode = opcodeVal;
		portNo = -1;
		portType = null;
		fnSapOut = null;
		timer = null;
		counter = null;
		param = null;
	}

	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portVal) {
		opcode = opcodeVal;
		portType = portVal;
		portNo = 0;
		fnSapOut = null;
		timer = null;
		counter = null;
		param = null;
	}
	
	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portTypeVal, int portNoVal) {
		opcode = opcodeVal;
		portNo = portNoVal;
		portType = portTypeVal;
		fnSapOut = null;
		timer = null;
		counter = null;
		param = null;
	}
	
	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portTypeVal, FnSapOut fnSapOutVal) {
		opcode = opcodeVal;
		portType = portTypeVal;
		portNo = 0;
		fnSapOut = fnSapOutVal;
		timer = null;
		counter = null;
		param = null;
	}
	
	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portTypeVal, int portNoVal, TimerClass timerObj) {
		opcode = opcodeVal;
		portType = portTypeVal;
		portNo = portNoVal;
		timer = timerObj;
		counter = null;
		param = null;
	}
	
	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portTypeVal, int portNoVal, CounterClass counterObj) {
		opcode = opcodeVal;
		portType = portTypeVal;
		portNo = portNoVal;
		timer = null;
		counter = counterObj;
		param = null;
	}
	
	LadderStatement(LadderConst.Opcodes opcodeVal, LadderConst.PortTypes portTypeVal, String[] strVal) {
		opcode = opcodeVal;
		portType = portTypeVal;
		portNo = 0;
		fnSapOut = null;
		timer = null;
		counter = null;
		param = new String[strVal.length];
		for(int i=0; i < strVal.length; i++)
			param[i] = strVal[i];
	}
}

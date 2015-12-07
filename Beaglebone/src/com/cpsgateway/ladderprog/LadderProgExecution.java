package com.cpsgateway.ladderprog;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import com.cpsgateway.localio.LocalIOInit;

public class LadderProgExecution {
	
	
	public static void ladderProgExecute() {
		
		Deque<Boolean> ladderBooleanStack = new ArrayDeque<Boolean>();
		
		Iterator<LadderStatement> iterator = ReadLadderProgFile.ladderProgBin.iterator();
		
		while(iterator.hasNext()) {
			LadderStatement lStatement = iterator.next();
			Boolean booleanValue = true;
			Boolean booleanValue2 = true;


			LadderConst.Opcodes opcode = lStatement.opcode;
			System.out.println("Processing opcode: " + opcode);
			
			if((opcode != LadderConst.Opcodes.LD) && (opcode != LadderConst.Opcodes.LDN)) {
				if(!ladderBooleanStack.isEmpty()) {
					booleanValue = ladderBooleanStack.pop();
					//System.out.println(" Not LD  poping stack");
				}
			}
			switch(opcode) {
				case ER: // End Rung
					ladderBooleanStack.clear();
					break;
				case LD: // LD <port>
				case LDN: // LDN <port>
					booleanValue = getBinaryValue(lStatement, booleanValue);
					if(opcode == LadderConst.Opcodes.LDN)
							booleanValue = !booleanValue;
					ladderBooleanStack.push(booleanValue);
					System.out.println("LD " + lStatement.portType+":"+lStatement.portNo + ":Value = " + booleanValue);
					break;
				case AND: // AND <port>
				case ANDN: //ANDN <port>
					if(lStatement.portNo == -1) {
						if(!ladderBooleanStack.isEmpty()) {
							booleanValue2 = ladderBooleanStack.pop();
							System.out.println("AND No Port " +  ":Value = " + booleanValue2);
						} else {
							System.out.println("AND No Port Stack is empty");
						}
					} else {
						booleanValue2 = getBinaryValue(lStatement, booleanValue);
					}
					if(opcode == LadderConst.Opcodes.ANDN)
						booleanValue2 = !booleanValue2;
					if((lStatement.portType == LadderConst.PortTypes.CTR) || (lStatement.portType == LadderConst.PortTypes.TMR)) {
						booleanValue = booleanValue2;
					} else {
						booleanValue &= booleanValue2;
					}
					ladderBooleanStack.push(booleanValue);					
					System.out.println("AND " + lStatement.portType+":"+lStatement.portNo + ":Value = " + booleanValue);
					break;
				case OR: // OR <port>
				case ORN: //ORN <port>
					if(lStatement.portNo == -1) {
						if(!ladderBooleanStack.isEmpty())
							booleanValue2 = ladderBooleanStack.pop();
					} else {
						booleanValue2 = getBinaryValue(lStatement, booleanValue);
					}
					if(opcode == LadderConst.Opcodes.ORN)
						booleanValue2 = !booleanValue2;
					if((lStatement.portType != LadderConst.PortTypes.CTR) && (lStatement.portType != LadderConst.PortTypes.TMR))
						booleanValue |= booleanValue2;
					ladderBooleanStack.push(booleanValue);
					System.out.println("OR " + lStatement.portType + ":"+lStatement.portNo + ":Value = " + booleanValue);
					break;
				case ST: // ST <port>
				case STN: // STN <port>
					if(opcode == LadderConst.Opcodes.STN)
						booleanValue = !booleanValue;
					setOutputValue(lStatement, booleanValue);
					System.out.println("ST " + lStatement.portType+":"+lStatement.portNo + ":Value = " + booleanValue);
					break;
				default:
					System.out.println("Error: LadderProgExecution: Unknown opcode: " + lStatement.opcode);
					break;
			}
		}
	}
	
	private static boolean getBinaryValue(LadderStatement lStatement, boolean curBooleanValue) {
		LadderConst.PortTypes portType = lStatement.portType;
		int portNo = lStatement.portNo;
		if(portType == null) {
			return false;
		} else {
			switch(portType) {
				case LDI:
					return(LocalIOInit.localDIPorts.get(portNo).currentValue);
				case RDI:
					return(LocalIOInit.remoteDIPorts.get(portNo).currentValue);
				case IDI:
					return(LocalIOInit.internalDIPorts.get(portNo).currentValue);
				case LDO:
					return(LocalIOInit.localDOPorts.get(portNo).currentValue);
				case RDO:
					return(LocalIOInit.remoteDOPorts.get(portNo).currentValue);
				case IDO:
					return(LocalIOInit.internalDOPorts.get(portNo).currentValue);
				case TMR:
					return(lStatement.timer.execute(curBooleanValue));
				case CTR:
					return(lStatement.counter.execute(curBooleanValue));
				case EQUAL:
				case GREATER:
				case LESSTHAN:
				case GREATEROREQUAL:
				case LESSTHANOREQUAL:
					return(CompareClass.fnComparator(portType, lStatement.param, curBooleanValue));
				default:
					return false;
			}
		}
	}
	
	private static boolean setOutputValue(LadderStatement lStatement, boolean booleanValue) {
		LadderConst.PortTypes  portType = lStatement.portType;
		int portNo = lStatement.portNo;
		switch(portType) {
			case LDO:
				LocalIOInit.localDOPorts.get(portNo).currentValue= booleanValue;
				return true;
			case RDO:
				LocalIOInit.remoteDOPorts.get(portNo).currentValue= booleanValue;
				return true;
			case IDO:
				LocalIOInit.internalDOPorts.get(portNo).currentValue= booleanValue;
				return true;
			case ADD:
			case MINUS:
			case MULTIPLY:
			case DIVIDE:							
				return(MathClass.fnMath(portType, lStatement.param, booleanValue));
			case PWM:
				break;
			case SAPIN:
				break;
			case SAPOUT:
				lStatement.fnSapOut.fnSapOutExecute(booleanValue);
				return true;
			default:
				return false;
		}
		
		return false;
	}

	public static int getIntValueFromIO(String port) {
		if(port == null)
			System.out.println("Error: getIntValueFromIO port Value is null");
		else
			System.out.println("Info: getIntValueFromIO port Value is: " + port);
		String subWords[] = port.split("_");
	 	if(subWords.length == 2) {
	 		try {
	 			LadderConst.PortTypes portTypeObj = LadderConst.PortTypes.valueOf(subWords[0]);
	 			int portNo = Integer.valueOf(subWords[1]);
	 			switch(portTypeObj) {
					case LAI:
						return(LocalIOInit.localAIPorts.get(portNo).currentValue);
					case RAI:
						return(LocalIOInit.remoteAIPorts.get(portNo).currentValue);
					case IAI:
						return(LocalIOInit.internalAIPorts.get(portNo).currentValue);
					case LAO:
						return(LocalIOInit.localAOPorts.get(portNo).currentValue);
					case RAO:
						return(LocalIOInit.remoteAOPorts.get(portNo).currentValue);
					case IAO:
						return(LocalIOInit.internalAOPorts.get(portNo).currentValue);
					case TMR:
						return(LocalIOInit.timers.get(portNo).currentValue);
					default:
						return 0;
	 			}
	 		} catch (IllegalArgumentException ex) {
				System.out.println("Error: getIntValueFromIO: unable to parse Ignoring entire line -" );
			}
	 	} else {
	 		try { // It is constant
	 		    int retValue = Integer.parseInt(port);
	 		    return retValue;
	 		} catch (NumberFormatException e) {
	 		    System.out.println("Wrong number");
	 		    return(0);
	 		}
	 	}
	 	return (0);
	}
	
	public static boolean setIntValueToIO(String port, int value) {
		if(port == null)
			System.out.println("setIntValueToIO port Value is null");
		else
			System.out.println("setIntValueToIO port Value is: " + port);
		String subWords[] = port.split("_");
	 	if(subWords.length == 2) {
	 		LadderConst.PortTypes portType = LadderConst.PortTypes.valueOf(subWords[0]);
			int portNo = Integer.valueOf(subWords[1]);
			if(portType == null) {
				return false;
			} else {
				switch(portType) {
					case LAO:
						return(LocalIOInit.localAOPorts.get(portNo).setValue(value));
					case TMR:
						return(LocalIOInit.timers.get(portNo).setTimer(value));
					case CTR:
						return(LocalIOInit.counters.get(portNo).setCounter(value));
					default:
						return false;
				}
			}
	 	}
	 	return false;
	}
}

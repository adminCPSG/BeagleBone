package com.cpsgateway.localio;

import java.util.concurrent.TimeUnit;

import com.cpsgateway.ladderprog.LadderConst;

public class TimerClass {
	public String timerName;
	public int timerNumber;
	private LadderConst.timerModes mode;
	private long curTimeSec, recordTimeSec, cumSecs, setSecs;
	private boolean prevState;
	public int currentValue;
	
	public TimerClass(String tmrName) {
		timerName = tmrName;
		String subWords[] = tmrName.split("_");
		if(subWords[1] != null)
			timerNumber = Integer.valueOf(subWords[1]);
		curTimeSec = 0;
		recordTimeSec = 0;
		mode = LadderConst.timerModes.ON;
		prevState = false;
	}
	
	public void initTimer(int setpointSecs, String opMode) {
		setSecs = (long)setpointSecs;
		curTimeSec = 0;
		recordTimeSec = 0;
		cumSecs = 0;
		mode = LadderConst.timerModes.valueOf(opMode);
		prevState = false;
		currentValue = 0;
	}
	
	public boolean setTimer(int setpointSecs) {
		setSecs = ((long)setpointSecs/4);
		curTimeSec = 0;
		recordTimeSec = 0;
		cumSecs = 0;
		currentValue = 0;
		return true;
	}
	
	public boolean execute(boolean state) {
		long timeMillis = System.currentTimeMillis();
		curTimeSec = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
		
		System.out.println("In Timer: " + timerNumber + " recordTime = " + recordTimeSec + "Cur Time = " + curTimeSec);
		boolean oldState = prevState;
		prevState = state;
		
		switch(mode) {
			case ON:
				if(state == true) {
					if(oldState == false ) {
						recordTimeSec = curTimeSec;
					} else {
						long elapsedTime = curTimeSec-recordTimeSec;
						currentValue = (int)elapsedTime;
						System.out.println("Timer: elapsed time = " + currentValue);
						if((recordTimeSec > 0) && ((curTimeSec-recordTimeSec) >= setSecs))
							return true;
					}
				} else {
					recordTimeSec = 0;
				}
				return false;
			case OFF:
				if(state == false) {
					if(oldState == true ) {
						recordTimeSec = curTimeSec;
						return true;
					} else {
						if(recordTimeSec > 0) {
							if((curTimeSec-recordTimeSec) >= setSecs) {
								return false;
							} else {
								return true;
							}
						} else {
							return false;
						}
					}
				} else {
					recordTimeSec = 0;
					return true;
				}
			case CUM:
				if(state == true) {
					if(oldState == false ) {
						recordTimeSec = curTimeSec;
					} else {
						cumSecs += (curTimeSec-recordTimeSec);
					} 
				}
				System.out.println("Cum Sec = " + cumSecs);
				if(recordTimeSec > 0) {
					if (cumSecs >= setSecs) {
						return true;
					} else {
							return false;
					}
				} else {
					return false;
				}
			
			default:
				return state;
		}
	}
}

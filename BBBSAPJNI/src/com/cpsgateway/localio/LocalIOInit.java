package com.cpsgateway.localio;

import java.util.ArrayList;

public class LocalIOInit {
	
	public static ArrayList<LocalDigitalInputPort> localDIPorts;
	public static ArrayList<RemoteDigitalInputPort> remoteDIPorts;
	public static ArrayList<InternalDigitalInputPort> internalDIPorts;
	
	public static ArrayList<LocalDigitalOutputPort> localDOPorts;
	public static ArrayList<RemoteDigitalOutputPort> remoteDOPorts;
	public static ArrayList<InternalDigitalOutputPort> internalDOPorts;
	
	public static ArrayList<LocalAnalogInputPort> localAIPorts;
	public static ArrayList<RemoteAnalogInputPort> remoteAIPorts;
	public static ArrayList<InternalAnalogInputPort> internalAIPorts;
	
	public static ArrayList<LocalAnalogOutputPort> localAOPorts;
	public static ArrayList<RemoteAnalogOutputPort> remoteAOPorts;
	public static ArrayList<InternalAnalogOutputPort> internalAOPorts;
	
	public static ArrayList<TimerClass> timers;
	public static ArrayList<CounterClass> counters;
	public static boolean simulationMode;
	

	public static void LocalIOInitialize() {
		initLDIPorts();
		initRDIPorts();
		initIDIPorts();
		
		initLDOPorts();
		initRDOPorts();
		initIDOPorts();
		
		initLAIPorts();
		initRAIPorts();
		initIAIPorts();
		
		initLAOPorts();
		initRAOPorts();
		initIAOPorts();
		
		initTimers();
		initCounters();
	}
	
	public static void initLDIPorts() {
		localDIPorts = new ArrayList<LocalDigitalInputPort>();
		localDIPorts.add(new LocalDigitalInputPort("LDI_00", 0, 9, 11, 30));
		localDIPorts.add(new LocalDigitalInputPort("LDI_01", 1, 9, 12, 60));
		localDIPorts.add(new LocalDigitalInputPort("LDI_02", 2, 9, 13, 31));
		localDIPorts.add(new LocalDigitalInputPort("LDI_03", 3, 9, 15, 48));
	}
	
	public static void initRDIPorts() {
		remoteDIPorts = new ArrayList<RemoteDigitalInputPort>();
		remoteDIPorts.add(new RemoteDigitalInputPort("RDI_00", 0, 0, 0, 0));
		remoteDIPorts.add(new RemoteDigitalInputPort("RDI_01", 1, 0, 0, 0));
		remoteDIPorts.add(new RemoteDigitalInputPort("RDI_02", 2, 0, 0, 0));
		remoteDIPorts.add(new RemoteDigitalInputPort("RDI_03", 3, 0, 0, 0));
	}
	
	public static void initIDIPorts() {
		internalDIPorts = new ArrayList<InternalDigitalInputPort>();
		internalDIPorts.add(new InternalDigitalInputPort("LDI_00", 0, 0, 0, 0));
		internalDIPorts.add(new InternalDigitalInputPort("LDI_01", 1, 0, 0, 0));
		internalDIPorts.add(new InternalDigitalInputPort("LDI_02", 2, 0, 0, 0));
		internalDIPorts.add(new InternalDigitalInputPort("LDI_03", 3, 0, 0, 0));
	}
	
	public static void initLDOPorts() {
		localDOPorts = new ArrayList<LocalDigitalOutputPort>();
		localDOPorts.add(new LocalDigitalOutputPort("LDO_00", 0, 9, 23, 49));
		localDOPorts.add(new LocalDigitalOutputPort("LDO_01", 1, 9, 24, 15));
		localDOPorts.add(new LocalDigitalOutputPort("LDO_02", 2, 9, 25, 117));
		localDOPorts.add(new LocalDigitalOutputPort("LDO_03", 3, 9, 30, 112));
	}
	
	public static void initRDOPorts() {
		remoteDOPorts = new ArrayList<RemoteDigitalOutputPort>();
		remoteDOPorts.add(new RemoteDigitalOutputPort("RDO_00", 0, 0, 0, 0));
		remoteDOPorts.add(new RemoteDigitalOutputPort("RDO_01", 1, 0, 0, 0));
		remoteDOPorts.add(new RemoteDigitalOutputPort("RDO_02", 2, 0, 0, 0));
		remoteDOPorts.add(new RemoteDigitalOutputPort("RDO_03", 3, 0, 0, 0));
	}
	
	public static void initIDOPorts() {
		internalDOPorts = new ArrayList<InternalDigitalOutputPort>();
		internalDOPorts.add(new InternalDigitalOutputPort("IDO_00", 0, 0, 0, 0));
		internalDOPorts.add(new InternalDigitalOutputPort("IDO_01", 1, 0, 0, 0));
		internalDOPorts.add(new InternalDigitalOutputPort("IDO_02", 2, 0, 0, 0));
		internalDOPorts.add(new InternalDigitalOutputPort("IDO_03", 3, 0, 0, 0));
	}
	
	public static void initLAIPorts() {
		localAIPorts = new ArrayList<LocalAnalogInputPort>();
		localAIPorts.add(new LocalAnalogInputPort("LAI_00", 0, 9, 39, 0));
		localAIPorts.add(new LocalAnalogInputPort("LAI_01", 1, 9, 40, 1));
		localAIPorts.add(new LocalAnalogInputPort("LAI_02", 2, 9, 37, 2));
		localAIPorts.add(new LocalAnalogInputPort("LAI_03", 3, 9, 38, 3));
	}
	
	public static void initRAIPorts() {
		remoteAIPorts = new ArrayList<RemoteAnalogInputPort>();
		remoteAIPorts.add(new RemoteAnalogInputPort("RAI_00", 0, 9, 39, 0));
		remoteAIPorts.add(new RemoteAnalogInputPort("RAI_01", 1, 9, 40, 1));
		remoteAIPorts.add(new RemoteAnalogInputPort("RAI_02", 2, 9, 37, 2));
		remoteAIPorts.add(new RemoteAnalogInputPort("RAI_03", 3, 9, 38, 3));
	}
	public static void initIAIPorts() {
		internalAIPorts = new ArrayList<InternalAnalogInputPort>();
		internalAIPorts.add(new InternalAnalogInputPort("IAI_00", 0, 9, 39, 0));
		internalAIPorts.add(new InternalAnalogInputPort("IAI_01", 1, 9, 40, 1));
		internalAIPorts.add(new InternalAnalogInputPort("IAI_02", 2, 9, 37, 2));
		internalAIPorts.add(new InternalAnalogInputPort("IAI_03", 3, 9, 38, 3));
	}
	
	public static void initLAOPorts() {
		localAOPorts = new ArrayList<LocalAnalogOutputPort>();
		localAOPorts.add(new LocalAnalogOutputPort("LAO_00", 0, 0, 0, 0));
		localAOPorts.add(new LocalAnalogOutputPort("LAO_01", 0, 0, 0, 0));
		localAOPorts.add(new LocalAnalogOutputPort("LAO_02", 0, 0, 0, 0));
		localAOPorts.add(new LocalAnalogOutputPort("LAO_03", 0, 0, 0, 0));
	}
	
	public static void initRAOPorts() {
		remoteAOPorts = new ArrayList<RemoteAnalogOutputPort>();
		remoteAOPorts.add(new RemoteAnalogOutputPort("RAO_00", 0, 9, 39, 0));
		remoteAOPorts.add(new RemoteAnalogOutputPort("RAO_01", 1, 9, 40, 1));
		remoteAOPorts.add(new RemoteAnalogOutputPort("RAO_02", 2, 9, 37, 2));
		remoteAOPorts.add(new RemoteAnalogOutputPort("RAO_03", 3, 9, 38, 3));
	}
	
	public static void initIAOPorts() {
		internalAOPorts = new ArrayList<InternalAnalogOutputPort>();
		internalAOPorts.add(new InternalAnalogOutputPort("IAO_00", 0, 9, 39, 0));
		internalAOPorts.add(new InternalAnalogOutputPort("IAO_01", 1, 9, 40, 1));
		internalAOPorts.add(new InternalAnalogOutputPort("IAO_02", 2, 9, 37, 2));
		internalAOPorts.add(new InternalAnalogOutputPort("IAI_03", 3, 9, 38, 3));
	}
	
	public static void initTimers() {
		timers = new ArrayList<TimerClass>();
		timers.add(new TimerClass("TMR_00"));
		timers.add(new TimerClass("TMR_01"));
		timers.add(new TimerClass("TMR_02"));
		timers.add(new TimerClass("TMR_03"));
	}
	
	public static void initCounters() {
		counters = new ArrayList<CounterClass>();
		counters.add(new CounterClass("CTR_00"));
		counters.add(new CounterClass("CTR_01"));
		counters.add(new CounterClass("CTR_02"));
		counters.add(new CounterClass("CTR_03"));
	}
	
	public static void readAllDIPorts() {
		for(LocalDigitalInputPort LDIPort : localDIPorts) {
			LDIPort.getValue();
		}
		for(RemoteDigitalInputPort RDIPort : remoteDIPorts) {
			RDIPort.getValue();
		}
		for(InternalDigitalInputPort IDIPort : internalDIPorts) {
			IDIPort.getValue();
		}
	}
	
	public static void writeAllDOPorts() {
		for(LocalDigitalOutputPort LDOPort : localDOPorts) {
			LDOPort.setValue();
		}
		for(RemoteDigitalOutputPort RDOPort : remoteDOPorts) {
			RDOPort.setValue();
		}
		for(InternalDigitalOutputPort IDOPort : internalDOPorts) {
			IDOPort.setValue();
		}
	}
	
	public static void readAllAIPorts() {
		for(LocalAnalogInputPort LAIPort : localAIPorts) {
			LAIPort.getValue();
		}
		for(RemoteAnalogInputPort RAIPort : remoteAIPorts) {
			RAIPort.getValue();
		}
		for(InternalAnalogInputPort IAIPort : internalAIPorts) {
			IAIPort.getValue();
		}
	}
	
	public static void writeAllAOPorts() {
		for(LocalAnalogOutputPort LAOPort : localAOPorts) {
			LAOPort.setValue();
		}
		for(RemoteAnalogOutputPort RAOPort : remoteAOPorts) {
			RAOPort.setValue();
		}
		for(InternalAnalogOutputPort IAOPort : internalAOPorts) {
			IAOPort.setValue();
		}
	}	
}

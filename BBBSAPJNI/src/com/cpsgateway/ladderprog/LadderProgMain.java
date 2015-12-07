/*
 * Author Dhanaseelan Thangavel
 */

package com.cpsgateway.ladderprog;

import com.cpsgateway.localio.LocalIOInit;

public class LadderProgMain {
	
	private static Simulator simulator = null;
	
	public static void main(String[] argv) {
		
		System.out.println("Starting Ladder Application");
		String osName = System.getProperty("os.name");
		System.out.println("OS = " + osName);
		if(System.getProperty("os.name").startsWith("Windows")) {
			System.out.println("OS is Windows : Turning ON Simulation mode");
			LocalIOInit.simulationMode = true;
			simulator = new Simulator();
		} else {
			LocalIOInit.simulationMode = false;
			System.out.println("OS is BeagleBone : Turning OFF Simulation mode");
			LocalIOInit.simulationMode = false;
		}
		
		LocalIOInit.LocalIOInitialize();

		while(true) {
			ReadLadderProgFile.readLadderProgramfile();
			LocalIOInit.readAllDIPorts();
			LocalIOInit.readAllAIPorts();
			LadderProgExecution.ladderProgExecute();
			if(!LocalIOInit.simulationMode) {
				LocalIOInit.writeAllDOPorts();
				LocalIOInit.writeAllAOPorts();
			} else {
				writeToAllOutput();
			}
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			XmlPortStatus.createXmlPortStatus();
		}
	}
	
	public static void writeToAllOutput() {
		for(int i=0; i < 4; i++) {
			//Digital Inputs
			if(LocalIOInit.localDOPorts.get(i).currentValue == true) {
				simulator.LDObuttons[i].setText("ON");
			} else {
				simulator.LDObuttons[i].setText("OFF");
			}
			if(LocalIOInit.remoteDOPorts.get(i).currentValue == true) {
				simulator.RDObuttons[i].setText("ON");
			} else {
				simulator.RDObuttons[i].setText("OFF");
			}
			if(LocalIOInit.internalDOPorts.get(i).currentValue == true) {
				simulator.IDObuttons[i].setText("ON");
			} else {
				simulator.IDObuttons[i].setText("OFF");
			}
			
			//Analog output
			simulator.LAOtxt[i].setText(String.valueOf(LocalIOInit.localAOPorts.get(i).currentValue));
			simulator.RAOtxt[i].setText(String.valueOf(LocalIOInit.remoteAOPorts.get(i).currentValue));
			simulator.IAOtxt[i].setText(String.valueOf(LocalIOInit.internalAOPorts.get(i).currentValue));
			
		}
	}
}

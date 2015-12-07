package com.cpsgateway.localio;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;


public class LocalDigitalInputPort {
	
	private String LDIPortName;
	private String  gpioValueFile;
	public boolean currentValue;
	public boolean prevValue;
	
	public LocalDigitalInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		
		if(!LocalIOInit.simulationMode) {
			gpioValueFile = null;
			LDIPortName = portName + ":";
			String gpioNoStr = Integer.toString(gpioNo);
			File fp;
			FileWriter fw;
			BufferedWriter bw;
			
			String gpioDir = "/sys/class/gpio/gpio" + gpioNoStr;
			try {
				FileSystem defaultFS = FileSystems.getDefault();
				if(Files.exists(defaultFS.getPath(gpioDir))) {
					if(Files.isDirectory(defaultFS.getPath(gpioDir))) {
						System.out.println(LDIPortName + gpioDir + " Exists and is Directory");
					} else {
						System.out.println(LDIPortName + gpioDir + " Exists and is Not Directory - Error");
						return;
					}
				} else {
					System.out.println(LDIPortName + gpioDir + " Does Not Exist - Create New");
					fp = new File("/sys/class/gpio/export");
					fw = new FileWriter(fp.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					bw.write(gpioNoStr);
					bw.close();
					
					System.out.println(LDIPortName + gpioDir + " created successfully");
				}
				fp = new File(gpioDir + "/direction");
				fw = new FileWriter(fp.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("in");
				bw.close();
				System.out.println(LDIPortName + gpioDir + " Direction Set to in successfully");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			gpioValueFile = gpioDir + "/value";
			getValue();
		}
		currentValue = false;
		prevValue = false;
	}
	
	
	public boolean getValue() {
		boolean value = false;
		if(!LocalIOInit.simulationMode) {
			if(gpioValueFile == null) {
				System.out.println("Error: " + LDIPortName + " Was not setup properly - Error");
			} else {
				BufferedReader br = null;
				try {
					FileInputStream fileInput = new FileInputStream(gpioValueFile);
					int r;
					r = fileInput.read();
					char c = (char) r;
					fileInput.close();
					if(c == '0') {
						if(LDIPortName.equals("LDI_00:"))
							System.out.println("Info: " + LDIPortName + " OFF");
						value = false;
					} else {
						//System.out.println("Info: " + LDIPortName + " ON");
						value = true;
					}
					
		 		} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		} else {
			value = currentValue;
		}
		prevValue = currentValue;
		currentValue = value;
		return value;
	}
}

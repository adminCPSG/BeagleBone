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


public class LocalDigitalOutputPort {
	
	private String LDOPortName;
	private String  gpioValueFile;
	public boolean currentValue;
	public boolean prevValue;
	
	public LocalDigitalOutputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		
		if(!LocalIOInit.simulationMode) {
			gpioValueFile = null;
			LDOPortName = portName + ":";
			String gpioNoStr = Integer.toString(gpioNo);
			File fp;
			FileWriter fw;
			BufferedWriter bw;
			
			String gpioDir = "/sys/class/gpio/gpio" + gpioNoStr;
			try {
				FileSystem defaultFS = FileSystems.getDefault();
				if(Files.exists(defaultFS.getPath(gpioDir))) {
					if(Files.isDirectory(defaultFS.getPath(gpioDir))) {
						System.out.println(LDOPortName + gpioDir + " Exists and is Directory");
					} else {
						System.out.println(LDOPortName + gpioDir + " Exists and is Not Directory - Error");
						return;
					}
				} else {
					System.out.println(LDOPortName + gpioDir + " Does Not Exist - Create New");
					fp = new File("/sys/class/gpio/export");
					fw = new FileWriter(fp.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					bw.write(gpioNoStr);
					bw.close();
					
					System.out.println(LDOPortName + gpioDir + " created successfully");
				}
				fp = new File(gpioDir + "/direction");
				fw = new FileWriter(fp.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("out");
				bw.close();
				System.out.println(LDOPortName + gpioDir + " Direction Set to in successfully");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gpioValueFile = gpioDir + "/value";
		}
		currentValue = false;
		prevValue = false;
		
	}
	
	public boolean getValue() {
		boolean value = false;
		if(!LocalIOInit.simulationMode) {
			if(gpioValueFile == null) {
				System.out.println(LDOPortName + " Was not setup properly - Error");
			} else {
				BufferedReader br = null;
				try {
					FileInputStream fileInput = new FileInputStream(gpioValueFile);
					int r;
					r = fileInput.read();
					char c = (char) r;
					fileInput.close();
					if(c == '0') {
						// System.out.println("Info: " + LDOPortName + " OFF");
						value = false;
					} else {
						// System.out.println("Info: " + LDOPortName + " ON");
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
	
	public void setValue() {
		this.setValue(currentValue);
	}
	
	public void setValue(boolean value) {
		if(!LocalIOInit.simulationMode) {
			if(gpioValueFile == null) {
				System.out.println("Error: " + LDOPortName + " Was not setup properly - Error");
			} else {
				BufferedReader br = null;
				try {
					File fp = new File(gpioValueFile);
					FileWriter fw = new FileWriter(fp.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					if(value == true) {
						bw.write("1");
						//System.out.println("Info: " + LDOPortName + " Turning ON");
					} else {
						//System.out.println("Info: " + LDOPortName + " Turning OFF");
						bw.write("0");
					}
					bw.close();	
					prevValue = currentValue;
					currentValue = value;
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
			prevValue = currentValue;
			currentValue = value;
		}
	}
}

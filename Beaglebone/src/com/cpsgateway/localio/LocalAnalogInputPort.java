package com.cpsgateway.localio;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LocalAnalogInputPort {
	
	private String LAIPortName;
	private String  gpioValueFile;
	public int currentValue;
	public int prevValue;
	
	public LocalAnalogInputPort(String portName, int portNumber, int port, int pinNo, int gpioNo) {
		if(!LocalIOInit.simulationMode) {
			gpioValueFile = null;
			LAIPortName = portName + ":";
			String gpioNoStr = Integer.toString(gpioNo);
			File fp;
			FileWriter fw;
			BufferedWriter bw;
			
			String ainFile = "/sys/devices/ocp.3/helper.15/AIN" + gpioNoStr;
			FileSystem defaultFS = FileSystems.getDefault();
			try {
				if(Files.exists(defaultFS.getPath(ainFile))) {
					if(Files.isReadable(defaultFS.getPath(ainFile))) {
						System.out.println("Info: " + LAIPortName + ainFile + " Exists and is Readable");
					} else {
						System.out.println("Error: " + LAIPortName + ainFile + " Exists and but not readable - Error");
						return;
					}
				} else {
					System.out.println("Info: " + LAIPortName + ainFile + " Does Not Exist - Create New");
					fp = new File("/sys/devices/bone_capemgr.9/slots");
					fw = new FileWriter(fp.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					bw.write("cape-bone-iio");
					bw.close();
					if(Files.exists(defaultFS.getPath(ainFile))) {
						System.out.println("Info: " + LAIPortName + ainFile + " created successfully");
					} else {
						return;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			gpioValueFile = ainFile;
		}
		currentValue = 0;
		prevValue = 0;
	}
	
	
	public int getValue() {
		int value = 0;
		if(!LocalIOInit.simulationMode) {
			if(gpioValueFile == null) {
				System.out.println("Error: " + LAIPortName + " Was not setup properly - Error");
			} else {
				BufferedReader br = null;
				try {
					File file = new File(gpioValueFile);
					FileReader inputFile = new FileReader(file);
					BufferedReader in = new BufferedReader(inputFile);
					String s =in.readLine();
					int r = Integer.parseInt(s);
					in.close();
					value = (int) (((float)r*0.05)+19.4);
					if(LAIPortName.equals("LAI_00:"))
						System.out.println("Info: " + LAIPortName + " Raw Value = " + Integer.toString(r) + " temp = " + Integer.toString(value));
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

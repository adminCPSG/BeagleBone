package com.cpsgateway.ladderprog;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

/*
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
*/
import java.util.ArrayList;

import com.cpsgateway.localio.CounterClass;
import com.cpsgateway.localio.LocalIOInit;
import com.cpsgateway.localio.TimerClass;

public class ReadLadderProgFile {
	
	public static ArrayList<LadderStatement> ladderProgBin = new ArrayList<LadderStatement>();
	private static String filePath = null;
	private static FileTime fOldTime = null;
	
	public static void readLadderProgramfile(){
		if(!LocalIOInit.simulationMode) {
			filePath = "/root/ladder.ldr";
		} else {
			filePath = "../ladderfile/ladder.ldr";
			//filePath = "D:/Anant/PythonTest/ladderfile/ladder.ldr";
		}
		try {
			Path p = Paths.get(filePath);
			BasicFileAttributes fileAttributes = Files.getFileAttributeView(p, 
					BasicFileAttributeView.class).readAttributes();
			FileTime fNewTime = fileAttributes.lastModifiedTime();
			
			if((fOldTime == null) || (fNewTime.compareTo(fOldTime) > 0)) {
				System.out.println("Info: latter.ldr file Modified. New time = " + fNewTime.toString());
				if(fOldTime != null) {
					System.out.println("Info: Old time = " + fOldTime.toString());
				}
				readLadderProgram(filePath);
				fOldTime = fNewTime;
			}
		}
		catch (NoSuchFileException e) {
				e.printStackTrace();
				System.out.println("Error: File Not found");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: IO Error while getting File attributs");
		}
	}

	public static void readLadderProgram(String fileName) {
		
		File fp = null;
		InputStream ips = null;
        BufferedReader reader = null;
        String line;
		String[] param = new String[10];
        int lineNo = 1;
        ladderProgBin.clear();  // Clear all the ladder statements     
        try {
        	fp = new File(fileName);
            ips = new FileInputStream(fp);
            reader = new BufferedReader(new InputStreamReader(ips));            

			while((line = reader.readLine()) != null) {
                System.out.println("Info: ReadLadderProgFile: Process Line#" + lineNo++ + ": " + line);
                line = line.trim();
                if((line.startsWith("//")) || line.length() == 0) {
                	System.out.println("Info: ReadLadderProgFile: Comment/empty Line Ignoring " + line);
                	continue;
                }
				Integer portNo;
				
                String words[] = line.split("\\s+");
				try {
					LadderConst.Opcodes opcode = LadderConst.Opcodes.valueOf(words[0]);
					 if(opcode == LadderConst.Opcodes.END) {
						 System.out.println("Info: ReadLadderProgFile: END Statement Reached - Returning");
						 break;
					 }
					 if(words.length == 1) {
						 System.out.println("Info: ReadLadderProgFile: Only opcode present - " + words[0]);
						 ladderProgBin.add(new LadderStatement(opcode));
					 } else if(words.length == 2) {
						 String subWords[] = words[1].split("_");
						 portNo = -1;
						 if(subWords.length == 2) {
							 portNo = Integer.valueOf(subWords[1]);
						 }
						 LadderConst.PortTypes portType = LadderConst.PortTypes.valueOf(subWords[0]);
						 System.out.println("Info: ReadLadderProgFile: Opcode=" + opcode.ordinal() + " Port Type = " + portType.ordinal() +
								 " Port No = " + portNo);
						 switch (portType) {
							case TMR:
								if(getParams(reader, "Timer", param, 2) == true) {
									TimerClass timer = LocalIOInit.timers.get(portNo);
									timer.initTimer(Integer.valueOf(param[0]), param[1]);
									ladderProgBin.add(new LadderStatement(opcode, portType, portNo, timer));            							  
								}
								break;
							case CTR:
								if(getParams(reader, "counter", param, 1) == true) {
									CounterClass counter = LocalIOInit.counters.get(portNo);
									counter.setCounter(Integer.valueOf(param[0]));
									ladderProgBin.add(new LadderStatement(opcode, portType, portNo, counter));            							  
								}
								break;
							case SAPIN:
							 case SAPOUT:
								 if(getParams(reader, "SAPOUT", param, 5) == true) {
									 FnSapOut sapOutFn = new FnSapOut(param);
									 ladderProgBin.add(new LadderStatement(opcode, portType, sapOutFn));            							 
									 
								 }
								 break;
							case EQUAL:
							case GREATER:
							case LESSTHAN:
							case GREATEROREQUAL:
							case LESSTHANOREQUAL:
								if(getParams(reader, "Comparator", param, 2) == true) {
									 ladderProgBin.add(new LadderStatement(opcode, portType, param));            							 
								}
								break;
							case ADD:
							case MINUS:
							case MULTIPLY:
							case DIVIDE:
								if(getParams(reader, "Math Op", param, 3) == true) {
									 ladderProgBin.add(new LadderStatement(opcode, portType, param));            							 
								 }
								break;
							default:
								ladderProgBin.add(new LadderStatement(opcode, portType, portNo));
								break;
						}
					 } else {
						 System.out.println("Error: ReadLadderProgFile: More than 2 words - Ignoring" + line);
					 }
				} catch (IllegalArgumentException ex) {
					System.out.println("Error: ReadLadderProgFile: unable to parse Ignoring entire line -" + line);
				}
            }
        } catch (FileNotFoundException ex) {
        	 System.err.format("Errror: ReadLadderProgFile: FileNotFoundException: %s%n", ex);
        } catch (IOException ex) {
        	 System.err.format("Error: ReadLadderProgFile: IOException: %s%n", ex);
        } 
        finally {
        	try {
	        	if(reader != null)
					reader.close();
	        	if(ips != null)
	              	ips.close();
        	} catch (IOException e) {
        		 System.out.println("Error: ReadLadderProgFile: IO Exception while closing");
				e.printStackTrace();
			}
        }
       
	}
	
	public static boolean getParams(BufferedReader reader, String fnName, String[] param, int count) {
		String str;
		try {
			for(int i=0; i < count; i++) {
				if((str = reader.readLine()) == null){
					System.out.println("Error: In " + fnName + ": Not enough parameters:  Ignoring");
					return false;
				}
				if (str.startsWith("PA")) {
					String words[];
					words = str.split("\\s+"); 
					System.out.println("Info: " + fnName + ":" + str + "/" + words[0] + "/" + words[1]); 
					param[i] = words[1];
				} else {
					System.out.println("Error: In " + fnName + ": parameters tag missing:  Ignoring: "
							+ str);
					return false;
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + fnName + ": unable to read parameters");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

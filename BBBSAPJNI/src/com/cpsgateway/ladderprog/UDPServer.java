package com.cpsgateway.ladderprog;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import com.cpsgateway.localio.LocalIOInit;
public class UDPServer implements Runnable {	
	DatagramSocket serverSocket = null;
	public UDPServer() {
		try {
			serverSocket = new DatagramSocket(9876);
			System.out.println("Port instantiated!!!!");
		} catch (SocketException ex) {
			System.out.println("Socket Exception occured");
		}
	}
	
	@Override  
	public void run()   
	{
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		String inLine;
		String outLine;
		int portNo = 0;
		String subWords[];
		System.out.println("server is running!!!!!");
		while(true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("################################################");
			try {
				serverSocket.receive(receivePacket);
				ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
				BufferedReader br = new BufferedReader(new InputStreamReader(bais));
				br = null;
				//"GET LDI_01\nGET LDI_02\nGET LAI_03\n"
				outLine = "";
				while ((inLine = br.readLine()) != null) {
					inLine = inLine.trim();
					System.out.println("RECEIVED: " + inLine);
					String words[] = inLine.split("\\s+");
					
					if(words.length == 2) {
						System.out.println("words[0] = " + words[0] + " words[1] = " + words[1]);
						subWords = words[1].split("_");
						System.out.println("subWords[0] = " + subWords[0] + " subWords[1] = " + subWords[1]);
						if(subWords.length == 2) {
							 portNo = Integer.valueOf(subWords[1]);
						 }
						//outLine = outLine.concat(words[1]);
						//outLine = outLine.concat("=");
						LadderConst.PortTypes portType = LadderConst.PortTypes.valueOf(subWords[0]);
						if(words[0].equals("GET")) {
							switch(portType) {
								case LDI:
								case RDI:
								case IDI:
								case LDO:
								case RDO:
								case IDO:
									boolean curState = getBinaryValue(portType, portNo);
									if(curState == true) {
										outLine = outLine.concat("1");
									} else {
										outLine = outLine.concat("0");
									}
									break;
								case LAI:
									outLine = outLine.concat("0");
									break;
								default:
									outLine = outLine.concat("0");
									break;
							}
						} else if(words[0].equals("SET")) {
							System.out.println("Set Not Supported\n");
						}
						//outLine = outLine.concat(",");
						System.out.println(outLine);
					}
					br.reset();
				}
				
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				sendData = outLine.getBytes();
				DatagramPacket sendPacket =
				new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			} catch (IOException ex) {
				System.out.println("IOException occured");
				serverSocket.close();
			}
		}
	}

	private static boolean getBinaryValue(LadderConst.PortTypes portType, int portNo) {
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
					//return(LocalIOInit.timers.get(portNo).currentState);
				default:
					return false;
			}
		}
	}
}
package com.cpsgateway.ladderprog;

import com.cpsgateway.sapserver.SapSendXml;

public class FnSapOut {
	private String url;
	private String userName;
	private String password;
	private String documentNumber;
	private String port;
	private int intValue;
	private boolean prevValue;
	
	public FnSapOut(String[] param) {
		System.out.println("Info: in Sapout object Created " + param[0] + param[1] + param[2] + param[3] + param[4]);
		prevValue = false;
		url = param[0];
		userName = param[1];
		password = param[2];
		documentNumber = param[3];
		port = param[4];
	}
	
	public  void fnSapOutExecute(boolean rungState) {
		if((rungState == true) && (prevValue == false)) {
			intValue = LadderProgExecution.getIntValueFromIO(port);
			System.out.println("Info: in Sapout Sending SOAP Message " + url + "/" + userName + "/"+ password +
					"/"+ documentNumber + "/"+ intValue);
		 	SapSendXml.SendXml(url, userName, password, documentNumber, String.valueOf(intValue));
		} else {
			System.out.println("Info: in Sapout RungState is false returning");
		}
		prevValue = rungState;
	}
}

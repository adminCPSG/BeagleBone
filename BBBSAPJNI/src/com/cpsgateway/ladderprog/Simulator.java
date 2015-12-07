package com.cpsgateway.ladderprog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import com.cpsgateway.localio.LocalIOInit;



public class Simulator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Buttons
	JButton LDIButtons[] = new JButton[4];
	JButton RDIButtons[] = new JButton[4];
	JButton IDIButtons[] = new JButton[4];
	JButton LDObuttons[] = new JButton[4];
	JButton RDObuttons[] = new JButton[4];
	JButton IDObuttons[] = new JButton[4];
	JFormattedTextField LAItxt[] = new JFormattedTextField[4];
	JFormattedTextField RAItxt[] = new JFormattedTextField[4];
	JFormattedTextField IAItxt[] = new JFormattedTextField[4];
	JFormattedTextField LAOtxt[] = new JFormattedTextField[4];
	JFormattedTextField RAOtxt[] = new JFormattedTextField[4];
	JFormattedTextField IAOtxt[] = new JFormattedTextField[4];
	// Upper Labels

	JLabel labelLD = new JLabel("LDI");
	JLabel labelRD = new JLabel("RDI");
	JLabel labelID = new JLabel("IDI");
	JLabel labelLDO = new JLabel("LDO");
	JLabel labelRDO = new JLabel("RDO");
	JLabel labelIDO = new JLabel("IDO");
	JLabel labelLAI = new JLabel("LAI");
	JLabel labelRAI = new JLabel("RAI");
	JLabel labelIAI = new JLabel("IAI");
	JLabel labelLAO = new JLabel("LAO");
	JLabel labelRAO = new JLabel("RAO");
	JLabel labelIAO = new JLabel("IAO");
	// side Labels

	JLabel labelLD1 = new JLabel("00");
	JLabel labelLD2 = new JLabel("01");
	JLabel labelLD3 = new JLabel("02");
	JLabel labelLD4 = new JLabel("03");
	JLabel labelLA1 = new JLabel("00");
	JLabel labelLA2 = new JLabel("01");
	JLabel labelLA3 = new JLabel("02");
	JLabel labelLA4 = new JLabel("03");
	JLabel labelInput = new JLabel("INPUT");
	JLabel labelOutput = new JLabel("OUTPUT");

	// Cancel Button

	JButton exitButton = new JButton("Cancel");

	public Simulator() {
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Listener for Change the mode of button from ON to OFF

		ClickListener cl = new ClickListener();
		// Listeners for Cancel button

		ClickListener3 c3 = new ClickListener3();
		JPanel panel1 = new JPanel();
		int ldi = 1;
		int rdi = 1;
		int idi = 1;
		int ldo = 150;
		int rdo = 150;
		int ido = 150;

		// For LDI Button at level 0,1,2,3
		for (int i = 0; i < LDIButtons.length; i++) {
			int k = 30 * ldi++;
			LDIButtons[i] = new JButton("OFF");
			LDIButtons[i].addActionListener(cl);
			LDIButtons[i].setBounds(30, k, 80, 30);
			panel1.add(LDIButtons[i]);
			LDIButtons[i].setName("btnl" + i);
			LDIButtons[i].setText("OFF");
			this.add(LDIButtons[i]);
		}
		// end of LDI Button

		// For RDI Button at level 0,1,2,3
		for (int i = 0; i < RDIButtons.length; i++) {
			int k = 30 * (rdi++);
			RDIButtons[i] = new JButton("OFF");
			RDIButtons[i].addActionListener(cl);
			RDIButtons[i].setBounds(120, k, 80, 30);
			panel1.add(RDIButtons[i]);
			RDIButtons[i].setName("btnr" + i);
			RDIButtons[i].setText("OFF");
			this.add(RDIButtons[i]);
		}
		// end RDI Button

		// For IDI Button at level 0,1,2,3
		for (int i = 0; i < IDIButtons.length; i++) {
			int k = 30 * (idi++);
			IDIButtons[i] = new JButton("OFF");
			IDIButtons[i].addActionListener(cl);
			IDIButtons[i].setBounds(210, k, 80, 30);
			panel1.add(IDIButtons[i]);
			IDIButtons[i].setName("btni" + i);
			IDIButtons[i].setText("OFF");
			this.add(IDIButtons[i]);
		}
		// end of IDI Button

		// For LDO Button at level 0,1,2,3
		for (int i = 0; i < LDObuttons.length; i++) {
			int j = i;
			int k = ldo + (30 * ++j);
			LDObuttons[i] = new JButton("OFF");
			LDObuttons[i].setBounds(30, k, 80, 30);
			panel1.add(LDObuttons[i]);
			LDObuttons[i].setName("btnldo" + i);
			LDObuttons[i].setEnabled(false);
			LDObuttons[i].setText("OFF");
			this.add(LDObuttons[i]);
		}
		// end of LDO

		// For RDO Button at level 0,1,2,3
		for (int i = 0; i < RDObuttons.length; i++) {
			int j = i;
			int k = rdo + (30 * ++j);
			RDObuttons[i] = new JButton("OFF");
			RDObuttons[i].setBounds(120, k, 80, 30);
			panel1.add(RDObuttons[i]);
			RDObuttons[i].setName("btnrdo" + i);
			RDObuttons[i].setText("OFF");
			RDObuttons[i].setEnabled(false);
			this.add(RDObuttons[i]);
		}
		// end of RDO

		// For IDO Button at level 0,1,2,3
		for (int i = 0; i < IDObuttons.length; i++) {
			int j = i;
			int k = ido + (30 * ++j);
			IDObuttons[i] = new JButton("OFF");
			IDObuttons[i].setBounds(210, k, 80, 30);
			panel1.add(IDObuttons[i]);
			IDObuttons[i].setName("btnido" + i);
			IDObuttons[i].setText("OFF");
			IDObuttons[i].setEnabled(false);
			this.add(IDObuttons[i]);
		 }
		 //end of IDO
     
 
     //For LAI TEXT Field at level 0,1,2,3
     
     int laitxt=1;
     
     for (int i=0;i<LAItxt.length;i++) {
    	int k=30*(laitxt++);
    	LAItxt[i]=new JFormattedTextField();
    	LAItxt[i].setText("0");
    	LAItxt[i].addActionListener(c3);
    	LAItxt[i].setBounds(300 , k , 80, 30);
   	    panel1.add(LAItxt[i]);
		this.add(LAItxt[i]);
		final int j=i;
       
       //validation for Leave the focus from Input text if input is correct
       
       LAItxt[i].addFocusListener(new FocusListener() {

 	      public void focusGained(FocusEvent e) {
 	      };

 	      public void focusLost(FocusEvent e) {
 	        if (!e.isTemporary()) {
				String content = LAItxt[j].getText();
				String regex = "[0-9]+";
				if(!content.matches(regex)) {
					SwingUtilities.invokeLater(new FocusGrabber(LAItxt[j]));
					JOptionPane.showMessageDialog(LAItxt[j], "plaese Enter the psositive Numeric Value", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				 } else {
					int i =Integer.parseInt(content); 	        
					if (!(i>=0 && i<=32767)) { 
						System.out.println("illegal value! " + content);
						SwingUtilities.invokeLater(new FocusGrabber(LAItxt[j]));
						JOptionPane.showMessageDialog(LAItxt[j], "plaese Enter the value b/w 0 to 32767", "Warning",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					LocalIOInit.localAIPorts.get(j).currentValue = i;					
				}
  	        } 
 	      }
 	    });
     }
     //end of LAI TextFiled
     
     //For RAI TEXT Field at level 0,1,2,3
		int rdtxt = 1;
		for (int i = 0; i < RAItxt.length; i++) {
			int k = 30 * (rdtxt++);
			RAItxt[i] = new JFormattedTextField();
			RAItxt[i].setText("0");
			RAItxt[i].addActionListener(c3);
			RAItxt[i].setBounds(390, k, 80, 30);
			panel1.add(RAItxt[i]);
			final int j = i;
			this.add(RAItxt[i]);
			RAItxt[i].addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
				};

				// validation for Leave the focus from Input text if input is
				// correct
				public void focusLost(FocusEvent e) {
					if (!e.isTemporary()) {
						String content = RAItxt[j].getText();
						String regex = "[0-9]+";
						if (!content.matches(regex)) {
							SwingUtilities.invokeLater(new FocusGrabber(RAItxt[j]));
							JOptionPane.showMessageDialog(RAItxt[j],
									"please Enter the psositive Numeric Value",
									"Warning", JOptionPane.WARNING_MESSAGE);
									return;
						} else {
							int i = Integer.parseInt(content);
							if (!(i >= 0 && i <= 32767)) {
								System.out.println("illegal value! " + content);
								SwingUtilities.invokeLater(new FocusGrabber(
										RAItxt[j]));
								JOptionPane.showMessageDialog(
												RAItxt[j],
												"please Enter the value b/w 0 to 32767",
												"Warning",
												JOptionPane.WARNING_MESSAGE);
									return;
							}
							LocalIOInit.remoteAIPorts.get(j).currentValue = i;
						}
					}
				}
			});
     }
     //end of RAI TextFiled
     
     //For IAI TEXT Field at level 0,1,2,3
     int idtxt=1;
	 for (int i = 0; i < IAItxt.length; i++) {
		int k = 30 * (idtxt++);
		IAItxt[i] = new JFormattedTextField();
		IAItxt[i].setText("0");
		IAItxt[i].addActionListener(c3);
		IAItxt[i].setBounds(480, k, 80, 30);
		panel1.add(IAItxt[i]);
		this.add(IAItxt[i]);
		final int j = i;
		IAItxt[i].addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			};
 			//validation for Leave the focus from Input text if input is correct
			public void focusLost(FocusEvent e) {
				if (!e.isTemporary()) {
					String content = IAItxt[j].getText();
					System.out.println(content);
					String regex = "[0-9]+";
					if (!content.matches(regex)) {
						SwingUtilities.invokeLater(new FocusGrabber(
								IAItxt[j]));
						JOptionPane.showMessageDialog(IAItxt[j],
								"plaese Enter the psositive Numeric Value",
								"Warning", JOptionPane.WARNING_MESSAGE);
								return;
					} else {
						int i = Integer.parseInt(content);

						if (!(i >= 0 && i <= 32767)) {
							System.out.println("illegal value! " + content);
							SwingUtilities.invokeLater(new FocusGrabber(
									IAItxt[j]));
							JOptionPane.showMessageDialog(
											IAItxt[j],
											"plaese Enter the value b/w 0 to 32767",
											"Warning",
											JOptionPane.WARNING_MESSAGE);
							return;
						}
						LocalIOInit.internalAIPorts.get(j).currentValue = i;
					}
				}
			}
		});
     }
     //end of IAI TextFiled
     
     //For LAO TEXT Field at level 0,1,2,3
     
     int laotxt=150;
     for (int i=0;i<LAOtxt.length;i++) {
    	int l=i;
    	int k=laotxt+(30*++l);
    	LAOtxt[i]=new JFormattedTextField();
    	LAOtxt[i].setText("0");
    	LAOtxt[i].setEditable(false);
    	//LAOtxt[i].addActionListener(c3);
    	LAOtxt[i].setBounds(300 , k , 80, 30);
   	    panel1.add(LAOtxt[i]);
        this.add(LAOtxt[i]);
     }
     //end of LAO TextFiled
     
     //For RAO TEXT Field at level 0,1,2,3
     int raotxt=150;
     for (int i=0;i<IDObuttons.length;i++) {
    	int l=i;
    	int k=raotxt+(30*++l);
    	RAOtxt[i]=new JFormattedTextField();
    	RAOtxt[i].setText("0");
    	RAOtxt[i].setEditable(false);
    	RAOtxt[i].setBounds(390 , k , 80, 30);
   	    panel1.add(RAOtxt[i]);
       	this.add(RAOtxt[i]);
     }
     //end of RAO TextFiled
     
     //For IAO TEXT Field at level 0,1,2,3
     int iaotxt=150;
     for (int i=0;i<IDObuttons.length;i++) {
    	int l=i;
    	int k=iaotxt+(30*++l);
    	IAOtxt[i]=new JFormattedTextField();
    	IAOtxt[i].setText("0");
    	IAOtxt[i].setEditable(false);
    
    	IAOtxt[i].setBounds(480 , k , 80, 30);
   	    panel1.add(IAOtxt[i]);
        this.add(IAOtxt[i]);
     }
     //end of IAO TextFiled
     //LDI Level
		panel1.add(labelLD);
		labelLD.setBounds(60, 5, 20, 30);
		this.add(labelLD);
		// RDI Level
		panel1.add(labelRD);
		labelRD.setBounds(145, 5, 20, 30);
		this.add(labelRD);
		// IDI Level
		panel1.add(labelID);
		labelID.setBounds(240, 5, 20, 30);
		this.add(labelID);
		// Level LDO
		panel1.add(labelLDO);
		labelLDO.setBounds(60, 155, 30, 30);
		this.add(labelLDO);
		// Level RDO
		panel1.add(labelRDO);
		labelRDO.setBounds(145, 155, 30, 30);
		this.add(labelRDO);
		// Level IDO
		panel1.add(labelIDO);
		labelIDO.setBounds(240, 155, 30, 30);
		this.add(labelIDO);
		// Level LAO
		panel1.add(labelLAI);
		labelLAI.setBounds(325, 5, 30, 30);
		this.add(labelLAI);
		// Level RAI
		panel1.add(labelRAI);
		labelRAI.setBounds(420, 5, 30, 30);
		this.add(labelRAI);
		// Level IAI
		panel1.add(labelIAI);
		labelIAI.setBounds(510, 5, 30, 30);
		this.add(labelIAI);
		// Level LAO
		panel1.add(labelLAO);
		labelLAO.setBounds(330, 155, 30, 30);
		this.add(labelLAO);
		// Level RAO
		panel1.add(labelRAO);
		labelRAO.setBounds(425, 155, 30, 30);
		this.add(labelRAO);
		// Level IAO
		panel1.add(labelIAO);
		labelIAO.setBounds(510, 155, 30, 30);
		this.add(labelIAO);
		// Setting the side level 00 01 02 03 for input and Output
		panel1.add(labelLD1);
		labelLD1.setBounds(3, 30, 20, 30);
		this.add(labelLD1);
		panel1.add(labelLA1);
		labelLA1.setBounds(3, 180, 20, 30);
		this.add(labelLA1);
		panel1.add(labelLD2);
		labelLD2.setBounds(3, 60, 20, 30);
		this.add(labelLD2);
		panel1.add(labelLA2);
		labelLA2.setBounds(3, 210, 20, 30);
		this.add(labelLA2);
		panel1.add(labelLD3);
		labelLD3.setBounds(3, 90, 20, 30);
		this.add(labelLD3);
		panel1.add(labelLA3);
		labelLA3.setBounds(3, 240, 20, 30);
		this.add(labelLA3);
		panel1.add(labelLD4);
		labelLD4.setBounds(3, 120, 80, 30);
		this.add(labelLD4);
		panel1.add(labelLA4);
		labelLA4.setBounds(3, 270, 80, 30);
		this.add(labelLA4);
		// setting the header for input and out put
		panel1.add(labelInput);
		labelInput.setBounds(280, 0, 40, 30);
		this.add(labelInput);
		panel1.add(labelOutput);
		labelOutput.setBounds(270, 150, 50, 30);
		this.add(labelOutput);
		exitButton.addActionListener(c3);
		exitButton.setName("exit");
		exitButton.setBounds(250, 310, 80, 25);
		panel1.add(exitButton);
		this.add(exitButton);
		this.add(panel1);
		this.setVisible(true);
	}

	private class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton o = (JButton) e.getSource();
			String name = o.getName();
			String m = o.getText();

    	//For LDI Input Button
    	
			for (int i = 0; i < 4; i++) {
				if (name.equals("btnl" + i)) {
					if (m.equals("OFF")) {
						LDIButtons[i].setText("ON");
						LocalIOInit.localDIPorts.get(i).currentValue = true;
					} else {
						LDIButtons[i].setText("OFF");
						LocalIOInit.localDIPorts.get(i).currentValue = false;
					}
				}
			}
			
			//For RDI input Button
			for (int i = 0; i < 4; i++) {
				if (name.equals("btnr" + i)) {
					if (m.equals("OFF")) {
						RDIButtons[i].setText("ON");
						LocalIOInit.remoteDIPorts.get(i).currentValue = true;
					} else {
						RDIButtons[i].setText("OFF");
						LocalIOInit.remoteDIPorts.get(i).currentValue = false;
					}
				}
			}
			//For IDI Input Buttons
			if (name.contains("btni")) {
				for (int i = 0; i < 4; i++) {
					if (name.equals("btni" + i)) {
						if (m.equals("OFF")) {
							IDIButtons[i].setText("ON");
							LocalIOInit.internalDIPorts.get(i).currentValue = true;
						} else {
							IDIButtons[i].setText("OFF");
							LocalIOInit.internalDIPorts.get(i).currentValue = false;
						}
					}
				}
			}
		}
	}

	private class ClickListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//System.exit(0);
		}
	} 
	//for focus lost
  
	class FocusGrabber implements Runnable {
		private JComponent component;

		public FocusGrabber(JComponent component) {
			this.component = component;
		}

		public void run() {
			component.grabFocus();
		}
	}
}

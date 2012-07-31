/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.hospital.main;

import javax.swing.JFrame;
import ru.hospital.gui.admin.AdminFullPanel;

/**
 *
 * @author a.yakischik
 */
public class Medical {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {	
		init();
		JFrame frame = new JFrame();
		AdminFullPanel panel = new AdminFullPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setContentPane(panel);		
		frame.setSize(500, 400); 
		frame.setVisible(true);	
	}
	
	private static void init(){
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {

		}
	}
}

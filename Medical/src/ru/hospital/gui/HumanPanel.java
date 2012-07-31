package ru.hospital.gui;

import java.util.Date;
import javax.swing.JTextField;
import ru.hospital.model.base.HumanEntityExtension;

public class HumanPanel extends javax.swing.JPanel {

	public HumanPanel() {
		initComponents();
		controller = new Controller(this);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        textLastName = new javax.swing.JTextField();
        labelFatherName = new javax.swing.JLabel();
        textFatherName = new javax.swing.JTextField();
        dateBirthday = new org.jdesktop.swingx.JXDatePicker();
        labelBirthday = new javax.swing.JLabel();

        labelName.setText("Имя");
        labelName.setMaximumSize(new java.awt.Dimension(100, 20));
        labelName.setMinimumSize(new java.awt.Dimension(100, 20));

        labelLastName.setText("Фамилия");
        labelLastName.setMaximumSize(new java.awt.Dimension(100, 20));
        labelLastName.setMinimumSize(new java.awt.Dimension(100, 20));

        labelFatherName.setText("Отчество");
        labelFatherName.setMaximumSize(new java.awt.Dimension(100, 20));
        labelFatherName.setMinimumSize(new java.awt.Dimension(100, 20));

        labelBirthday.setText("Дата рождения");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelFatherName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addComponent(labelBirthday))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textName)
                    .addComponent(textLastName)
                    .addComponent(textFatherName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFatherName)
                    .addComponent(labelFatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBirthday))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker dateBirthday;
    private javax.swing.JLabel labelBirthday;
    private javax.swing.JLabel labelFatherName;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField textFatherName;
    private javax.swing.JTextField textLastName;
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables

//--------------------------------------------//
/**
 * Весь код предлагаю писать вот в таких вот контроллерах.
 * Каждая панель/форма должна иметь контроллер, через который
 * усуществляется доступ к объектам панели.
 */	
	
	private Controller controller;

	public Controller getController() {
		return controller;
	}

	public static class Controller{
		
		private HumanPanel panel;
		
		private Controller(HumanPanel panel){
			this.panel = panel;
		};
		
		public void setFirstName(String firstName){
			panel.textName.setText(firstName); 
		}
		
		public void setLastName(String lastName){
			panel.textLastName.setText(lastName); 
		}
		
		public void setFatherName(String fatherName){
			panel.textFatherName.setText(fatherName); 
		}
		
		public void setBirthDay(Date birthDay){
			panel.dateBirthday.setDate(birthDay); 
		}
		
		public String getFirstName(){
			String text = panel.textName.getText();
			return text;
		}
		
		public String getLastName(){
			String text = panel.textLastName.getText();
			return text;
		}
		
		public String getFatherName(){
			String text = panel.textFatherName.getText();
			return text;
		}	
		
		public Date getBirthDay(){
			Date birthDay = panel.dateBirthday.getDate();
			return birthDay;
		}
		
		public void setHuman(HumanEntityExtension human){
			setFirstName(human.getFirstName()); 
			setLastName(human.getLastName()); 
			setFatherName(human.getFatherName());
			setBirthDay(human.getBirthDay()); 
		}
	}
	
}

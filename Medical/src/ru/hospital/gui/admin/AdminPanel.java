package ru.hospital.gui.admin;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import ru.hospital.model.Administrator;

public class AdminPanel extends javax.swing.JPanel {

	public AdminPanel() {
		initComponents();
		controller = new Controller(this);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogin = new javax.swing.JLabel();
        textLogin = new javax.swing.JTextField();
        labelPasswordFirst = new javax.swing.JLabel();
        passwordFirst = new javax.swing.JPasswordField();
        labelPasswordSecond = new javax.swing.JLabel();
        passwordSecond = new javax.swing.JPasswordField();
        labelPermission = new javax.swing.JLabel();
        comboPermission = new javax.swing.JComboBox();

        labelLogin.setText("Логин");
        labelLogin.setMaximumSize(new java.awt.Dimension(100, 20));
        labelLogin.setMinimumSize(new java.awt.Dimension(100, 20));
        labelLogin.setPreferredSize(new java.awt.Dimension(100, 20));

        labelPasswordFirst.setText("Пароль");
        labelPasswordFirst.setMaximumSize(new java.awt.Dimension(100, 20));
        labelPasswordFirst.setMinimumSize(new java.awt.Dimension(100, 20));
        labelPasswordFirst.setPreferredSize(new java.awt.Dimension(100, 20));

        labelPasswordSecond.setText("Повторите пароль");
        labelPasswordSecond.setMaximumSize(new java.awt.Dimension(100, 20));
        labelPasswordSecond.setMinimumSize(new java.awt.Dimension(100, 20));
        labelPasswordSecond.setPreferredSize(new java.awt.Dimension(100, 20));

        labelPermission.setText("Права");
        labelPermission.setMaximumSize(new java.awt.Dimension(100, 20));
        labelPermission.setMinimumSize(new java.awt.Dimension(100, 20));
        labelPermission.setPreferredSize(new java.awt.Dimension(100, 20));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelPermission, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelPasswordFirst, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelPasswordSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(textLogin)
                    .add(passwordFirst)
                    .add(passwordSecond)
                    .add(comboPermission, 0, 125, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(textLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelPasswordFirst, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(passwordFirst, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelPasswordSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelPermission, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboPermission, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboPermission;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelPasswordFirst;
    private javax.swing.JLabel labelPasswordSecond;
    private javax.swing.JLabel labelPermission;
    private javax.swing.JPasswordField passwordFirst;
    private javax.swing.JPasswordField passwordSecond;
    private javax.swing.JTextField textLogin;
    // End of variables declaration//GEN-END:variables

	private Controller controller;

	public Controller getController() {
		return controller;
	}
//********************************************//
	public static class Controller{
		
		private AdminPanel panel;
		
		private Controller(AdminPanel panel){
			this.panel = panel;
			
			DefaultComboBoxModel model = new DefaultComboBoxModel();
			for (PermissionMapper permission: PermissionMapper.getMapping()){
				model.addElement(permission); 
			}
			panel.comboPermission.setModel(model); 
		};
		
		public void setPassword(String password){
			panel.passwordFirst.setText(password);
			panel.passwordSecond.setText(""); 
		}		
		
		public void setPermission(Long permission){
			panel.comboPermission.setSelectedItem(new PermissionMapper(null, permission)); 
		}
		
		public void setLogin(String login){
			panel.textLogin.setText(login); 
		}
		
		public String getPassword() throws IllegalAccessException{
			String password = new String(panel.passwordFirst.getPassword()); 
			String confirm = new String(panel.passwordSecond.getPassword()); 
			if (!password.equals(confirm)){
				throw new IllegalAccessException("Пароли не совпадают");
			}
			return password;
		}		
		
		public Long getPermission(){
			PermissionMapper permission = (PermissionMapper)panel.comboPermission.getSelectedItem();
			return permission.value;
		}
		
		public String getLogin(){
			return panel.textLogin.getText(); 
		}
	}
//********************************************//	
	private static class PermissionMapper{
		
		private static List<PermissionMapper> list = null;
		private Long value;
		private String name;
		
		private PermissionMapper(String name, Long value){
			this.name = name;
			this.value = value;
		}
		
		private static List<PermissionMapper> getMapping(){
			if (list == null){
				list = new ArrayList<>();
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_MAIN, Administrator.PERMISSION_LEVEL_MAIN)); 
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_HIGH, Administrator.PERMISSION_LEVEL_HIGH)); 
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_MEDIUM, Administrator.PERMISSION_LEVEL_MEDIUM)); 
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_LOW, Administrator.PERMISSION_LEVEL_LOW)); 
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_VIEW, Administrator.PERMISSION_LEVEL_VIEW)); 
				list.add(new PermissionMapper(Administrator.PERMISSION_LEVEL_NAME_BLOCKED, Administrator.PERMISSION_LEVEL_BLOCKED)); 
			}
			return list;
		}
		
		@Override
		public String toString(){
			return name;
		}
		
		@Override
		public boolean equals(Object object){
			if (object == null || !(object instanceof PermissionMapper)){
				return false;
			}
			if (value == null || ((PermissionMapper)object) == null){
				return false;
			}
			return ((PermissionMapper)object).value.equals(value);
		}			
	}
}

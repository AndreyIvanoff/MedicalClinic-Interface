package ru.hospital.gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ru.hospital.gui.HumanPanel;
import ru.hospital.model.Administrator;

public class AdminFullPanel extends javax.swing.JPanel {	
	
	public AdminFullPanel() {
		initComponents();
		controller = new Controller(this);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminPanel = new ru.hospital.gui.admin.AdminPanel();
        humanPanel = new ru.hospital.gui.HumanPanel();
        scrollAdmins = new javax.swing.JScrollPane();
        listAdmins = new javax.swing.JList();
        buttonClose = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        buttonCreate = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();

        adminPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        humanPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listAdmins.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollAdmins.setViewportView(listAdmins);

        buttonClose.setText("Отмена");

        buttonCreate.setText("Зарегистрировать");

        buttonUpdate.setText("Обновить");

        buttonDelete.setText("Удалить");

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addComponent(buttonCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCreate)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonDelete)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(humanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollAdmins, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(humanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollAdmins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ru.hospital.gui.admin.AdminPanel adminPanel;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton buttonUpdate;
    private ru.hospital.gui.HumanPanel humanPanel;
    private javax.swing.JList listAdmins;
    private javax.swing.JScrollPane scrollAdmins;
    // End of variables declaration//GEN-END:variables


	private Controller controller;

	public Controller getController() {
		return controller;
	}
	
//*************************************//	
	public static class Controller{
		
		private AdminFullPanel panel;
		private Administrator current;
		private String adminLogin;
		
		private Controller(AdminFullPanel adminPanel){
			this.panel = adminPanel;
			refreshListFromServer();
			
			adminPanel.buttonCreate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addAdministrator(getAdministrator(Boolean.TRUE));  
				}
			}); 
			
			adminPanel.buttonDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeAdministrator(getAdministrator(Boolean.FALSE));  
				}
			}); 
			
			adminPanel.listAdmins.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					Administrator admin = (Administrator) panel.listAdmins.getSelectedValue();
					if (admin != null){						
						setAdministrator(admin); 
					}
					
				}
			}); 
		}	

		public String getAdminLogin() {
			return adminLogin;
		}

		public void setAdminLogin(String adminLogin) {
			this.adminLogin = adminLogin;
		}		
	//-------------------------------------//
		public void setAdministrator(Administrator admin){
			current = admin;
			
			HumanPanel.Controller humanController = panel.humanPanel.getController();
			humanController.setHuman(admin); 
			
			AdminPanel.Controller adminController = panel.adminPanel.getController();
			adminController.setPassword(admin.getPassword()); 
			adminController.setLogin(admin.getLogin()); 
			adminController.setPermission(admin.getPermissionLevel()); 
		}	
	//-------------------------------------//
		public Administrator getAdministrator(Boolean createNew){
			
			String password; 
			try {
				//считывание пароля должно проверяться так или иначе
				password = panel.adminPanel.getController().getPassword();
			} catch (IllegalAccessException e) {
				JOptionPane.showMessageDialog(panel, e.getMessage()); 
				return null;
			}			
			
			//пробуем заполнить поля тестового админа
			Administrator result = new Administrator();
			try {				
				refreshAdministrator(result); 
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(panel, e.getMessage()); 
			}

			//если не возникло исключения, применяем изменения на current (выделенного)
			//при условии, что не нужно создавать нового админа
			if (!createNew){
				refreshAdministrator(current); 
				current.setPassword(password); 
				return current;
			}
			
			result.setPassword(password); 
			return result;
		}
	//-------------------------------------//	
		//TODO сделать проверку корректности
		private void refreshAdministrator(Administrator admin) throws IllegalArgumentException{
			HumanPanel.Controller humanController = panel.humanPanel.getController();
			AdminPanel.Controller adminController = panel.adminPanel.getController();
			
			admin.setLogin(adminController.getLogin()); 
			admin.setPermissionLevel(adminController.getPermission());			
			//admin.setRegisteredBy(adminLogin);
			admin.setFirstName(humanController.getFirstName());
			admin.setLastName(humanController.getLastName());
			admin.setFatherName(humanController.getFatherName()); 
			admin.setBirthDay(humanController.getBirthDay());
		}
	//-------------------------------------//	
		//TODO - добавить отправку запроса на сервер (в первую очередь)
		public void addAdministrator(Administrator admin){
			DefaultListModel model = (DefaultListModel)panel.listAdmins.getModel();
			model.addElement(admin);
			panel.listAdmins.setSelectedValue(admin, true); 
			setAdministrator(admin); 
		}
	//-------------------------------------//
		//TODO - добавить отправку запроса на сервер (в первую очередь)
		public void removeAdministrator(Administrator admin){
			DefaultListModel model = (DefaultListModel)panel.listAdmins.getModel();
			model.removeElement(admin); 
			if (!model.isEmpty()){
				panel.listAdmins.setSelectedIndex(0); 
			}
			setAdministrator((Administrator)panel.listAdmins.getSelectedValue()); 
		}
	//-------------------------------------//
		private void refreshListFromServer(){
			List<Administrator> admins = new ArrayList<>();
			Administrator admin = new Administrator();
			admin.setBirthDay(new Date());
			admin.setId(1L);
			admin.setLogin("Artem"); 
			admin.setLastName("Yakischik");
			admin.setFirstName("Artem"); 
			admin.setFatherName("Andreevich");
			admin.setPassword("1"); 
			admin.setPermissionLevel(100L);
			admin.setRegisteredBy(1L);
			
			admins.add(admin);
			
			admin = new Administrator();
			admin.setBirthDay(new Date());
			admin.setId(2L);
			admin.setLogin("Andrey"); 
			admin.setLastName("Ivanov");
			admin.setFirstName("Andrey"); 
			admin.setFatherName("Alexandrovich");
			admin.setPassword("1"); 
			admin.setPermissionLevel(100L);
			admin.setRegisteredBy(1L);
			
			admins.add(admin);
			
			DefaultListModel model = new DefaultListModel();
			
			for (Administrator administrator: admins){
				model.addElement(administrator);				
			}
			
			panel.listAdmins.setModel(model); 
		}
	//-------------------------------------//
	}		
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


public class TicketController implements ActionListener{
	Manager man = new Manager("Tickets","Ticket Breakdown");
	Login v;
	MainFrame mf ;
	TicketModel m;
	InnerfFrames[] innerFrame = new InnerfFrames[100];
	int i = 0;
	int row=0;
	int col=0;
	String dept;
	
	
	
	public TicketController(TicketModel model, Login view){
		man.setVisible(false);
		this.v = view;
		this.m = model;
		v.addActionListener(v.login,this,"login");
		
		
	}
	
	public void login(){
		String un = v.getTextField(v.Login);
		String pw = v.getTextField(v.Password);
		m.query("SELECT * from login where username = '"+un+"' and password = '"+pw+"';");
		
		
		try {
			if(m.rs.next()){
				mf = new MainFrame();
				mf.tab1.addActionListener(this);
				mf.tab1.setActionCommand("tickets");
				mf.tab2.addActionListener(this);
				mf.tab2.setActionCommand("users");
				mf.tab3.addActionListener(this);
				mf.tab3.setActionCommand("statistics");
				mf.tab4.addActionListener(this);
				mf.tab4.setActionCommand("logout");
				mf.addActionListener(mf.registerUser, this, "register");
				
				mf.ticketTable.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent evt) {
				        row = mf.ticketTable.rowAtPoint(evt.getPoint());
				        col = mf.ticketTable.columnAtPoint(evt.getPoint());
				        createTicketWindow();
				        
				    }
				});
				
				mf.userTable.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent evt) {
				        row = mf.ticketTable.rowAtPoint(evt.getPoint());
				        col = mf.ticketTable.columnAtPoint(evt.getPoint());
				        createUserWindow();
				    }
				});
				v.setInvisible(false);
				v.Login.setText("");
				v.Password.setText("");
				dept = m.rs.getString("dept");
				if(dept.equals("techsupport")){
					mf.techSupportTap();
					mf.registerTicket.addActionListener(this);
					mf.registerTicket.setActionCommand("registerTicket");
					
				}else if(dept.equals("sysadmin")){
					mf.registrationTab();
				}else if(dept.equals("manager")){
					setChart();
					mf.managerTab();
					
				}
			}else{
				JOptionPane.showMessageDialog(null, "Username or Password are incorrect.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void closeTicket(){
		try{
			  long closing =  System.currentTimeMillis();
			  long opening = 0;
			  InnerfFrames  iF = (InnerfFrames) mf.dp.getSelectedFrame();
			  String idd = iF.id.getText();
			  m.query("select * from tickets where id = '"+idd+"';");
			  if(m.rs.next()){
				  opening = Long.parseLong(m.rs.getString("timestamp"));
			  }
			  long elapsed = closing-opening;
			  elapsed = elapsed/1000;
		   	  String query = "update tickets set closingtime ="+closing+" where id ="+idd;
		   	  String query1 = "update tickets set elapsedtime ="+elapsed+" where id ="+idd;
	    	  m.update(query);
	    	  m.update(query1);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please select a row.", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		mf.techSupportTap();
	}
	public void deleteTicket(){
			try{
			  InnerfFrames  iF = (InnerfFrames) mf.dp.getSelectedFrame();
			  String idd = iF.id.getText();
			  String query = "delete from tickets where id ="+idd;
			  m.update(query);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Please select a row.", "Error",
                        JOptionPane.ERROR_MESSAGE);
			}    	  
			mf.techSupportTap();
	}
	public void makeEditable(boolean edit){
		InnerfFrames  iF = (InnerfFrames) mf.dp.getSelectedFrame();
		int frameID = iF.frameID;
		innerFrame[frameID].setUserEditable(edit);
		
	}
	public void editUser(){
		InnerfFrames  iF = (InnerfFrames) mf.dp.getSelectedFrame();
		int frameID = iF.frameID;
		String un;
		String pw;
		String ph;
		String em;
		if(innerFrame[frameID].username.getText()!=null&&innerFrame[frameID].username.getText()!=""){
			un = innerFrame[frameID].username.getText();
			m.update("UPDATE login SET username = '"+un+"' WHERE id = '"+innerFrame[frameID].id.getText()+"'");
		}
		if(innerFrame[frameID].password.getText()!=null&&innerFrame[frameID].password.getText()!=""){
			pw = innerFrame[frameID].password.getText();
			m.update("UPDATE login SET password = '"+pw+"' WHERE id = '"+innerFrame[frameID].id.getText()+"'");
		}
		if(innerFrame[frameID].phone.getText()!=null&&innerFrame[frameID].phone.getText()!=""){
			ph = innerFrame[frameID].phone.getText();
			m.update("UPDATE login SET phone = '"+ph+"' WHERE id = '"+innerFrame[frameID].id.getText()+"'");
		}
		if(innerFrame[frameID].email.getText()!=null&&innerFrame[frameID].email.getText()!=""){
			em = innerFrame[frameID].email.getText();
			m.update("UPDATE login SET mail = '"+em+"' WHERE id = '"+innerFrame[frameID].id.getText()+"'");
		}
	}
	public void createUserWindow(){
		String id = "";
		String username = "";
		String dept = "";
		String email = "";
		String phone = "";
		String password = "";
		try{
		String idd = (String) mf.userTable.getModel().getValueAt(row, 0);
		m.query("SELECT * from login where id = '"+idd+"';");
		if(m.rs.next()){
			id = m.rs.getString("ID");
			username = m.rs.getString("username");
			dept = m.rs.getString("dept");
			email = m.rs.getString("mail");
			phone = m.rs.getString("phone");
			password = m.rs.getString("password");
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please select a row.", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		innerFrame[i] = new InnerfFrames("Ticket"+i, true, true, true, true);
		
		innerFrame[i].setUserInfo(id, username, email, phone, password,false);
		innerFrame[i].setBounds(25+(i*35),25+(i*35),400,400);
		innerFrame[i].setMinimumSize(new Dimension(400,400));
		mf.addActionListener(innerFrame[i].deleteUser, this, "deleteUser");
		mf.addActionListener(innerFrame[i].editUser, this, "editUser");
		mf.addActionListener(innerFrame[i].saveUser, this, "saveUser");
		
		mf.dp.add(innerFrame[i]);
		innerFrame[i].frameID = i;
		i++;
		mf.drawMenu();
		mf.validate();
		mf.repaint();
	}
	public void createTicketWindow(){
		String id = "";
		String issue = "";
		String comment = "";
		String elapsed = "";
		String urgency = "";
		try{
		String idd = (String) mf.ticketTable.getModel().getValueAt(row, 0);
		m.query("SELECT * from tickets where ID = '"+idd+"';");
		if(m.rs.next()){
			id = m.rs.getString("ID");
			issue = m.rs.getString("issue");
			elapsed = m.rs.getString("timestamp");
			urgency = m.rs.getString("urgency");
			comment = m.rs.getString("comment");
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please select a row.", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		innerFrame[i] = new InnerfFrames("Ticket"+i, true, true, true, true);
		innerFrame[i].setTicketInfo(id, comment, issue, urgency);
		innerFrame[i].setBounds(25+(i*35),25+(i*35),400,400);
		mf.addActionListener(innerFrame[i].delete, this, "deleteTicket");
		mf.addActionListener(innerFrame[i].close, this, "closeTicket");
		
		mf.dp.add(innerFrame[i]);
		i++;
		mf.drawMenu();
		mf.validate();
		mf.repaint();
	}
	public void createTicket(){
    		long dates =  System.currentTimeMillis();
    	    String issues = mf.getTextField(mf.issueField);
    	    String comment = mf.getTextField(mf.comment);
    	    
    	    if(comment.length()>=150){
    	    	JOptionPane.showMessageDialog(null, "Comment must have at most 150 characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
    	    }else if(issues.length()<=5){
    	    	JOptionPane.showMessageDialog(null, "Issue must contain at least five characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
    	    }else{
    	    	int index = mf.tecCombo.getSelectedIndex();
    	    	String key = mf.primaryKey[index];
    	    	
    	    	m.insert("INSERT INTO `workout`.`tickets` (`issue`, `userid`, `urgency`, `timestamp`, `comment`) VALUES ('"+issues+"','"+key+"', '"+mf.getCombo(mf.combo)+"','"+dates+"','"+comment+"');");	    
    	    	mf.techSupportTap();
    	    }
	}
	public void deleteUser(){
		try{
			  InnerfFrames  iF = (InnerfFrames) mf.dp.getSelectedFrame();
			  String idd = iF.id.getText();
			  String query = "delete from login where id ="+idd;
			  m.update(query);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Please select a row.", "Error",
                      JOptionPane.ERROR_MESSAGE);
			}    	  
			mf.registrationTab();
	}
	
	public void setChart(){
		
		try {
			m.query("SELECT COUNT(*) AS total FROM tickets;");
			if(m.rs.next()){
			final int totalTickets = m.rs.getInt("total");
			man.dataset.addValue( totalTickets , "Total Tickets (Total cost: €"+totalTickets*50+")" , "" );
			mf.totalTickets.setText(String.valueOf(totalTickets));
			mf.costTickets.setText("€ "+String.valueOf(totalTickets*50));
			}
			m.query("SELECT COUNT(*) AS total FROM tickets WHERE closingtime IS NULL;");
			if(m.rs.next()){
				final int openTickets = m.rs.getInt("total");
				man.dataset.addValue( openTickets , "Open Tickets", "" );    
				mf.openTickets.setText(String.valueOf(openTickets));
			}
			m.query("SELECT COUNT(*) AS total FROM tickets WHERE closingtime IS NOT NULL;");
			if(m.rs.next()){
				final int closedTickets = m.rs.getInt("total");
				man.dataset.addValue( closedTickets , "Closed Tickets", "" ); 
				mf.closedTickets.setText(String.valueOf(closedTickets));
			}
			mf.addActionListener(mf.update, this, "update");
			mf.addActionListener(mf.graphs, this, "graphs");
			     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void registerUser(){
		String un = mf.un.getText();
		String passwordd = mf.pw.getText();
		String mail = mf.em.getText();
		String phone = mf.ph.getText();
		String dpt = (String) mf.accountType.getSelectedItem();
		if(un.length()>=6&&passwordd.length()>=8){
			m.insert("INSERT INTO `workout`.`login` (`username`, `password`, `dept`, `phone`, `mail`) VALUES ('"+un+"','"+passwordd+"','"+dpt+"', '"+phone+"','"+mail+"');");
			JOptionPane.showMessageDialog(null, "Registration Successful.", "Success",
                    JOptionPane.ERROR_MESSAGE);
			mf.registrationTab();
		}else if(un.length()<=5&&passwordd.length()<=7){
			JOptionPane.showMessageDialog(null, "Username must contain at least and Password must contain at least 8 characters.", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}else{
			if(un.length()<=5){
				JOptionPane.showMessageDialog(null, "Username must contain at least 6 letters.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
			}
			if(passwordd.length()<=7){
				JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("login")){
			login();
		}else if(arg0.getActionCommand().equals("register")){
			registerUser();
		}else if(arg0.getActionCommand().equals("deleteUser")){
			deleteUser();
		}else if(arg0.getActionCommand().equals("registerTicket")){
			createTicket();
		}else if(arg0.getActionCommand().equals("deleteTicket")){
			deleteTicket();
		}else if(arg0.getActionCommand().equals("closeTicket")){
			closeTicket();
		}else if(arg0.getActionCommand().equals("editUser")){
			makeEditable(true);	
		}else if(arg0.getActionCommand().equals("saveUser")){
			editUser();
			makeEditable(false);
			mf.displayUserTable();
			
		}else if(arg0.getActionCommand().equals("tickets")){
			if(dept.equals("techsupport")){
				mf.techSupportTap();
				mf.registerTicket.addActionListener(this);
				mf.registerTicket.setActionCommand("registerTicket");
			}else{
				JOptionPane.showMessageDialog(null, "Access Restricted to Tech Support.", "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
		}else if(arg0.getActionCommand().equals("statistics")){
			if(dept.equals("manager")){
				setChart();
				mf.managerTab();
			}else{
				JOptionPane.showMessageDialog(null, "Access Restricted to Tech Support.", "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
		}else if(arg0.getActionCommand().equals("users")){
			if(dept.equals("sysadmin")){
			mf.registrationTab();
			}else{
				JOptionPane.showMessageDialog(null, "Access Restricted to System Administrator.", "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
		}else if(arg0.getActionCommand().equals("logout")){
			mf.setInvisible(false);
			v.setInvisible(true);
			dept = "";
		}else if(arg0.getActionCommand().equals("graphs")){
			man.setVisible(true);
		}else if(arg0.getActionCommand().equals("update")){
			setChart();
			mf.managerTab();
		}
	}
}

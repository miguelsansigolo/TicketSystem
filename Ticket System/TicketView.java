import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public abstract class TicketView extends JFrame{
	TicketModel m = new TicketModel();
	JPanel top = new JPanel();
	JPanel mid = new JPanel();
	JPanel bottom = new JPanel();
	JPanel right = new JPanel();
	String[] ticketColumns = {"id","issue","urgency","timestamp","closingtime","technician"};
	String[] userColumns = {"id","username","dept","mail","phone"};
	String[][] dataTicket = new String[100][10];
	String[][] dataUser = new String[100][10];
	String[] urgency = {"urgent","normal","longterm"};
	JComboBox combo = new JComboBox(urgency);
	JComboBox tecCombo;
	JTable ticketTable = new JTable(dataTicket, ticketColumns);
	JTable userTable = new JTable(dataUser, userColumns);
	JTextField issueField = new JTextField();
	JFrame j = new JFrame();
	
	public TicketView(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createDynamicCombo(JFrame j,String query,String[] data, String[] primaryKey,String dataTupple, String pKeyTupple){
		m.query(query);
		int i = 0;
		try {
			while(TicketModel.rs.next()){
				data[i] = TicketModel.rs.getString(dataTupple);
				primaryKey[i] = TicketModel.rs.getString(pKeyTupple);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tecCombo = new JComboBox(data);
		tecCombo.setPreferredSize(new Dimension(250,25));
		applyConstraints(j,mid,tecCombo,1,4,1,1,0,0,0,0);
		
	}
	public void applyConstraints(JFrame frame, Container container, Component c, int x, int y, int w, int h,int p1,int p2,int p3, int p4){
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = w;
		cons.gridheight = h;
		cons.insets = new Insets(p1, p2, p3, p4);
		container.add(c,cons);
	}
	

		
	public void displayTicketTable(){
    	try {
    		ticketTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
    			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    		        String status = (String)table.getModel().getValueAt(row,2);
    		        if("urgent".equals(status)){
    		        	setBackground(Color.red);
    		        }else if("normal".equals(status)){
    		        	setBackground(Color.green);
    		        }else if("longterm".equals(status)){
    		        	setBackground(Color.yellow);
    		        }else{
    		        	setBackground(Color.white);
    		        }
    		        return this;
    		    }
    		});
    	    m.query("SELECT * FROM tickets LEFT JOIN login ON tickets.userid = login.ID;");
    	    int counter = 0;
    	    while(TicketModel.rs.next()){
    	    	String id = TicketModel.rs.getString("id");
    	    	
    	    	dataTicket[counter][0]= id;
    	    	
    	    	String iss = TicketModel.rs.getString("issue");      
    	       
    	    	dataTicket[counter][1] = iss;  	      
    	        
    	        String urg = TicketModel.rs.getString("urgency");
  
    	        dataTicket[counter][2] = urg;
    	    	
    	    	String time = TicketModel.rs.getString("timestamp");
    	    	dataTicket[counter][3] = time;
    	    	
    	    	String closingtime = TicketModel.rs.getString("closingtime");
    	    	dataTicket[counter][4] = closingtime;
    	    	
    	    	String technician = TicketModel.rs.getString("username");
    	    	dataTicket[counter][5] = technician;
    	    	
    	    	
    	        counter = counter + 1;
    	    }
    	    
    	    bottom.setBorder(new TitledBorder("Tickets"));
    	    bottom.removeAll();
    	    createTable(this,bottom, ticketTable);
    	    
    	} catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
	}
	
	
	public void displayUserTable(){
    	try {
    		ticketTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
    			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    		        String status = (String)table.getModel().getValueAt(row,2);
    		        if("urgent".equals(status)){
    		        	setBackground(Color.red);
    		        }else if("normal".equals(status)){
    		        	setBackground(Color.green);
    		        }else if("longterm".equals(status)){
    		        	setBackground(Color.yellow);
    		        }else{
    		        	setBackground(Color.white);
    		        }
    		        return this;
    		    }
    		});
    	    m.query("select * from login;");
    	    int counter = 0;
    	    while(TicketModel.rs.next()){
    	    	String id = TicketModel.rs.getString("id");
    	    	
    	    	dataUser[counter][0]= id;
    	    	
    	    	String username = TicketModel.rs.getString("username");      
    	       
    	    	dataUser[counter][1] = username;  	      
    	        
    	        String	dept = TicketModel.rs.getString("dept");
  
    	        dataUser[counter][2] = dept;
    	    	
    	    	String email = TicketModel.rs.getString("mail");
    	    	dataUser[counter][3] = email;
    	    	
    	    	String phone = TicketModel.rs.getString("phone");
    	    	dataUser[counter][4] = phone;
    	    	
    	        
    	        counter = counter + 1;
    	    }
    	    
    	    bottom.setBorder(new TitledBorder("Employees"));
    	    bottom.removeAll();
    	    createTable(this,bottom, userTable);
    	    
    	} catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
	}
	
	public void centerScreen(Container c){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		c.setLocation(dim.width/2-c.getSize().width/2, dim.height/2-c.getSize().height/2);
	}
	
	public void setPreferences(JFrame j,int w, int h, boolean visible, String title, boolean resizable){
		j.setSize(w,h);
		j.setVisible(visible);
		j.setTitle(title);
		j.setResizable(resizable);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		j.setLocation(dim.width/2-j.getSize().width/2, dim.height/2-j.getSize().height/2);
	}
	public String getCombo(JComboBox c){
		String combos = (String) c.getSelectedItem();
		return combos;
	}
	public void setInvisible(boolean vis){
		j.setVisible(vis);
	}
	
	public String getTextField(JTextField tf){
		String txtField = tf.getText();
		return txtField;
	}
	
	public void createTable(JFrame j,Container c,JTable t){
		JScrollPane sr = new JScrollPane(t);
		c.add(sr);
	}
	
	public void createButton(JFrame j,Container frame,JButton button, int x, int y, int width,int height, Color c,int p1,int p2,int p3, int p4){
		button.setBackground(c);
		applyConstraints(j,frame,button,x,y,width,height,p1,p2,p3,p4);
	}
	
	public void createCombo(JFrame j,Container frame,JComboBox combo,Dimension dim, String action,int x, int y, int width,int height, Color c,int p1,int p2,int p3, int p4){
		combo.setBackground(c);
		combo.setPreferredSize(dim);
		applyConstraints(j,frame,combo,x,y,width,height,p1,p2,p3,p4);
	}
	public void createTextField(JFrame j,Container frame, JTextField tx, int x, int y, int width, int height, Color c,int p1,int p2,int p3, int p4){
		tx.setForeground(c);
		applyConstraints(j,frame,tx,x,y,width,height,p1,p2,p3,p4);
	}
	
	public void createLabel(JFrame j,Container frame, JLabel label, int x, int y, int width, int height, Color c,int p1,int p2,int p3, int p4){
		label.setForeground(c);
		label.setFont(new Font("Arial",Font.BOLD,11));
		applyConstraints(j,frame,label,x,y,width,height,p1,p2,p3,p4);
	}
	
	public void addActionListener(JButton b,ActionListener a, String action){
		b.addActionListener(a);
		b.setActionCommand(action);
	}
}

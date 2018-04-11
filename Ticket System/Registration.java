/*import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Registration extends TicketView{
	JPanel mid = new JPanel();
	JPanel bot = new JPanel();
	JLabel log = new JLabel("Employee Name: ");
	JLabel pass = new JLabel("Password: ");
	JLabel mai = new JLabel("Email: ");
	JLabel phone = new JLabel("Phone Number: ");
	JLabel dept = new JLabel("Department: ");
	JTable table = new JTable( data, userColumns);
	JScrollPane sr = new JScrollPane(table);
	String[] dpt = {"techsupport","sysadmin","manager"};
	JTextField untxt = new JTextField();
	JPasswordField passtxt = new JPasswordField();
	JTextField mailtxt = new JTextField();
	JTextField phonetxt = new JTextField();
	JComboBox cb = new JComboBox(dpt);
	
	JButton Button = new JButton("Back");
	JButton Button1 = new JButton("Register");

	public Registration(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferences(this, 500, 500, true, "System Administrator",true);
		setLayout(new BorderLayout());
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		bot.setLayout(new BorderLayout());
		
		mid.setLayout(new GridBagLayout());
		applyConstraints(mid, log, 0, 0, 1, 1);
		applyConstraints(mid, untxt, 1, 0, 1, 1);
		applyConstraints(mid, pass, 2, 0, 1, 1);
		applyConstraints(mid, passtxt, 3, 0, 1, 1);
		applyConstraints(mid, mai, 0, 1, 1, 1);
		applyConstraints(mid, mailtxt, 1, 1, 1, 1);
		applyConstraints(mid, phone, 2, 1, 1, 1);
		applyConstraints(mid, phonetxt, 3, 1, 1, 1);
		applyConstraints(mid, dept, 0, 2, 1, 1);
		applyConstraints(mid, cb, 1, 2, 1, 1);
		untxt.setPreferredSize(new Dimension(100,20));
		passtxt.setPreferredSize(new Dimension(100,20));
		untxt.setPreferredSize(new Dimension(100,20));
		mailtxt.setPreferredSize(new Dimension(100,20));
		untxt.setPreferredSize(new Dimension(100,20));
		phonetxt.setPreferredSize(new Dimension(100,20));
		
		applyConstraints(mid, Button, 1, 3, 1, 1);
		//bot.add(Button);
		 applyConstraints(mid, Button1, 0, 3, 1, 1);
		//bot.add(Button1);
		
		Button.addActionListener(this);
		Button.setActionCommand("back");
		Button1.addActionListener(this);
		Button1.setActionCommand("register");
		
		displayTable();
		validate();
		repaint();
		}
	
	public void register(){
		String un = untxt.getText();
		String passwordd = passtxt.getText();
		String mail = mailtxt.getText();
		String phone = phonetxt.getText();
		String dpt = (String) cb.getSelectedItem();
		if(un.length()>=6&&passwordd.length()>=8){
			m.insert("INSERT INTO `workout`.`login` (`username`, `password`, `dept`, `phone`, `mail`) VALUES ('"+un+"','"+passwordd+"','"+dpt+"', '"+phone+"','"+mail+"');");
			JOptionPane.showMessageDialog(null, "Registration Successful.", "Success",
                    JOptionPane.ERROR_MESSAGE);
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
	
	
	public void displayTable(){
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/workout?user=root&password=");
    		Statement stmt = conn.createStatement();
    	    ResultSet rs = stmt.executeQuery("select * from login;");
    	    int counter = 0;
    	    while(rs.next()){
    	    	String id = rs.getString("id");
    	    	
    	    	 data[counter][0]= id;
    	    	String iss = rs.getString("username");      
    	       
    	    	 data[counter][1] = iss;
    	      
    	        String tec = rs.getString("password");
    	    	 data[counter][2] = tec;    	      
    	        
    	        String urg = rs.getString("dept");
    	    	 data[counter][3] = urg;
    	    	
    	    	String time = rs.getString("phone");
    	    	 data[counter][4] = time;
    	    	
    	    	String closingtime = rs.getString("mail");
    	    	 data[counter][5] = closingtime;
    	    	
    	        
    	        counter = counter + 1;
    	    }
       	    bot.add(sr);
    	} catch (SQLException ex) {
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("back")){
			Login log = new Login();
			this.setVisible(false);
		}else if(arg0.getActionCommand().equals("register")){
			register();
			setVisible(false);
			Registration reg = new Registration();
		}
		
	}

	
}*/

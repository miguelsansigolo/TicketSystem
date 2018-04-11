import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends TicketView {
	JButton registerUser = new JButton("Register User");
	JButton registerTicket = new JButton("Register Ticket");
	JButton update = new JButton("Update");
	JButton graphs = new JButton("Show Graphs");
	JTextField un = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	JTextField ph = new JTextField();
	JTextField em = new JTextField();
	JTextField comment = new JTextField();
	JTextField totalTickets = new JTextField();
	JTextField openTickets = new JTextField();
	JTextField closedTickets = new JTextField();
	JTextField costTickets = new JTextField();
	JMenuBar menuBar = new JMenuBar();
	JMenuItem tab1 = new JMenuItem("Tickets");
	JMenuItem tab2 = new JMenuItem("Users");
	JMenuItem tab3 = new JMenuItem("Statistics");
	JMenuItem tab4 = new JMenuItem("Logout");
	JMenu menu = new JMenu("Views");
	JDesktopPane dp = new JDesktopPane();
	String[] accType = {"manager","sysadmin","techsupport"};
	JComboBox accountType = new JComboBox(accType);
	
	String[] usernameData = new String[500];
	String[] primaryKey = new String[500];
	
	
	
	public MainFrame() {
		
	}
	
	public void drawMenu(){
		j.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout());
		top.add(menuBar, BorderLayout.NORTH);
		top.setBackground(Color.white);
		
		menuBar.add(menu);
		menu.add(tab1);
		menu.add(tab2);
		menu.add(tab3);
		menu.add(tab4);
	}
	
	
	public void techSupportTap(){
		mid.removeAll();
		bottom.removeAll();
		SwingUtilities.updateComponentTreeUI(j);
		setPreferences(j, 600,650, true, "Technical Support", true);
		j.setExtendedState(j.MAXIMIZED_BOTH);
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setBackground(Color.white);
		j.setLayout(new BorderLayout());
		Dimension dim = j.getSize();
		double screenWidth = dim.getWidth();
		
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		j.add(mid,BorderLayout.CENTER);
		j.add(bottom,BorderLayout.SOUTH);
		j.add(split);
		split.setBottomComponent(bottom);
		split.setTopComponent(mid);
		split.setDividerSize(2);
		
		
		dp.setPreferredSize(new Dimension ((int) (screenWidth/1.35),250));
		dp.setBackground(new Color(92, 96, 104));
		dp.setBorder(new BevelBorder(1,Color.black,Color.LIGHT_GRAY));
	    j.add(dp, BorderLayout.EAST);
		
		mid.setLayout(new GridBagLayout());
		mid.setBackground(new Color(249, 252, 252));
		mid.setMinimumSize(new Dimension ((int) (screenWidth/2),250));
		mid.setMaximumSize(new Dimension ((int) (screenWidth/2),350));
		bottom.setBackground(new Color(249, 252, 252));
		bottom.setLayout(new GridLayout());
		
		mid.setLayout(new GridBagLayout());
		applyConstraints(j,mid,combo,1,2,1,1,5,0,0,0);
		combo.setPreferredSize(new Dimension(250,25));
		//createButton(j,mid,delete,1,4,1,1, Color.white,5,0,0,0);
		//createButton(j,mid,close, 2,4,1,1, Color.white,5,0,0,0);
		createButton(j,mid,registerTicket, 1,5,1,1, Color.white,5,0,0,0);
		registerTicket.setBorder(new BevelBorder(1,Color.black,Color.LIGHT_GRAY));
		registerTicket.setPreferredSize(new Dimension(125,35));
		
		createLabel(j,mid, new JLabel("URGENCY :"), 0, 2, 1, 1, Color.black,5,0,0,0);
		createLabel(j,mid, new JLabel("ISSUE :"), 0, 0, 1, 1, Color.black,0,0,0,0);
		createLabel(j,mid,new JLabel("COMMENT :"),0,3,1,1,Color.black,5,0,0,0);
		createLabel(j,mid,new JLabel("TECHNICIAN :"),0,4,1,1,Color.black,5,0,0,0);
		createTextField(j,mid,comment,1,3,1,1,Color.black,5,0,0,0);
		createTextField(j,mid,issueField,1,0,1,1,Color.black,0,0,0,0);
		comment.setPreferredSize(new Dimension(250,100));
		issueField.setPreferredSize(new Dimension(250,25));
		createDynamicCombo(this,"select * from login where dept = 'techsupport';",usernameData,primaryKey,"username","id");
		
		drawMenu();
		bottom.removeAll();
		displayTicketTable();
		validate();
		repaint();
	}
	
	public void registrationTab(){
		mid.removeAll();
		bottom.removeAll();
		SwingUtilities.updateComponentTreeUI(j);
		setPreferences(j, 600,650, true, "Technical Support", true);
		j.setExtendedState(j.MAXIMIZED_BOTH);
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setBackground(Color.white);
		j.setLayout(new BorderLayout());
		Dimension dim = j.getSize();
		double screenWidth = dim.getWidth();
		
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		j.add(mid,BorderLayout.CENTER);
		j.add(bottom,BorderLayout.SOUTH);
		j.add(split);
		split.setBottomComponent(bottom);
		split.setTopComponent(mid);
		split.setDividerSize(2);
		
		/////////////////////////////////////////////////////////////
		dp.setPreferredSize(new Dimension ((int) (screenWidth/1.35),250));
		dp.setBackground(new Color(92, 96, 104));
		dp.setBorder(new BevelBorder(1,Color.black,Color.LIGHT_GRAY));
	    j.add(dp, BorderLayout.EAST);
	    ///////////////////////////////////////////////////////////////
		
		mid.setLayout(new GridBagLayout());
		mid.setBackground(new Color(249, 252, 252));
		mid.setMinimumSize(new Dimension ((int) (screenWidth/2),250));
		mid.setMaximumSize(new Dimension ((int) (screenWidth/2),350));
		bottom.setBackground(new Color(249, 252, 252));
		bottom.setLayout(new GridLayout());
		
		mid.setLayout(new GridBagLayout());
		createButton(j,mid,registerUser, 1,6,1,1, Color.white,5,0,0,0);
		registerTicket.setBorder(new BevelBorder(1,Color.black,Color.LIGHT_GRAY));
		registerTicket.setPreferredSize(new Dimension(125,35));
		
		createLabel(j,mid, new JLabel("USERNAME :"), 0, 0, 1, 1, Color.black,5,0,0,0);
		createLabel(j,mid, new JLabel("PASSWORD :"), 0, 1, 1, 1, Color.black,0,0,0,0);
		createLabel(j,mid,new JLabel("EMAIL :"),0,2,1,1,Color.black,5,0,0,0);
		createLabel(j,mid,new JLabel("PHONE NUMBER :"),0,3,1,1,Color.black,5,0,0,0);
		createLabel(j,mid,new JLabel("ACCOUNT TYPE :"),0,4,1,1,Color.black,5,0,0,0);
		
		createTextField(j,mid,un,1,0,1,1,Color.black,5,0,0,0);
		createTextField(j,mid,pw,1,1,1,1,Color.black,0,0,0,0);
		createTextField(j,mid,em,1,2,1,1,Color.black,0,0,0,0);
		createTextField(j,mid,ph,1,3,1,1,Color.black,0,0,0,0);
		un.setPreferredSize(new Dimension(250,25));
		pw.setPreferredSize(new Dimension(250,25));
		em.setPreferredSize(new Dimension(250,25));
		ph.setPreferredSize(new Dimension(250,25));
		createCombo(j,mid,accountType,new Dimension(250,25),"getAccount", 1,4,1,1,new Color(255,255,255), 0,0,0,0);
		
		drawMenu();
		displayUserTable();
		validate();
		repaint();
	}
	
	
	public void managerTab(){
		mid.removeAll();
		bottom.removeAll();
		SwingUtilities.updateComponentTreeUI(j);
		setPreferences(j, 600,650, true, "Technical Support", true);
		j.setExtendedState(j.MAXIMIZED_BOTH);
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setBackground(Color.white);
		j.setLayout(new BorderLayout());
		j.add(mid,BorderLayout.CENTER);
		j.add(bottom,BorderLayout.SOUTH);
		mid.setLayout(new GridBagLayout());
		mid.setBackground(new Color(249, 252, 252));
		createTextField(j,mid,totalTickets,1,0,1,1,Color.black,5,0,0,0);
		createTextField(j,mid,openTickets,1,1,1,1,Color.black,0,0,0,0);
		createTextField(j,mid,closedTickets,1,2,1,1,Color.black,0,0,0,0);
		createTextField(j,mid,costTickets,1,3,1,1,Color.black,0,0,0,0);
		createButton(j,mid,update, 1,4,1,1, Color.white,5,0,0,0);
		createButton(j,mid,graphs, 1,5,1,1, Color.white,5,0,0,0);
		totalTickets.setPreferredSize(new Dimension(250,50));
		openTickets.setPreferredSize(new Dimension(250,50));
		closedTickets.setPreferredSize(new Dimension(250,50));
		costTickets.setPreferredSize(new Dimension(250,50));
		totalTickets.setBorder(new TitledBorder("Total Tickets"));
		openTickets.setBorder(new TitledBorder("Open Tickets"));
		closedTickets.setBorder(new TitledBorder("Closed Tickets"));
		costTickets.setBorder(new TitledBorder("Cost of the Tickets"));
		totalTickets.setEditable(false);
		openTickets.setEditable(false);
		closedTickets.setEditable(false);
		costTickets.setEditable(false);
		totalTickets.setBackground(new Color(249, 252, 252));
		openTickets.setBackground(new Color(249, 252, 252));
		costTickets.setBackground(new Color(249, 252, 252));
		closedTickets.setBackground(new Color(249, 252, 252));
		drawMenu();
		validate();
		repaint();
		
	}
	
	

}

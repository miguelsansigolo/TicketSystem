import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class InnerfFrames extends JInternalFrame {
	JTextArea comment = new JTextArea();
	JTextField username = new JTextField();
	JTextField password = new JTextField();
	JTextField email = new JTextField();
	JTextField issue = new JTextField();
	JTextField urgency = new JTextField();
	JTextField id = new JTextField();
	JTextField phone = new JTextField();
	JButton close = new JButton("CLOSE TICKET");
	JButton delete = new JButton("DELETE TICKET");
	JButton deleteUser = new JButton("DELETE USER");
	JButton editUser = new JButton("EDIT USER");
	JButton saveUser = new JButton("SAVE CHANGES");
	int frameID;
	
	public InnerfFrames(String title,boolean max, boolean icon,boolean resize,boolean close) {
			super.title = title;
			super.maximizable = max;
			super.iconable = icon;
			super.resizable = resize;
			super.closable = close;
			
		    
		    setLayout(new GridBagLayout());
		    
		    setVisible(true);
	}
	
	public void applyConstraints(JInternalFrame iF, Component c, int x, int y, int w, int h){
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = w;
		cons.gridheight = h;
		iF.add(c,cons);
	}
	
	public void setUserEditable(boolean edit){
		password.setEditable(edit);
		id.setEditable(edit);
		username.setEditable(edit);
		email.setEditable(edit);
		phone.setEditable(edit);
		password.setText("");
		username.setText("");
		email.setText("");
		phone.setText("");
			this.applyConstraints(this,this.saveUser, 1, 2, 1, 1);
			validate();
			repaint();
		
	}
	
	public void setUserInfo(String id, String username, String mail, String phone,String password, Boolean editable){
		this.setBackground(new Color(249, 252, 252));
		this.applyConstraints(this, this.phone, 0, 3, 1, 1);
		this.applyConstraints(this, this.id, 0, 0, 1, 1);
		this.applyConstraints(this, this.username, 0, 2, 1, 1);
		this.applyConstraints(this, this.email, 0, 4, 1, 1);
		this.applyConstraints(this, this.password, 0, 1, 1, 1);
		this.phone.setBorder(new TitledBorder("Phone"));
		this.username.setBorder(new TitledBorder("Username"));
		this.email.setBorder(new TitledBorder("Email"));
		this.password.setBorder(new TitledBorder("Password"));
		this.id.setBorder(new TitledBorder("ID"));
		this.id.setPreferredSize(new Dimension(250,50));
		this.id.setEditable(editable);
		this.id.setBackground(new Color(249, 252, 252));
		this.phone.setPreferredSize(new Dimension(250,50));
		this.phone.setEditable(editable);
		this.phone.setBackground(new Color(249, 252, 252));
		this.username.setPreferredSize(new Dimension(250,50));
		this.username.setEditable(editable);
		this.username.setBackground(new Color(249, 252, 252));
		this.phone.setBackground(new Color(249, 252, 252));
		this.email.setPreferredSize(new Dimension(250,50));
		this.email.setEditable(editable);
		this.email.setBackground(new Color(249, 252, 252));
		this.password.setPreferredSize(new Dimension(250,50));
		this.password.setEditable(editable);
		this.password.setBackground(new Color(249, 252, 252));
		
		
		this.id.setText(id);
		this.username.setText(username);
		this.email.setText(mail);
		this.phone.setText(phone);
		this.password.setText(password);
	
		this.applyConstraints(this,this.editUser, 1, 0, 1, 1);
		this.applyConstraints(this,this.deleteUser, 1, 1, 1, 1);
	}
	
	public void setTicketInfo(String id, String comment, String issue, String urgency){
		
		this.setBackground(new Color(249, 252, 252));
		this.applyConstraints(this, this.comment, 0, 3, 1, 1);
		this.applyConstraints(this, this.id, 0, 0, 1, 1);
		this.applyConstraints(this, this.urgency, 0, 2, 1, 1);
		this.applyConstraints(this, this.issue, 0, 1, 1, 1);
		
		this.issue.setBorder(new TitledBorder("Issue"));
		this.urgency.setBorder(new TitledBorder("Urgency"));
		this.comment.setBorder(new TitledBorder("Comment"));
		this.id.setBorder(new TitledBorder("ID"));
		this.id.setPreferredSize(new Dimension(250,50));
		this.id.setEditable(false);
		this.id.setBackground(new Color(249, 252, 252));
		this.urgency.setPreferredSize(new Dimension(250,50));
		this.urgency.setEditable(false);
		this.urgency.setBackground(new Color(249, 252, 252));
		this.issue.setPreferredSize(new Dimension(250,50));
		this.issue.setEditable(false);
		this.issue.setBackground(new Color(249, 252, 252));
		this.comment.setPreferredSize(new Dimension(250,50));
		this.comment.setEditable(false);
		this.comment.setBackground(new Color(249, 252, 252));
		
		this.id.setText(id);
		this.issue.setText(issue);
		this.urgency.setText(urgency);
		this.comment.setText(comment);
	
		this.applyConstraints(this,this.close, 1, 0, 1, 1);
		this.applyConstraints(this,this.delete, 1, 1, 1, 1);
	}
}

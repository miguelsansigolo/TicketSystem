import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


public class Login extends TicketView {
	JTextField Login = new JTextField();
	JTextField Password = new JPasswordField();
	JButton login = new JButton("LOGIN");
	public Login() {
		
		setPreferences(j, 300,200, true, "Technical Support", false);
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setBackground(Color.white);
		setPreferredSize(new Dimension(222,25));
		setLayout(new BorderLayout());

		
		mid.setLayout(new GridBagLayout());
		mid.setBackground(Color.white);
		bottom.setBackground(Color.white);
		top.setBackground(Color.white);
		
		j.add(top, BorderLayout.NORTH);
		j.add(mid, BorderLayout.CENTER);
		j.add(bottom, BorderLayout.SOUTH);
		createTextField(j,mid,Login, 1,0,1,1,Color.black,0,0,0,0);
		createTextField(j,mid,Password, 1,1,1,1,Color.black,0,0,0,0);

		
		Login.setPreferredSize(new Dimension(250,50));
		Login.setBorder(new TitledBorder("Login"));
		Password.setPreferredSize(new Dimension(250,50));
		Password.setBorder(new TitledBorder("Password"));
		
		createButton(j,bottom,login, 0,0,5,5, Color.white,0,0,0,0);
		login.setPreferredSize(new Dimension(75,35));
		login.setBorder(new BevelBorder(1,Color.black,Color.LIGHT_GRAY));
		j.validate();
		j.repaint();
	}

}

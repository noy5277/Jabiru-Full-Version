package Jabiru.View;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import Jabiru.Classes.User;
import Jabiru.client.Client;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class Login implements Runnable {
	
	private static JTextField tfUserName;
	private static JPasswordField passwordField;
	private static ActionListener loginBtnAction;
	
	private static void createAndShowGUI(Client c) {
		
		
		InitActionListeners(c);
		JFrame frame = new JFrame("Login");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\noyz\\git\\Jabiru\\Jabiru\\Icons\\jabiru.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH );
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1440, 144);
		panel.setBackground(SystemColor.activeCaption);
		panel.setForeground(new Color(60, 179, 113));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Title = new JLabel("Jabiru");
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Tahoma", Font.BOLD, 88));
		Title.setBounds(576, 36, 396, 78);
		panel.add(Title);
		
		JLabel username = new JLabel("Username:");
		username.setFont(new Font("Myanmar Text", Font.BOLD, 33));
		username.setBounds(402, 221, 342, 36);
		frame.getContentPane().add(username);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 33));
		lblNewLabel.setBounds(400, 359, 452, 50);
		frame.getContentPane().add(lblNewLabel);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Gisha", Font.PLAIN, 20));
		tfUserName.setBounds(400, 276, 649, 36);
		frame.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(SystemColor.activeCaptionText);
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(935, 564, 114, 36);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(loginBtnAction);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(402, 428, 647, 36);
		frame.getContentPane().add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.windowBorder);
		panel_1.setBounds(360, 202, 732, 409);
		frame.getContentPane().add(panel_1);

		frame.setVisible(true);

	}
	
	private static void InitActionListeners(Client c)
	{
		loginBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				User u=new User(null, null);
				u.setmUserName(tfUserName.getText());
				u.setmPassword(passwordField.getText());
				Thread action=new Thread(new Runnable()
                {
					public void run()
					{
						try
						{
							c.createLoginRequest(u);
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				});
				action.start();
				
				if(true)
				{
					System.out.println("connection succeed");
				}
			}
		};
	}


	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				Client c;
				try {
					c = new Client();
					
					createAndShowGUI(c);
					}
				catch (IOException e)
				{
				e.printStackTrace();
				}
			}
			});
		

	}

	@Override
	public void run() {
		
		
	}

}

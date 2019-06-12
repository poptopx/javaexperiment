package javaexperimentgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javaexperiment.Client;
import javaexperiment.DataProcessing;
import javaexperiment.User;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Selframe extends JFrame {

	private JFrame frame = this;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private String name;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selframe window = new Selframe("jack");
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Selframe(String name) throws Exception{
		this.name = name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		//frame = new JFrame();
		this.setBounds(100, 100, 450, 333);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("信息修改");
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(87, 40, 72, 18);
		this.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801");
		label_1.setBounds(87, 81, 72, 18);
		this.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801");
		label_2.setBounds(87, 125, 72, 18);
		this.getContentPane().add(label_2);
		
		JLabel lblNewLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel.setBounds(87, 163, 83, 18);
		this.getContentPane().add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u89D2\u8272");
		label_3.setBounds(87, 205, 72, 18);
		this.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(193, 37, 154, 24);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(name);
		textField.setEnabled(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 78, 154, 24);
		this.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(193, 122, 154, 24);
		this.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(193, 160, 154, 24);
		this.getContentPane().add(passwordField_2);
		User user ;
		user = DataProcessing.searchUser(name);
		String role = user.getRole();
		textField_1 = new JTextField();
		textField_1.setBounds(193, 202, 154, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(role);
		textField_1.setEnabled(false);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = String.copyValueOf(passwordField.getPassword());
				String s2 = String.copyValueOf(passwordField_1.getPassword());
				String s3 = String.copyValueOf(passwordField_2.getPassword());
				//String role = (String)comboBox.getSelectedItem();
				//System.out.println(role);
			/*	
				try {
					if(DataProcessing.searchUser(name, s1)!=null){
						if(s2.equals(s3)){
							DataProcessing.updateUser(name, s2, role);
							JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "两次密码输入不一致", "温馨提示", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				try {
					Client.ChangeSelfInfo(s1,s2,s3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(97, 246, 101, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(233, 246, 101, 27);
		getContentPane().add(button_1);
		
		
	}
}

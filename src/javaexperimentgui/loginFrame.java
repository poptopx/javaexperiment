package javaexperimentgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

import javaexperiment.Client;
import javaexperiment.DataProcessing;

public class loginFrame extends JFrame implements ActionListener {

	static JFrame fr ;
	
	static JTextField t1;
	static JPasswordField t2;
	public loginFrame(){
		//fr = new JFrame("ϵͳ��½");
		fr = this;
		this.setTitle("ϵͳ��½");
		this.setSize(520, 130);

		this.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		this.add(p);
		JLabel j1 = new JLabel("�û���");
		j1.setSize(500, 50);
		//j1.setBounds(50,50,30,30);
		//j1.setBounds(arg0, arg1, arg2, arg3);
		JLabel j2 = new JLabel("����");
		j2.setSize(100,200);
		t1 = new JTextField(15);
		t2 = new JPasswordField(20);
		t2.setEchoChar('*');
		JButton bt1 = new JButton("��½");
		JButton bt2 = new JButton("�˳�");
		bt1.setSize(100,50);
		bt2.setSize(100,50);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		p.add(j1);
		p.add(t1);
		p.add(j2);
		p.add(t2);
		p.add(bt1);
		p.add(bt2);
		//fr.setVisible(true);
		this.setVisible(true);
	}
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��½")){
			String name = t1.getText();
			String password = String.valueOf(t2.getPassword());
			/*				if(DataProcessing.searchUser(name)!=null){
				if(DataProcessing.searchUser(name, password)!=null){
					JOptionPane.showMessageDialog(null, "��½�ɹ�", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						Client.Login()
					this.dispose();
					JFrame menu = new MainFr(name);
					menu.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�������", "��ܰ��ʾ", JOptionPane.ERROR_MESSAGE);
					Client.sendData("LOGIN_FALSE");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "�˺Ų�����", "��ܰ��ʾ", JOptionPane.ERROR_MESSAGE);
				Client.sendData("LOGIN_FALSE");
			}*/
			try {
				Client.Login(name,password,this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			this.dispose();
		}
	}

}

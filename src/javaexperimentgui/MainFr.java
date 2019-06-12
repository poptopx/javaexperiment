package javaexperimentgui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javaexperiment.DataProcessing;
import javaexperiment.User;

import javax.swing.JLabel;

public class MainFr extends JFrame implements ActionListener{

	//private JFrame frmv;
	String role;
	/**
	 * Launch the application.
	 */
	String name;
	@Override

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("新增用户")){
			JFrame menux = new UserFrame(name,0);
			menux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			menux.setVisible(true);
		}
		else if(e.getActionCommand().equals("修改用户")){
			JFrame menux = new UserFrame(name,1);
			menux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			menux.setVisible(true);
		}
		else if(e.getActionCommand().equals("删除用户")){
			JFrame menux = new UserFrame(name,2);
			menux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			menux.setVisible(true);
		}else if(e.getActionCommand().equals("档案上传")){
			JFrame menut;
			try {
				User user;
				user = DataProcessing.searchUser(name);
				String role = user.getRole();
				menut = new Fileframe(name,role,1);
				menut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				menut.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("档案下载")){
			JFrame menut;
			try {
				User user;
				user = DataProcessing.searchUser(name);
				String role = user.getRole();
				menut = new Fileframe(name,role,0);
				menut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				menut.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("信息修改")){
			JFrame menus;
			try {
				menus = new Selframe(name);
				menus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				menus.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFr window = new MainFr("kate");
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
	public MainFr(String name) {
		this.name = name;
		try {
			System.out.println(name);
			role = DataProcessing.searchUser(name).getRole();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frmv = new JFrame();
		this.setTitle("档案管理系统V1.0");
		this.setBounds(100, 100, 520, 477);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu1 = new JMenu("用户管理");
		menuBar.add(menu1);
		menu1.addActionListener(this);
		JMenuItem item1 = new JMenuItem("修改用户");
		menu1.add(item1);
		item1.addActionListener(this);

		JMenuItem item2 = new JMenuItem("删除用户");
		menu1.add(item2);
		item2.addActionListener(this);

		JMenuItem item3 = new JMenuItem("新增用户");
		menu1.add(item3);
		item3.addActionListener(this);

		JMenu menu2 = new JMenu("档案管理");
		menuBar.add(menu2);
		menu2.addActionListener(this);
		JMenuItem item4 = new JMenuItem("档案上传");
		menu2.add(item4);
		item4.addActionListener(this);
		JMenuItem item5 = new JMenuItem("档案下载");
		menu2.add(item5);
		item5.addActionListener(this);
		JMenu menu3 = new JMenu("个人信息管理");
		menuBar.add(menu3);
		menu3.addActionListener(this);
		JMenuItem item6 = new JMenuItem("信息修改");
		menu3.add(item6);
		item6.addActionListener(this);
		if(role.equals("administrator")) {
			menu1.setEnabled(true);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(true);
			item2.setEnabled(true);
			item3.setEnabled(true);
			item4.setEnabled(false);
			item5.setEnabled(true);
			item6.setEnabled(true);
		}
		else if(role.equals("operator")){
			menu1.setEnabled(false);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(false);
			item2.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(true);
			item5.setEnabled(true);
			item6.setEnabled(true);
		}
		else if(role.equals("browser")){
			menu1.setEnabled(false);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(false);
			item2.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(false);
			item5.setEnabled(true);
			item6.setEnabled(true);
		}

	}

	

}

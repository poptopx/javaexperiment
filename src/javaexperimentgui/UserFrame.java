package javaexperimentgui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;

import javaexperiment.Client;
import javaexperiment.DataProcessing;
import javaexperiment.User;


import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

	//private JFrame frame;
	int index;
	String adname;
	JComboBox<String> box_name;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame window = new UserFrame("jack",1);
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
	public UserFrame(String name,int i) {
		this.adname = name;
		index = i;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame = new JFrame();
		String[] columnName={"用户名","密码","角色"};
		String[][] rowData=new String[50][3];
		Enumeration<User> e=null;
		try {
			e = DataProcessing.getAllUser();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();;
		}
		User user;
		String[] nameData=new String[50];
		int i=0;
		while(e.hasMoreElements()) {
			user=e.nextElement();
			nameData[i]=rowData[i][0]=user.getName();
			rowData[i][1]=user.getPassword();
			rowData[i][2]=user.getRole();
			i++;
		}
		
		
		this.setBounds(100, 100, 504, 474);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("新增用户",  panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(79, 76, 72, 29);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u53E3\u4EE4");
		lblNewLabel_1.setBounds(79, 134, 72, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u89D2\u8272");
		lblNewLabel_2.setBounds(79, 188, 72, 29);
		panel.add(lblNewLabel_2);
		
		JTextField te1 = new JTextField();
		te1.setBounds(184, 78, 181, 24);
		panel.add(te1);
		te1.setColumns(10);
		
		JPasswordField te2 = new JPasswordField();
		te2.setBounds(184, 139, 181, 24);
		panel.add(te2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("administrator");
		comboBox.addItem("browser");
		comboBox.addItem("operator");
		
				comboBox.setBounds(184, 190, 181, 24);
				panel.add(comboBox);
				
				JButton btnNewButton = new JButton("\u6DFB\u52A0");
				/*btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});*/
				btnNewButton.addActionListener(new ButtonHandler(this,te1,te2,comboBox));
				btnNewButton.setBounds(94, 259, 113, 27);
				panel.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("\u9000\u51FA");
				btnNewButton_1.addActionListener(new ButtonHandler(this));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnNewButton_1.setBounds(252, 259, 113, 27);
				panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("修改用户",  panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("\u53E3\u4EE4");
		label_1.setBounds(85, 111, 30, 18);
		panel_1.add(label_1);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(85, 58, 45, 18);
		panel_1.add(label);
		
		JLabel label_2 = new JLabel("\u89D2\u8272");
		label_2.setBounds(84, 161, 30, 18);
		panel_1.add(label_2);
		
		JPasswordField te4 = new JPasswordField();
		te4.setBounds(170, 108, 180, 24);
		panel_1.add(te4);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(170, 158, 180, 24);
		comboBox_1.addItem("administrator");
		comboBox_1.addItem("browser");
		comboBox_1.addItem("operator");
		//String name = comboBox_1.
		//JComboBox<String> box_name=new JComboBox<String>(nameData);
		panel_1.add(comboBox_1);
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(100, 241, 113, 27);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setBounds(238, 241, 113, 27);
		panel_1.add(button_1);
		box_name=new JComboBox<String>(nameData);
		box_name.setBounds(170, 39, 124, 24);
		panel_1.add(box_name);
		button.addActionListener(new ButtonHandler(this,box_name,te4,comboBox_1));
		button_1.addActionListener(new ButtonHandler(this));
		/*JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(170, 39, 124, 24);
		comboBox_2.addItem("Administrator");
		comboBox_2.addItem("Browser");
		comboBox_2.addItem("Operator");
		//panel_1.add(comboBox_2);*/
	
		

		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("删除用户", panel_2);
		tabbedPane.setSelectedIndex(index);
		//JList<? extends E> list = new JList();
		//panel_2.add(list);
		
		JTable table=new JTable(rowData,columnName) {
			public boolean isCellEditable(int rowIndex,int ColIndex) {
				return false;
			}
		};
		table.setFont(new Font("黑体",Font.PLAIN,18));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setBounds(43, 13, 389, 338);
		scroll.setVisible(true);
		panel_2.setLayout(null);
		//scroll.setSize(100,100);
		//scroll.setBounds(0,0,100,100);
		panel_2.add(scroll);
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setBounds(115, 360, 113, 27);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u9000\u51FA");
		btnNewButton_3.setBounds(250, 360, 113, 27);
		panel_2.add(btnNewButton_3);
		btnNewButton_2.addActionListener(new ButtonHandler(this,table));
		btnNewButton_3.addActionListener(new ButtonHandler(this));
		
	}

	//@Override
	public class ButtonHandler implements ActionListener{
		public JTextField te1=new JTextField();
	    public JPasswordField te2=new JPasswordField();
	    public JFrame frame=new JFrame();
	    public JComboBox<String> box=new JComboBox<String>();
	    public JComboBox<String> box_name=new JComboBox<String>();
	    public JTable table;
	    public String role;
	    ButtonHandler(JFrame frame){
	    	this.frame=frame;
	    }
	    ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2,JComboBox<String> box){
	    	this.frame=frame;
			this.te1=te1;
			this.te2=te2;
			this.box=box;
	    }
	    ButtonHandler(JFrame frame,JComboBox<String> box_name,JPasswordField te2,JComboBox<String> box) {
	    	this.frame=frame;
			this.box_name=box_name;
			this.te2=te2;
			this.box=box;
		}
	    ButtonHandler(JFrame frame,JTable table) {
	    	this.frame=frame;
			this.table=table;
		}
	    public void actionPerformed(ActionEvent e)  {
			String name=te1.getText();
			String password=String.valueOf(te2.getPassword());
			if(e.getActionCommand().equals("修改")) {
				try {/*
					name=(String)box_name.getSelectedItem();
					//name=comboBo
					if(DataProcessing.searchUser(name)!=null) {
						role=(String)box.getSelectedItem();
						DataProcessing.updateUser(name, password, role);
						JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
						Client.sendData("SELFCHANGE_TRUE");
						frame.dispose();
						JFrame userWindow=new UserFrame(adname,1);
						userWindow.setVisible(true);
						//userWindow.showMenu(adname,1);
					}
					else {
						Client.sendData("SELFCHANGE_FALSE");
						JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
					}*/
					//name=(String)box_name.getSelectedItem();
					name=(String)box_name.getSelectedItem();
					role=(String)box.getSelectedItem();
					Client.UpdateUser(name,password,role,frame);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("添加")){
				role=(String)box.getSelectedItem();
				/*try {
					DataProcessing.insertUser(name, password, role);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Client.sendData("SELFCHANGE_TRUE");
				JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
				JFrame userWindow=new UserFrame(adname,1);
				userWindow.setVisible(true);*/
				try {
					Client.AddUser(name,password,role,frame);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			else if(e.getActionCommand().equals("删除")){
				if(table.getSelectedRow()<0) ; 
				else {
					String name_del=(String) table.getValueAt(table.getSelectedRow(), 0);
					if(name_del.equals(adname)) {
						JOptionPane.showMessageDialog(null, "无法删除", "温馨提示", JOptionPane.ERROR_MESSAGE);
						//Client.sendData("SELFCHANGE_FALSE");
					}
					else {/*
						try {
							if(DataProcessing.deleteUser(name_del)) {
								JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
								Client.sendData("SELFCHANGE_TRUE");
							}
							else {
								
								Client.sendData("SELFCHANGE_FALSE");
								JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}*/
						try {
							try {
								Client.DelUser(name_del,frame);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						frame.dispose();
						JFrame userWindow=new UserFrame(adname,2);
						userWindow.setVisible(true);
					}
					
				}
			}	
			else if(e.getActionCommand().equals("退出")) {
				frame.dispose();
			}
		}
	}

	//@Override

}

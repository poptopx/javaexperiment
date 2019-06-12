package javaexperimentgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javaexperiment.Client;
import javaexperiment.DataProcessing;
import javaexperiment.Doc;
//import javax.print.Doc;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Fileframe extends JFrame {

	private JFrame frame = this;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String uname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fileframe window = new Fileframe("jack","Operator",0);
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
	public Fileframe(String name,String role ,int index) {
		this.uname = name;
		initialize(role,index);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String role,int index) {
		//frame = new JFrame();
		this.setBounds(100, 100, 491, 479);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setTitle("文件管理");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("文件下载", null, panel, null);
		
		//panel.setLayout(null);
		String[] columnName={"ID","Creator","Time","FileName","Description"};
		String[][] rowData=new String[50][5];
		Enumeration<Doc> e = null;
		try {
			 e = DataProcessing.getAllDocs();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();;
		}
		Doc doc;
		String[] nameData=new String[50];
		int i=0;
		while(e.hasMoreElements()) {
			doc=e.nextElement();
			nameData[i]=rowData[i][0]=doc.getID();
			rowData[i][1]=doc.getCreator();
			rowData[i][2]=doc.getTimestamp().toString();
			rowData[i][3]=doc.getFilename();
			rowData[i][4]=doc.getDescription();
			i++;
		}
		panel.setLayout(null);
		//@SuppressWarnings("serial")
		JTable table=new JTable(rowData,columnName) {
			public boolean isCellEditable(int rowIndex,int ColIndex) {
				return false;
			}
		};
		JScrollPane scroll=new JScrollPane(table);
		scroll.setLocation(14, 13);
		panel.add(scroll);
		scroll.setSize(452,340);
		
		JButton btnNewButton_2 = new JButton("\u4E0B\u8F7D");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uploadpath="d:\\uploadfile\\";
				String downloadpath="d:\\downloadfile\\";
				JFileChooser fileChooser=new JFileChooser();
			    if(e.getActionCommand()=="下载") {
			    	int op=JOptionPane.showConfirmDialog(null, "确认下载", "温馨提示", JOptionPane.YES_NO_OPTION);
			    	if(op==JOptionPane.YES_OPTION) {
				    	if(table.getSelectedRow()<0) 
				    		; 
				    	else {
				    		String id=(String)table.getValueAt(table.getSelectedRow(), 0);
				    		try {
								Client.Download(id,frame);
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

				    		/*
				    		String id=(String)table.getValueAt(table.getSelectedRow(), 0);
				    		byte[] buffer = new byte[1024];
				    		Doc doc = null;
							try {
								doc = DataProcessing.searchDoc(id);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				    		File file1=new File(uploadpath);
				    		if(!file1.exists()){//如果文件夹不存在
				    			file1.mkdir();//创建文件夹
				    		}
				    		File file2=new File(downloadpath);
				    		if(!file2.exists()){//如果文件夹不存在
				    			file2.mkdir();//创建文件夹
				    		}
				    		if(doc==null)
				    			return ;
				    		try{
				    		File tempFile = new File(uploadpath+doc.getFilename());
				    		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
				    		BufferedOutputStream  targetfile = new BufferedOutputStream(new FileOutputStream(downloadpath+doc.getFilename()));
				    		while(true){
				    			int byteRead = infile.read(buffer);
				    			if(byteRead==-1)
				    				break;
				    			targetfile.write(buffer,0,byteRead);
					    		infile.close();
					    		targetfile.close();
				    			}
				    		}catch(Exception e1){
				    			
				    		}
				    		JOptionPane.showMessageDialog(null, "下载成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
				    */
				    	}
				  }
			   }
			  }
			}
			    	);
		btnNewButton_2.setBounds(96, 366, 113, 27);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u53D6\u6D88");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(243, 366, 113, 27);
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("文件上传", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u6863\u6848\u53F7");
		label.setBounds(65, 37, 72, 18);
		panel_1.add(label);
		
		JLabel lblNewLabel = new JLabel("\u6863\u6848\u63CF\u8FF0");
		lblNewLabel.setBounds(65, 92, 72, 18);
		panel_1.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u6587\u4EF6\u5730\u5740");
		label_1.setBounds(65, 201, 72, 18);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(194, 34, 161, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 89, 160, 72);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(194, 198, 161, 24);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6253\u5F00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					JFileChooser fileChooser=new JFileChooser();
	    			fileChooser.setFileSelectionMode(0);
	    			int state=fileChooser.showOpenDialog(null);
	    			if(state==1) {
	    				return ;
	    			}
	    			else {
	    				File file=fileChooser.getSelectedFile();
	    				textField_2.setText(file.getAbsolutePath());
	    			}
	    		}
	
			}
		});
		btnNewButton.setBounds(369, 197, 85, 27);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("上传");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 			int op=JOptionPane.showConfirmDialog(null, "确认上传", "温馨提示", JOptionPane.YES_NO_OPTION);
    			if(op==JOptionPane.YES_OPTION) {
    				String ID=null,description=null,filename=null;
    				ID=textField.getText();
        			description=textField_1.getText();
        			filename=textField_2.getText();
        			System.out.println("id"+ID+"id"+description+filename);
        		if(ID.equals("")||description.equals("")||filename.equals("")){
        			JOptionPane.showMessageDialog(null, "文件信息未填写完整", "温馨提示", JOptionPane.ERROR_MESSAGE);
        			
        		}
        		else
        			
        		try{
        			Client.Upload(ID,uname,description,filename,frame);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

        			/*
        			byte[] buffer = new byte[1024];
        			String uploadpath="d:\\uploadfile\\";
        			File file2=new File(uploadpath);
        			if(!file2.exists()){//如果文件夹不存在
        				file2.mkdir();//创建文件夹
        			}
        			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
        			int len = filename.length();

        			while(filename.charAt(len-1)!='\\')
        					len--;
        			String name = filename.substring(len, filename.length());
         			System.out.println(filename+" "+name);
        			File file1=new File(filename);
        			try{
        			if(file1.exists()){
        				//System.out.println(name);
        				if(DataProcessing.insertDoc(ID, uname, timestamp, description, name)){
        					Doc doc=DataProcessing.searchDoc(ID);
        					BufferedInputStream infile = new BufferedInputStream(new FileInputStream(filename));
        					BufferedOutputStream  targetfile = new BufferedOutputStream(new FileOutputStream(uploadpath+name));
        					while(true){
        						int byteRead = infile.read(buffer);
        						if(byteRead==-1)
        							break;
        						targetfile.write(buffer,0,byteRead);
        					}
        					infile.close();
        					targetfile.close();
        				}
        				else
        					return ;
       
        			}else
            			return ;
        			}catch(Exception e2){
        				
        			}
        			JOptionPane.showMessageDialog(null, "上传成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
        			frame.dispose();
					JFrame userWindow=new Fileframe(name,role,1);
					userWindow.setVisible(true);
					*/
        		}
    			}
        			
			});
		
		
		btnNewButton_1.setBounds(104, 261, 113, 27);
		panel_1.add(btnNewButton_1);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setBounds(257, 261, 113, 27);
		panel_1.add(button);
		
		if(role.equalsIgnoreCase("operator")){
			tabbedPane.setEnabledAt(1, true);
		}
		else
			tabbedPane.setEnabledAt(1, false);
		tabbedPane.setSelectedIndex(index);
	}
}

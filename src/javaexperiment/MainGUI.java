package javaexperiment;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import javaexperimentgui.loginFrame;
public class MainGUI {

	public static void main(String[] args) throws Exception{
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";
		String user="root";
		String password="123456";
		
			try {
				DataProcessing.Init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		System.out.println("Attempting connecgtion>>>");
		Frame loginframe = new loginFrame();
		Client cl ;
//		cl.runClient();
		if ( args.length == 0 )
	         cl = new Client( "127.0.0.1" ); 
	      else
	         cl = new Client( args[ 0 ] ); 
	      cl.runClient();  
			//Frame loginframe = new loginFrame();

//	
//		Client cl = new Client("127.0.0.1");
//		cl.runClient();
/*		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{

					JFrame loginframe = new loginFrame();
					loginframe.setVisible(true);
					if(loginframe.isVisible()){
					Client cl = new Client("127.0.0.1");
					cl.runClient();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	*/
		
    }
}

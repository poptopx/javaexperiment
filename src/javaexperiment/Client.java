package javaexperiment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javaexperimentgui.MainFr;
public class Client extends JFrame {
	private static String user_role="";
	private static JFrame Jframe;
	private static ObjectOutputStream output; 
	private static ObjectInputStream input; 
	private String message = null; 
	private static String user_name = null;
	private static String user_password = null;
	private String chatServer; 
	private static Socket client;
	public Client( String host ){
      super( "Client" );
   }
	public void runClient(){
      try{
         connectToServer(); 
         getStreams(); 
         try {
			processConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
      }
      catch ( EOFException eofException ) {
         displayMessage( "Client terminated connection" );
      } 
      catch ( IOException ioException ){
         ioException.printStackTrace();
      } 
      finally{
         try {
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} 
      } 
   } 
   private void connectToServer() throws IOException{      
      displayMessage( "Attempting connection" );
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );
      displayMessage( "Connected to: " + 
         client.getInetAddress().getHostName() );
   }
   private void getStreams() throws IOException{
      output = new ObjectOutputStream( client.getOutputStream() );      
      output.flush(); 
      input = new ObjectInputStream( client.getInputStream() );
      displayMessage( "Got I/O streams" );
   } 
   private void processConnection() throws IOException, ClassNotFoundException{
      do { 
         Message msg =(Message) input.readObject();
         message = msg.get_message();
         if(message.equals("LOGIN_TRUE")) {
        	 user_role=msg.get_content().elementAt(0);
        	 String user_name = msg.get_content().elementAt(1);
        	 MainFr menuWindow=new MainFr(user_name);
		     menuWindow.setVisible(true);
			 Jframe.dispose();	 
         }
         else if(message.equals("LOGIN_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "账号或密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("SELFCHANGE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
        	 String password=msg.get_content().elementAt(0);
        	 user_password=password;
        	 System.out.println("SELFCHANGE_SUCCESS");
         }
         else if(message.equals("SELFCHANGE_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "修改失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("DELETE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 output.writeObject(new Message("displayUser",null));
			 output.flush();
			 System.out.println("DELETE_SUCCESS");
         }
         else if(message.equals("DELETE_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("ADD_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 output.writeObject(new Message("displayUser",null));
			 output.flush();
			 System.out.println("ADD_SUCCESS");
         }
         else if(message.equals("ADD_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "添加失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("UPDATE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 output.writeObject(new Message("displayUser",null));
			 output.flush();
			 System.out.println("UPDATE_SUCCESS");
         }
         else if(message.equals("UPDATE_FLASE")) {
        	 JOptionPane.showMessageDialog(null, "修改失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("UPLOAD_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "上传成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 System.out.println("UPLOAD_SUCCESS");
			 Jframe.dispose();
			 output.writeObject(new Message("displayDoc",null));
			 output.flush();
         }
         else if(message.equals("UPLOAD_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "上传失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("SERVER>>> CLIENT_FILE_DOWN")) {
        	 String filename=msg.get_content().elementAt(0).toString();
        	 Long fileLength=Long.parseLong(msg.get_content().elementAt(1));
			FileOutputStream fos=new FileOutputStream(new File("D:\\downloadfile\\"+filename));
 			 byte[] sendBytes=new byte[1024];
 			 int transLen=0;
 			 System.out.println("----开始下载文件<"+filename+">,文件大小为<"+fileLength+">----");
 			 while(true) {
 				 int read=0;
 				 read=input.read(sendBytes);
 				 if(read==-1) break;
 				 transLen+=read;
 				 System.out.println("下载文件进度"+100*transLen*1.0/fileLength+"%...");
 				 fos.write(sendBytes,0,read);
 				 fos.flush();
 				 if(transLen>=fileLength) break;
 			 }
 			 fos.close();
 			 System.out.println("----下载文件<"+filename+">成功----");
 			 JOptionPane.showMessageDialog(null, "下载成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
         }
      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
   } 
   public static void closeConnection() throws IOException{
      try{
         output.close(); 
         input.close(); 
         client.close(); 
      }
      catch ( IOException e ) {
         e.printStackTrace();
      } 
   } 
   public static void prepareToClose() throws IOException {
	   String logout="CLIENT>>> CLIENT_LOGOUT";
	   output.writeObject(new Message(logout,null));
	   output.flush();
	   System.out.println(logout);
   }
   static void displayMessage( String messageToDisplay ){
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() 
            {
            	System.out.println(messageToDisplay);
            } 
         } 
      ); 
   } 
   synchronized public static void Login(String name,String password,JFrame frame) throws IOException {
	   user_name=name;
	   user_password=password;
	   Jframe=frame;
	   Vector<String> content=new Vector<String>();
	   content.addElement(name);
	   content.addElement(password);
	   output.writeObject(new Message("CLIENT>>> CLIENT_LOGIN",content));
	   output.flush();
	   System.out.println("CLIENT>>> CLIENT_LOGIN");
   }
   public synchronized static void ChangeSelfInfo(String old_password,String new_password,String new_password2) throws IOException {
	   if(user_password.equals(old_password)) {
		   if(new_password.equals(new_password2)) {
			   String changeSelfInfo="CLIENT>>> CLIENT_SELF_MOD";
			   System.out.println("CLIENT>>> CLIENT_SELF_MOD");
			   Vector<String> content=new Vector<String>();
			   content.addElement(user_name);
			   content.addElement(new_password);
			   content.addElement(user_role);
			   output.writeObject(new Message(changeSelfInfo,content));
			   output.flush();
		   }
		   else {
			   JOptionPane.showMessageDialog(null, "两次输入的新密码不一致", "温馨提示", JOptionPane.ERROR_MESSAGE);
		   }
	   }
	   else {
		   JOptionPane.showMessageDialog(null, "密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
	   }
   }
   
   static void Display_User() throws IOException {	   
	   output.writeObject(new Message("displayUser",null));
	   output.flush();
   }
   static void Display_Doc() throws IOException {
	   output.writeObject(new Message("displayDoc",null));
	   output.flush();
   }
   public synchronized static void DelUser(String del_name, JFrame frame) throws IOException {
	   Jframe=frame;
	   if(del_name.equals(user_name)) {
		   JOptionPane.showMessageDialog(null, "无法删除", "温馨提示", JOptionPane.ERROR_MESSAGE);
	   }
	   else {
		   Vector<String> content=new Vector<String>();
		   content.addElement(del_name);
		   output.writeObject(new Message("USER_DELETE",content));
		   output.flush();
		   System.out.println("CLIENT>>> "+del_name+ " USER_DELETE");
	   }
   }
   public synchronized static void UpdateUser(String name,String password,String role,JFrame frame) throws IOException {
	   Jframe=frame;
	   Vector<String> content=new Vector<String>();
	   content.addElement(name);
	   content.addElement(password);
	   content.addElement(role);
	   output.writeObject(new Message("USER_UPDATE",content));
	   output.flush();
	   System.out.println("CLIENT>>> "+name+ "USER_UPDATE");
   }
   public synchronized static void AddUser(String name, String password,String role,JFrame frame) throws IOException {
	   Jframe=frame;
	   Vector<String> content=new Vector<String>();
	   content.addElement(name);
	   content.addElement(password);
	   content.addElement(role);
	   output.writeObject(new Message("USER_ADD",content));
	   output.flush();
	   System.out.println("CLIENT>>> "+name+ "USER_ADD");
   }
   public synchronized static void Download(String ID,JFrame frame) throws IOException {
	   Jframe=frame;
	   Vector<String> content=new Vector<String>();
	   content.addElement(ID);
	   output.writeObject(new Message("DOWNLOAD",content));
       output.flush();
       System.out.println("CLIENT>>> CLIENT_FILE_DOWN");
   }
   public synchronized static void Upload(String ID,String Creator,String description,String filename,JFrame frame) throws IOException{
	   Jframe=frame;
	   Vector<String> content=new Vector<String>();
	   File file=new File(filename.trim());
	   String fileName=file.getName();
	   String fileLength=String.valueOf(file.length());
	   content.addElement(ID);
	   content.addElement(Creator);
	   content.addElement(description);
	   content.addElement(fileName);
	   content.addElement(fileLength);
	   output.writeObject(new Message("UPLOAD",content));
	   output.flush();
	   FileInputStream fis=new FileInputStream(file);
	   byte[] sendBytes=new byte[1024];
	   int length=0;
	   while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
		   output.write(sendBytes,0,length);
		   output.flush();
	   }
	   fis.close();
	   System.out.println("CLIENT>>> CLIENT_FILE_UP");
   }
   
}

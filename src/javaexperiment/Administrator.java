package javaexperiment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {
	
	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	public boolean changeUserInfo(String name,String password,String role) throws SQLException{
		
		return DataProcessing.updateUser(name, password, role);
	}
	public boolean delUser(String name) throws SQLException{
		return DataProcessing.deleteUser(name);
	}
	public boolean addUser(String name,String password,String role) throws SQLException{
		return DataProcessing.insertUser(name, password, role);
	}
	public void listUser() throws SQLException{
		Enumeration<User> u = DataProcessing.getAllUser();
		User user;
		while(u.hasMoreElements()){
			user = u.nextElement();
			System.out.println("Name: "+user.getName()+"  Password: "+user.getPassword()+"  Role: "+user.getRole());
		}
		
	}
	public  void showMenu() throws IOException,SQLException{
		String password,name,role;
		String tipo1 = "----��ӭ���뵵������Ա����----";
		String tipo2 = "\t1.�޸��û�\n"+"\t2.ɾ���û�\n"+"\t3.�����û�\n"+"\t4.�г��û�\n"+"\t5.�����ļ�\n"+"\t6.�ļ��б�\n"+"\t7.�޸ģ����ˣ�����\n"+"\t8.�˳�\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "��ѡ��˵�";
		String filename,docunumber,docudescription;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println(tipo1);
			System.out.println(tipo2);
			System.out.println(tipo3);
			System.out.println(tipo4);
			int num = scanner.nextInt();
			if(num!=1&&num!=2&&num!=3&&num!=4&&num!=5&&num!=6&&num!=7&&num!=8){
				System.out.println(tipo4);
				num = scanner.nextInt();
			}
			else{
				switch(num){
				case 1:
					System.out.println("�޸��û�");
					System.out.println("�������û���");
					name = scanner.next();
					System.out.println("����������");
					password = scanner.next();
					System.out.println("�������ɫ");
					role = scanner.next();
					try{
						if(changeUserInfo(name, password, role)){
							System.out.println("�޸ĳɹ���");
						}
						else
							System.out.println("�޸�ʧ�ܣ�");	
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 2:
					System.out.println("ɾ���û�");
					System.out.println("�������û���");
					name = scanner.next();
					try{
						if(delUser(name)){
							System.out.println("ɾ���ɹ���");
						}
						else
							System.out.println("ɾ��ʧ�ܣ�");
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 3:
					System.out.println("�����û�");
					System.out.println("�������û���");
					name = scanner.next();
					System.out.println("����������");
					password = scanner.next();
					System.out.println("�������ɫ");
					role = scanner.next();
					try{
						if(addUser(name, password, role)){
							System.out.println("��ӳɹ���");
						}
						else
							System.out.println("���ʧ�ܣ�");
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 4:
					try{
						listUser();
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 5:
					System.out.println("�����ļ�");
					System.out.println("�����뵵����");
					docunumber = scanner.next();
					try{
						if(downloadFile(docunumber)){
							System.out.println("���سɹ���");
						}
						else
							System.out.println("����ʧ�ܣ�");
					}catch(IOException e){
						System.out.println("�ļ����ʴ���"+e.getMessage());
					}
					break;
				case 6:
					System.out.println("�ļ��б�");
					try{
						showFileList();
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 7:
					System.out.println("�޸�����");
					System.out.println("������������");
					try{
						password = scanner.next();
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 8:
					exitSystem();
					break;
				}
			}
		}
	}


}

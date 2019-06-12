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
		String tipo1 = "----欢迎进入档案管理员界面----";
		String tipo2 = "\t1.修改用户\n"+"\t2.删除用户\n"+"\t3.新增用户\n"+"\t4.列出用户\n"+"\t5.下载文件\n"+"\t6.文件列表\n"+"\t7.修改（本人）密码\n"+"\t8.退出\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "请选择菜单";
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
					System.out.println("修改用户");
					System.out.println("请输入用户名");
					name = scanner.next();
					System.out.println("请输入密码");
					password = scanner.next();
					System.out.println("请输入角色");
					role = scanner.next();
					try{
						if(changeUserInfo(name, password, role)){
							System.out.println("修改成功！");
						}
						else
							System.out.println("修改失败！");	
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 2:
					System.out.println("删除用户");
					System.out.println("请输入用户名");
					name = scanner.next();
					try{
						if(delUser(name)){
							System.out.println("删除成功！");
						}
						else
							System.out.println("删除失败！");
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 3:
					System.out.println("新增用户");
					System.out.println("请输入用户名");
					name = scanner.next();
					System.out.println("请输入密码");
					password = scanner.next();
					System.out.println("请输入角色");
					role = scanner.next();
					try{
						if(addUser(name, password, role)){
							System.out.println("添加成功！");
						}
						else
							System.out.println("添加失败！");
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 4:
					try{
						listUser();
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 5:
					System.out.println("下载文件");
					System.out.println("请输入档案号");
					docunumber = scanner.next();
					try{
						if(downloadFile(docunumber)){
							System.out.println("下载成功！");
						}
						else
							System.out.println("下载失败！");
					}catch(IOException e){
						System.out.println("文件访问错误："+e.getMessage());
					}
					break;
				case 6:
					System.out.println("文件列表");
					try{
						showFileList();
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 7:
					System.out.println("修改密码");
					System.out.println("请输入新密码");
					try{
						password = scanner.next();
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
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

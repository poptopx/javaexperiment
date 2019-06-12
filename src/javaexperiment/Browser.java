package javaexperiment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Browser extends User {
	
	Browser(String name, String password, String role) {
		super(name, password, role);
	}
	public  void showMenu() throws IOException,SQLException{
		String password;
		String tipo1 = "----欢迎进入档案浏览员界面----";
		String tipo2 = "\t1.下载文件\n"+"\t2.文件列表\n"+"\t3.修改密码\n"+"\t4.退出\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "请选择菜单";
		Scanner scanner = new Scanner(System.in);
		//int num = scanner.nextInt();
		String filename,docunumber,docudescription;
		while(true){
			System.out.println(tipo1);
			System.out.println(tipo2);
			System.out.println(tipo3);
			System.out.println(tipo4);
			int num = scanner.nextInt();
			if(num!=1&&num!=2&&num!=3&&num!=4){
				System.out.println(tipo4);
				num = scanner.nextInt();
			}
			else{
				switch(num){
				case 1:
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
				case 2:
					System.out.println("文件列表");
					try{
						showFileList();
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 3:
					System.out.println("修改密码");
					System.out.println("请输入新密码");
					password = scanner.next();
					try{
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 4:
					exitSystem();
					break;
				}
			}
		}
	}

}

package javaexperiment;
import java.io.BufferedInputStream;
import java.sql.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
public class Operator extends User {

	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	public boolean uploadFile(String filename,String docunumber,String docudescription) throws FileNotFoundException, SQLException, IOException{
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
		File file1=new File(filename);
		if(file1.exists()){
			if(DataProcessing.insertDoc(docunumber, getName(), timestamp, docudescription, name)){
				Doc doc=DataProcessing.searchDoc(docunumber);
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
				return false;
		}
		else
			return false;
		
		return true;
	}
	public  void showMenu() throws IOException, SQLException{
		String password;
		String tipo1 = "----欢迎进入档案操作员界面----";
		String tipo2 = "\t1.上传文件\n"+"\t2.下载文件\n"+"\t3.文件列表\n"+"\t4.修改密码\n"+"\t5.退出\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "请选择菜单";
//		System.out.println(tipo1);
//		System.out.println(tipo2);
//		System.out.println(tipo3);
		String filename,docunumber,docudescription;
		Scanner scanner = new Scanner(System.in);
		//int num = scanner.nextInt();
		while(true){
			System.out.println(tipo1);
			System.out.println(tipo2);
			System.out.println(tipo3);
			System.out.println(tipo4);
			int num = scanner.nextInt();
			if(num!=1&&num!=2&&num!=3&&num!=4&&num!=5){
				System.out.println(tipo4);
				num = scanner.nextInt();
			}
			else{
				switch(num){
				case 1:
					System.out.println("上传文件");
					System.out.println("请输入源文件名");
					filename = scanner.next();
					System.out.println("请输入档案号");
					docunumber = scanner.next();
					System.out.println("请输入档案描述");
					docudescription = scanner.next();
					if(uploadFile(filename, docunumber, docudescription)){
						System.out.println("上传成功！");
					}
					else
						System.out.println("上传失败！");
					break;
				case 2:
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
				case 3:
					try{
						System.out.println("文件列表");
						showFileList();
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 4:
					System.out.println("修改密码");
					System.out.println("请输入新密码");
					password = scanner.next();
					try{
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("数据库异常："+e.getMessage());
					}
					break;
				case 5:
					exitSystem();
					break;
				}
			}
		}
		
	}
	public static void main(String[] args) {

	}

}

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
		if(!file2.exists()){//����ļ��в�����
			file2.mkdir();//�����ļ���
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
		String tipo1 = "----��ӭ���뵵������Ա����----";
		String tipo2 = "\t1.�ϴ��ļ�\n"+"\t2.�����ļ�\n"+"\t3.�ļ��б�\n"+"\t4.�޸�����\n"+"\t5.�˳�\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "��ѡ��˵�";
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
					System.out.println("�ϴ��ļ�");
					System.out.println("������Դ�ļ���");
					filename = scanner.next();
					System.out.println("�����뵵����");
					docunumber = scanner.next();
					System.out.println("�����뵵������");
					docudescription = scanner.next();
					if(uploadFile(filename, docunumber, docudescription)){
						System.out.println("�ϴ��ɹ���");
					}
					else
						System.out.println("�ϴ�ʧ�ܣ�");
					break;
				case 2:
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
				case 3:
					try{
						System.out.println("�ļ��б�");
						showFileList();
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 4:
					System.out.println("�޸�����");
					System.out.println("������������");
					password = scanner.next();
					try{
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
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

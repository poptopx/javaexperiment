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
		String tipo1 = "----��ӭ���뵵�����Ա����----";
		String tipo2 = "\t1.�����ļ�\n"+"\t2.�ļ��б�\n"+"\t3.�޸�����\n"+"\t4.�˳�\n";
		String tipo3 = "-----------------------------";
		String tipo4 = "��ѡ��˵�";
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
				case 2:
					System.out.println("�ļ��б�");
					try{
						showFileList();
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
					}
					break;
				case 3:
					System.out.println("�޸�����");
					System.out.println("������������");
					password = scanner.next();
					try{
						changeSelfInfo(password);
					}catch(SQLException e){
						System.out.println("���ݿ��쳣��"+e.getMessage());
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

package javaexperiment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class Main {

	public static void main(String[] args) throws SQLException,IOException,InputMismatchException{
		String tip1 = "----��ӭ���뵵������ϵͳ----\n\t  "+"1.��½\n    \t  2.�˳�\n"+"---------------------------";
		String tip2 = "��ѡ��˵�";
		String tip3 = "ϵͳ�˳���ллʹ�ã�";
		String tip4 = "�������û���";
		String tip5 = "����������";
		String name,password;
		int num;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println(tip1);
			System.out.println(tip2);
			try{
				num = scanner.nextInt();
			if(num!=1&&num!=2){
				//System.out.println(tip2);
				//num = scanner.nextInt();
				continue;
			}
			else{
				switch(num){
				case 1:
					System.out.println(tip4);
					name = scanner.next();
					System.out.println(tip5);
					password = scanner.next();
					try{
						User user = DataProcessing.searchUser(name, password);
						if(user == null)
							System.out.println("�û������ڻ��������");
						else
							user.showMenu();
					}catch(SQLException e){
						System.out.println("���ݿ����"+e.getMessage());
					}
					break;
				case 2:
					System.out.println("ϵͳ�˳�, ллʹ�� ! ");
					System.exit(0);
					break;
				}
			}
			}catch(InputMismatchException e){
				scanner.nextLine();
			}
		}
	}

}

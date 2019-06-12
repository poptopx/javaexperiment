package javaexperiment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class Main {

	public static void main(String[] args) throws SQLException,IOException,InputMismatchException{
		String tip1 = "----欢迎进入档案管理系统----\n\t  "+"1.登陆\n    \t  2.退出\n"+"---------------------------";
		String tip2 = "请选择菜单";
		String tip3 = "系统退出，谢谢使用！";
		String tip4 = "请输入用户名";
		String tip5 = "请输入密码";
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
							System.out.println("用户不存在或密码错误");
						else
							user.showMenu();
					}catch(SQLException e){
						System.out.println("数据库错误："+e.getMessage());
					}
					break;
				case 2:
					System.out.println("系统退出, 谢谢使用 ! ");
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

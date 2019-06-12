package javaexperiment;

import java.sql.SQLException;
import java.util.Enumeration;
import java.io.*;



public abstract class User {
	private String name;
	private String password;
	private String role;
	
	String uploadpath="d:\\uploadfile\\";
	String downloadpath="d:\\downloadfile\\";
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException{
		//д�û���Ϣ���洢
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("�޸ĳɹ�");
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String ID) throws IOException, SQLException{
		byte[] buffer = new byte[1024];
		Doc doc=DataProcessing.searchDoc(ID);
		File file1=new File(uploadpath);
		if(!file1.exists()){//����ļ��в�����
			file1.mkdir();//�����ļ���
		}
		File file2=new File(downloadpath);
		if(!file2.exists()){//����ļ��в�����
			file2.mkdir();//�����ļ���
		}
		if(doc==null)
			return false;
		File tempFile = new File(uploadpath+doc.getFilename());
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream  targetfile = new BufferedOutputStream(new FileOutputStream(downloadpath+doc.getFilename()));
		while(true){
			int byteRead = infile.read(buffer);
			if(byteRead==-1)
				break;
			targetfile.write(buffer,0,byteRead);
		}
		infile.close();
		targetfile.close();
		return true;
	}
	
	public void showFileList() throws SQLException{
		Enumeration<Doc> e =DataProcessing.getAllDocs();
		Doc doc;
		while(e.hasMoreElements()){
			doc=e.nextElement();
			System.out.println("ID:"+doc.getID()+"\t Creator:"+doc.getCreator()+"\t Timestamp:"+doc.getTimestamp()+"\t Description:"+doc.getDescription()+"\t Filename:"+doc.getFilename());
		}
	}
	
	
	public void exitSystem(){
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public abstract void showMenu() throws IOException, SQLException;
	

}

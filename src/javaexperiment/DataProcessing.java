package javaexperiment;
import java.util.Enumeration;
import java.util.Hashtable;



import java.sql.*;
import java.sql.*;
public  class DataProcessing {
/*
	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));
	
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
		
		
	}
	
*/
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static boolean connectedToDatabase = false;
	//this.Init();
	//Init();
	
	public static void connectedToDabase(String driverName,String url,String user,String password){
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url,user,password);
			connectedToDatabase = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void disconnectFromDatabase(){
		if(connectedToDatabase){
			try{
				resultSet.close();
				statement.close();
				connection.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				connectedToDatabase = false;
			}
		}
	}
	
	public static  void Init() throws Exception{
		// connect to database
		String driverName="com.mysql.jdbc.Driver";               // 加载数据库驱动类
	    String url="jdbc:mysql://localhost:3306/document?useSSL=false";       // 声明数据库的URL
	    String user="root";                                      // 数据库用户
	    String password="123456";
		// update database connection status
//		if (Math.random()>0.2)
//			connectToDB = true;
//		else
//			connectToDB = false;
	    Class.forName(driverName);
	    connection = DriverManager.getConnection(url, user, password);
	    connectedToDatabase = true;
	    /*statement = connection.createStatement(
	    		ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY);*/
	    
	}
	
	public static Doc searchDoc(String ID) throws SQLException {
		Doc temp = null;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from doc_info where Id='"+ID+"'";
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			String ID1 = resultSet.getString("Id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			temp = new Doc(ID1, creator, timestamp, description, filename);
		}
		return temp;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{
		//Doc temp = null;
		Hashtable<String,Doc> docs = new Hashtable<String,Doc>();
		Enumeration<Doc> e ;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from doc_info";
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			String ID1 = resultSet.getString("Id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			docs.put(ID1,new Doc(ID1,creator,timestamp,description,filename));
			//temp = new Doc(ID1, creator, timestamp, description, filename);
		}
		e = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		//Doc doc;		
		
		/*
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
		*/
		
		/*String sql="select * from doc_info";
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()){
			String ID1 = resultSet.getString("Id");
			if(ID.equals(ID1))
				return false;
			//docs.put(ID1,new Doc(ID1,creator,timestamp,description,filename));
			//temp = new Doc(ID1, creator, timestamp, description, filename);
		}*/
		
		//String sql = "INSERT INTO doc_info(Id,creator,timestamo,description,filename)VALUES(?,?,?,?,?)";
		/*
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ID);
		preparedStatement.setString(2, creator);
		preparedStatement.setTimestamp(3, timestamp);
		preparedStatement.setString(4, description);
		preparedStatement.setString(5, filename);
			//System.out.println(ID+creator+timestamp+description+filename);
		int a = preparedStatement.executeUpdate();
		if(a!=0)
			return true;
		else
			return false;*/
		if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");
		 
		 statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 String sql="select * from doc_info where Id='"+ID+"'";
		 resultSet=statement.executeQuery(sql);
		 
	     if(resultSet.next()) 
	    	 return false;
	     sql="insert into doc_info(Id,creator,timestamp,description,filename) values "+"('"+ID+"','"+creator+"','"+timestamp+"','"+description+"','"+filename+"')";
	     if(statement.executeUpdate(sql)>0) return true;
	     else return false;

	} 
	
	public static User searchUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//			throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		/*if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;*/
		User user = null;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info where username='"+name+"'";
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()){
			String uname = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			if(role.equals("administrator")){
				user = new Administrator(uname, password, role);
			}
			else if(role.equals("browser")){
				user = new Browser(uname, password, role);
			}else if(role.equals("operator")){
				user = new Operator(uname, password, role);
			}
		}
		
		return user;
	}
	
	public static User searchUser(String name, String password) throws SQLException {
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		/*
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;*/
		User user = null;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info where username='"+name+"'";
		resultSet = statement.executeQuery(sql);
		
			if(resultSet.next()){
				String uname = resultSet.getString("username");
				String upassword = resultSet.getString("password");
				String role = resultSet.getString("role");
				//System.out.println(uname+""+upassword+""+role);
				if(upassword.equals(password)){
				
				
				if(role.equals("administrator")){
					user = new Administrator(uname, password, role);
				}
				else if(role.equals("browser")){
					user = new Browser(uname, password, role);
				}else if(role.equals("operator")){
					//System.out.println(uname+""+upassword+""+role);
					user = new Operator(uname, password, role);
				}
			}
		}
		
		return user;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
//		Enumeration<User> e  = users.elements();
//		return e;
		Hashtable<String,User> users = new Hashtable<String,User>();
		Enumeration<User> e ;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info";
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			String name = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			if(role.equals("administrator")){
				users.put(name, new Administrator(name,password,role));
			}
			else if(role.equals("browser")){
				users.put(name, new Browser(name,password,role));
			}else if(role.equals("operator")){
				users.put(name, new Operator(name,password,role));
			}
			
			//temp = new Doc(ID1, creator, timestamp, description, filename);
		}
		e = users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		/*User user;
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Update" );
		
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;*/
		//User user = null;
		if(!connectedToDatabase){
			throw new SQLException("未连接到数据库");
		}
		
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info where username='"+name+"'";
		resultSet = statement.executeQuery(sql);
		if(!resultSet.next()) 
			return false;
		 sql="update user_info set password='"+password+"',role='"+role+"' where username='"+name+"'";
		 if(statement.executeUpdate(sql)>0) 
			 return true;
		 else 
			 return false;

			
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
//		User user;
		
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Insert" );
		
		/*if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}*/
//		String sql="select * from user_info where username='"+name+"'";
//		resultSet = statement.executeQuery(sql);
//		if(!resultSet.next()) 
//			return false;
//	     sql="update user_info set password='"+password+"',role='"+role+"' where username='"+name+"'";
//		 if(statement.executeUpdate(sql)>0) 
//			 return true;
//		 else 
//		 return false;
		/*
		 String sql = "INSERT INTO user_info(name,password,role)VALUES(?,?,?)";
		 preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setString(1, name);
		 preparedStatement.setString(2, password);
	     preparedStatement.setString(3, role);


		int a = preparedStatement.executeUpdate();
		if(a!=0)
			return true;
		else
			return false;
			*/
		if(!connectedToDatabase) 
			throw new SQLException("Not Connected to Database.");
		 
		 statement=connection.createStatement(
				 ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_UPDATABLE);
		 String sql="select * from user_info where username='"+name+"'";
		 resultSet=statement.executeQuery(sql);
		 
	     if(resultSet.next()) 
	    	 return false;
	     sql="insert into user_info(username,password,role) values "+"('"+name+"','"+password+"','"+role+"')";
	     if(statement.executeUpdate(sql)>0) 
	    	 return true;
	     else 
	    	 return false;
	}
	
	public static boolean deleteUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Delete" );
		
//		if (users.containsKey(name)){
//			users.remove(name);
//			return true;
//		}else
//			return false;
		if(!connectedToDatabase) 
			throw new SQLException("Not Connected to Database.");
		 
		 statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 String sql="select * from user_info where username='"+name+"'";
		 resultSet=statement.executeQuery(sql);
		 
		 if(!resultSet.next()) 
			 return false;
		 sql="delete from user_info where username='"+name+"'";
		 if(statement.executeUpdate(sql)>0) 
			 return true;
		 else 
			 return false;

	}	
            
	public static void disconnectFromDB() {
		if ( connectedToDatabase ){
			// close Statement and Connection            
			try{
				resultSet.close();
				statement.close();
				connection.close();
//				if (Math.random()>0.5)
//					throw new SQLException( "Error in disconnecting DB" );      
			}catch ( SQLException sqlException ){                                            
			    sqlException.printStackTrace();           
			}finally{                                            
				connectedToDatabase = false;              
			}                             
		} 
   }           

	
	public static void main(String[] args) throws Exception {		
		Init();
	}
	
}

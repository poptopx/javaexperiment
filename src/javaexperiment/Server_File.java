package javaexperiment;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_File extends ServerSocket{
 
    private static final int PORT =60212;
     
    private ServerSocket server;
    private Socket client;
    private DataInputStream dis;
    private FileOutputStream fos;
     
    public Server_File()throws Exception{
        try {
            try {
                server =new ServerSocket(PORT);
                 
                while(true){
                	System.out.println("等待客户端连接---》\n");
                    client = server.accept();
                    System.out.println("已经接收了客户端连接---》\n");
                    dis =new DataInputStream(client.getInputStream());
                    //文件名和长度
                    String fileName = dis.readUTF();
                    System.out.println(fileName);
                    long fileLength = dis.readLong();
                    //fos =new FileOutputStream(new File("e:/" + fileName));
                    fos =new FileOutputStream(new File("e:\\" + fileName));
                    byte[] sendBytes =new byte[1024];
                    int transLen =0;
                    System.out.println("----开始接收文件<" + fileName +">,文件大小为<" + fileLength +">----");
                    while(true){
                        int read =0;
                        read = dis.read(sendBytes);
                        if(read == -1)
                            break;
                        transLen += read;
                        System.out.println("接收文件进度" +100 * transLen/fileLength +"%...");
                        fos.write(sendBytes,0, read);
                        fos.flush();
                    }
                    System.out.println("----接收文件<" + fileName +">成功-------");
                    client.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(dis !=null)
                    dis.close();
                if(fos !=null)
                    fos.close();
                server.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args)throws Exception {
        new Server_File();
    }
}

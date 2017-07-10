import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;  
  
public class MySocketServer {  
    public static final int PORT = 263;
      
    public static void main(String[] args) {    
        System.out.println("server running...\n");    
        MySocketServer server = new MySocketServer();    
        server.init();
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);    
            while (true) {    
                Socket client = serverSocket.accept();  
                try {
                    DataInputStream dataInputStream =  new DataInputStream(client.getInputStream());
                    byte[] bytes = new byte[8];
                    dataInputStream.read(bytes);
                    int len = new Integer(new String(bytes));
                    System.out.println("len:"+len);
                    bytes = new byte[len];
                    dataInputStream.read(bytes);
                    String content = new String(bytes);
                    System.out.println("content:"+content);
                    String respContent = "00000007success";
                    client.getOutputStream().write(respContent.getBytes());
                } catch (Exception e) {    
                    System.out.println("Server Exception: " + e.getMessage());    
                } finally {
                    if (client != null) {    
                        try {    
                            client.close();    
                        } catch (Exception e) {    
                            client = null;    
                            System.out.println("Server finally Exception:" + e.getMessage());    
                        }    
                    }    
                }   
            }    
        } catch (Exception e) {    
            System.out.println("Server Exception: " + e.getMessage());    
        }    
    }    
    
}    
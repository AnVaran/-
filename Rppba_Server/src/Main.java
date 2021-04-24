import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainController controller = new MainController();
        try{
            ServerSocket serverSocket = new ServerSocket(8020);
            while (true){
                controller.processRequest(serverSocket.accept());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("OK");
    }
}
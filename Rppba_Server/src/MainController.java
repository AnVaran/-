import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    public void processRequest(Socket clientSocket) throws IOException, SQLException, ClassNotFoundException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream())
        );
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        String request = bufferedReader.readLine();
        DBhandler db=new DBhandler();
        boolean moreRecords;
        boolean cols;
        int columns;
        CachedRowSet cs = db.returneddata(request);
        String[]arr=request.split(",");
        if (arr[0].equals("getUser")) {
            moreRecords = cs.next();
            if (moreRecords) {
                cols = false;
                columns = cs.getMetaData().getColumnCount();
                do {
                    String name = (String)cs.getObject("Name");
                    String surname = (String)((String)cs.getObject("Surname"));
                    String lastname = (String)((String)cs.getObject("LastName"));
                    String userFullName = name + "," + surname + "," + lastname;
                    bufferedWriter.write(userFullName+ ";");
                    bufferedWriter.flush();
                } while (cs.next());
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
        clientSocket.close();
    }
}
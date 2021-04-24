package sample;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.net.Socket;

public class Request {

    public Request() {
    }

    public String doRequest(String query) {
        String response = "";
        try {
            Socket clientSocket = new Socket("127.0.0.1", 8020);
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream())
            );
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            bufferedWriter.write(query + "\n");
            bufferedWriter.flush();
            CachedRowSet cs;
            response = bufferedReader.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBhandler extends Configs {
    Connection dbconnection;

    public Connection getDbconnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbconnection;
    }

    public CachedRowSet returneddata(String request) throws SQLException, ClassNotFoundException {
        String[] arr = request.split(",");
        CachedRowSet cs = new CachedRowSetImpl();
         if(arr[0].equals("getUser")){
            String Login =arr[1];
            String Password=arr[2];
            cs=getUserLogin(Login,Password);
        }
        return cs;
    }
    public CachedRowSet getUserLogin(String Login,String Password) throws SQLException, ClassNotFoundException {
        ResultSet res = null;
        String select = "SELECT * FROM " + Constants.LOGIN_TABLE + " WHERE " + Constants.LOGIN_LOGIN + " =? AND " +
                Constants.LOGIN_PASSWORD + " =?";
        PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
        preparedStatement.setString(1,Login);
        preparedStatement.setString(2, Password);
        res = preparedStatement.executeQuery();
        CachedRowSet cs = new CachedRowSetImpl();
        cs.populate(res);
        return cs;
    }
}
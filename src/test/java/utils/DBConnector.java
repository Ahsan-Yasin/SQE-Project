package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private final String url;
    private final String user;
    private final String password;

    public DBConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<String> readProducts() {
        List<String> names = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT name FROM products LIMIT 10");
            while (rs.next()) {
                names.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("DBConnector: failed to read products - " + e.getMessage());
        }
        return names;
    }
}

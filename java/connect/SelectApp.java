package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

    private Connection connect(){
        String url = "jdbc:sqlite:C://sqlite/db/new.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ResultSet SelectAll() throws SQLException{
        String sql = "SELECT * FROM Bank_Transactions WHERE "
                    + "card = 'DEBIT CARD PURCHASE FROM' AND"
                    + "date BETWEEN '2019-09-08' AND '2019-09-15'";
        /* Try with resource: A resource is an object that must be
            closed after the program is finished, try with resource
            ensures that the resource is closed at the end of
            statement
        */
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            //PreparedStatement pstmt = conn.PreparedStatement(sql);) {
            return rs;
            //psmt.SetString()
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
            //System.out.println(e.getMessage());
        }

        /*
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("date") + "\t" +
                                    rs.getDouble("amount_left"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */

    }

    public static void main(String[] args) {
        SelectApp app = new SelectApp();
        try (ResultSet rs = app.SelectAll()) {
            while (rs.next()) {
                System.out.println(rs.getString("date") + "\t" +
                                    rs.getDouble("amount_left"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}

package com.Display;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class ConnectDB extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String host = "localhost";
        String port = "5432";
        String db_name = "studies";
        String username = "postgres";
        String password = "100ee20gg";

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType =
                "<!Doctype html" +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>");
        try {
            // Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");

            // Execute SQL query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM students";
            rs = stmt.executeQuery(sql);
            out.println("<table border=1 >");
            out.println("<caption><h2>Students Management System</h2></caption>");

            out.println("<tr style='background-color:#ffffb3; color:red'>");
            out.println("<th>student_name</th>");
            out.println("<th>registration_number</th>");
            out.println("<th>course</th>");
            out.println("<th>marks</th>");
            out.println("<th>average</th>");
            out.println("</tr>");
            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String student_name = rs.getString("student_name");
                String registration_number = rs.getString("registration_number");
                String course = rs.getString("course");
                String marks = rs.getString("marks");
                String average = rs.getString("average");
                // int marks = rs.getInt("marks");

                //Display values
                out.println("<tr style='background-color:#b3ffd9;'>");
                out.println("<td>" + student_name + "</td>");
                out.println("<td>" + registration_number + "</td>");
                out.println("<td>" + course + "</td>");
                out.println("<td>" + marks + "</td>");
                out.println("<td>" + average + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</bod;=y></html>");

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }

}




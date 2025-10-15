package com.project.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;

public class CreateJobServlet extends HttpServlet
 {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String companyName = request.getParameter("companyName");
        int postedBy = Integer.parseInt(request.getParameter("postedBy"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_db","root","root");

            String sql = "INSERT INTO jobs(title,description,companyName,postedBy,postedOn) VALUES(?,?,?,?,NOW())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, companyName);
            ps.setInt(4, postedBy);

            ps.executeUpdate();
            response.getWriter().println("Job created successfully");

        } catch(Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
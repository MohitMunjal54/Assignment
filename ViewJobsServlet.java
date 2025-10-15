package com.project.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewJobsServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_db","root","root");

            String sql = "SELECT * FROM jobs";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();
            while(rs.next()) 
		{
                out.println("ID: "+rs.getInt("id")+", Title: "+rs.getString("title")+", Company: "+rs.getString("companyName")+", Description: "+rs.getString("description"));
            }

        } catch(Exception e) 
{
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
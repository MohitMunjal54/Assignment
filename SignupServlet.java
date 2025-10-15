package com.project.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String address = request.getParameter("address");
        String profileHeadline = request.getParameter("profileHeadline");

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b : hash) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_db", "root", "root");

            String sql = "INSERT INTO users(name,email,password,userType,address,profileHeadline) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setString(4, userType);
            ps.setString(5, address);
            ps.setString(6, profileHeadline);

            ps.executeUpdate();
            response.getWriter().println("Signup Successful");

        } catch(Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
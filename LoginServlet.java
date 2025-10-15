package com.project.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.security.MessageDigest;

public class LoginServlet extends HttpServlet {
    private static final String SECRET_KEY = "MySecretKey12345";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b : hash) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_db","root","root");

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, hashedPassword);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String jwt = Jwts.builder()
                        .setSubject(email)
                        .claim("userType", rs.getString("userType"))
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                        .compact();
                response.getWriter().println("Login Successful. JWT: " + jwt);
            } else {
                response.getWriter().println("Invalid credentials");
            }

        } catch(Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
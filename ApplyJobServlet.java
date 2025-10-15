package com.project.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApplyJobServlet extends HttpServlet
 {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int jobId = Integer.parseInt(request.getParameter("job_id"));
        int applicantId = Integer.parseInt(request.getParameter("applicant_id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_db","root","root");

            String sql = "INSERT INTO job_applications(jobId, applicantId, appliedOn) VALUES(?, ?, NOW())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jobId);
            ps.setInt(2, applicantId);
            ps.executeUpdate();

            response.getWriter().println("Applied successfully to Job ID: " + jobId);

        } catch(Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
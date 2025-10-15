package com.project.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.*;

@MultipartConfig(fileSizeThreshold = 1024*1024*2, // 2MB
                 maxFileSize = 1024*1024*10,      // 10MB
                 maxRequestSize = 1024*1024*50)   // 50MB


public class UploadResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("resume");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) uploadFolder.mkdir();

        filePart.write(uploadDir + File.separator + fileName);
        response.getWriter().println("Resume uploaded successfully: " + fileName);
    }
}
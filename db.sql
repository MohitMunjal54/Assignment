-- Create database
CREATE DATABASE recruitment_db;
USE recruitment_db;
------------------------------------------------------------------------------------------
-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    userType VARCHAR(20) NOT NULL, -- Admin or Applicant
    profileHeadline VARCHAR(100),
    address VARCHAR(100)
);
--------------------------------------------------------------------------------------------
-- Jobs table
CREATE TABLE IF NOT EXISTS jobs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    companyName VARCHAR(50),
    postedBy INT NOT NULL,
    postedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (postedBy) REFERENCES users(id)
);
----------------------------------------------------------------------------------------------
-- Job Applications table
CREATE TABLE IF NOT EXISTS job_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jobId INT NOT NULL,
    applicantId INT NOT NULL,
    appliedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (jobId) REFERENCES jobs(id),
    FOREIGN KEY (applicantId) REFERENCES users(id)
);
---------------------------------------------------------------------------------------------------
-- Resumes table
CREATE TABLE IF NOT EXISTS resumes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    applicantId INT NOT NULL,
    fileName VARCHAR(100) NOT NULL,
    uploadedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (applicantId) REFERENCES users(id)
);
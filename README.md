# Assignment
recruitment server


 Recruitment Management Server implemented using **Java Servlets** and **JDBC**.  


---

## **Project Features Implemented**

1. User Signup (with password hashing)
2. User Login (returns JWT token for authentication)
3. Resume Upload (PDF/DOCX, stored in `uploads/` folder)
4. Admin: Create Job
5. View Jobs
6. Apply to Job

---

## **Skipped Features Due to  time limit**
- Resume parsing via third-party API (can be added later)
- Admin view of all applicants (optional)

---

## **Database Setup**

1. Run `db.sql` in MySQL to create the required database and tables:
   - Database: `recruitment_db`
   - Tables: `users`, `jobs`, `job_applications`, `resumes`
2. Database connection in Servlets:
   - URL: `jdbc:mysql://localhost:3306/recruitment_db`
   - Username: `root`
   - Password: 

---

## **Required Libraries**

The project uses the following libraries:

- **MySQL Connector:** `mysql-connector-java.jar` (JDBC driver)  
- **JWT Libraries:** `jjwt-api.jar`, `jjwt-impl.jar`, `jjwt-jackson.jar` (for authentication)

All jars are included in the `lib/` folder. Add them to your Eclipse build path before running the project.

---

## **Project Structure**

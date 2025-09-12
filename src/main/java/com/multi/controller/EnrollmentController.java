package com.multi.controller;

import com.multi.dao.CourseDAO;
import com.multi.dao.EnrollmentDAO;
import com.multi.dao.StudentDAO;
import com.multi.dto.EnrollmentDTO;
import com.multi.dto.StudentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/enrollments","/enrollments/*"})
public class EnrollmentController extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();

        if (path == null) {
            path = "/";
        }
        switch(path){
            case "/":
                enrollmentCourse(req,resp);
                break;
            case "/cancel":
                enrollmentCancel(req,resp);
                break;

        }
    }

    private void enrollmentCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        int studentId = Integer.valueOf(req.getParameter("studentId"));
        int courseId = Integer.valueOf(req.getParameter("courseId"));

        enrollmentDTO.setStudentId(studentId);
        enrollmentDTO.setCourseId(courseId);

        enrollmentDAO.insert(enrollmentDTO);
        resp.sendRedirect(req.getContextPath() + "/students/" + studentId);
        return;
    }

    private void  enrollmentCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        enrollmentDAO.delete(studentId,courseId);
        resp.sendRedirect(req.getContextPath() + "/students/" + studentId);
        return;
    }

}

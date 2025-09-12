package com.multi.controller;

import com.multi.dao.CourseDAO;
import com.multi.dao.StudentDAO;
import com.multi.dto.CourseDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/courses","/courses/*"})
public class CourseController extends HttpServlet {
    StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getPathInfo();
        if(path.matches("/\\d+$")){
            studentsByCourseId(req,resp);

        }
    }

    private void studentsByCourseId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getPathInfo();
        int courseId = Integer.parseInt(path.split("/")[1]);
        req.setAttribute("CourseStudents", studentDAO.findStudentsByCourseId(courseId));
        req.getRequestDispatcher("/WEB-INF/views/courses/list.jsp").forward(req, resp);
        return;
    }
}

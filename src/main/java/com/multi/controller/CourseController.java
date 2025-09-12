package com.multi.controller;

import com.multi.dao.CourseDAO;
import com.multi.dao.StudentDAO;
import com.multi.dto.CourseDTO;
import com.multi.dto.StudentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/courses","/courses/*"})
public class CourseController extends HttpServlet {
    StudentDAO studentDAO = new StudentDAO();
    CourseDAO courseDAO = new CourseDAO();

    private void view(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();

        if(path==null){
            path="/";
        }
        if(path.matches("/\\d+$")){
            studentsByCourseId(req,resp);
            return;
        }
        switch (path){
            case "/":
                courseList(req,resp);
                break;
            case "/new":
                courseNewForm(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();
        switch(path){
            case "/new":
                insertCourse(req,resp);
                break;
            default:
                if(path.matches("/\\d+/edit$")){
                    updateCourse(req,resp,path);
                } else if(path.matches("/\\d+/delete$")){
                    deleteCourse(req,resp,path);
                }
        }
    }

    private void courseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");

        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) { page = 1; }
        int size;
        try {
            size = Integer.parseInt(req.getParameter("size"));
        } catch (Exception e) { size = 10; }

        int total = courseDAO.count(q);
        int totalPages = Math.max(1, (int) Math.ceil(total / (double) size));
        page = Math.min(Math.max(1, page), totalPages);
        int offset = (page - 1) * size;

        List<CourseDTO> items = courseDAO.findCourse(q, size, offset);
        req.setAttribute("page", new CourseController.Page<>(items, page, size, total, totalPages));
        req.setAttribute("q", q);
        view(req, resp, "courses/courseList");
    }
    private void studentsByCourseId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getPathInfo();
        int courseId = Integer.parseInt(path.split("/")[1]);
        req.setAttribute("CourseStudents", studentDAO.findStudentsByCourseId(courseId));
        view(req, resp, "courses/list");
        return;
    }
    private void courseNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(req, resp, "courses/courseForm");
    }

//    post

    private void insertCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String professor = req.getParameter("professor");
        int credit = Integer.parseInt(req.getParameter("credit"));
        CourseDTO  courseDTO = new CourseDTO();
        courseDTO.setCode(code);
        courseDTO.setTitle(title);
        courseDTO.setProfessor(professor);
        courseDTO.setCredit(credit);

        courseDAO.insertCourse(courseDTO);
        resp.sendRedirect("/courses");
        return;
    }

    private void updateCourse(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
        int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
        CourseDTO c = new CourseDTO();
        c.setId(id);
        c.setTitle(req.getParameter("title"));
        c.setProfessor(req.getParameter("professor"));
        c.setCredit(Integer.parseInt(req.getParameter("credit")));

        courseDAO.updateCourse(c);
        resp.sendRedirect("/courses");
        return;
    }

    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
        int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
        courseDAO.deleteCourse(id);
        resp.sendRedirect("/courses");
        return;
    }

    public static class Page<T> {
        private final java.util.List<T> items;
        private final int number;      // 현재 페이지
        private final int size;        // 페이지당 개수
        private final int total;       // 전체 개수
        private final int totalPages;  // 전체 페이지 수

        public Page(java.util.List<T> items, int number, int size, int total, int totalPages) {
            this.items = items;
            this.number = number;
            this.size = size;
            this.total = total;
            this.totalPages = totalPages;
        }

        public java.util.List<T> getItems()      { return items; }
        public int getNumber()                   { return number; }
        public int getSize()                     { return size; }
        public int getTotal()                    { return total; }
        public int getTotalPages()               { return totalPages; }
    }

}

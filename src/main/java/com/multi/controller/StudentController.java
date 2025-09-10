package com.multi.controller;

import com.multi.dao.StudentDAO;
import com.multi.dto.StudentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/students","/students/*"})
public class StudentController extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    private void view(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();

        if (path == null) {
            path = "/";
        }

        switch(path){
            case "/":
                studentList(req,resp);
                break;
            case "/new":
                studentNewForm(req,resp);
                break;
            default:
                if(path.matches("^\\d+$")){
                    studentDetail(req,resp, path);
                } else if(path.matches("^\\d+/edit$")){
                    studentEditForm(req,resp);
                } else{
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void studentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) { page = 1; }
        int size;
        try {
            size = Integer.parseInt(req.getParameter("size"));
        } catch (Exception e) { size = 10; }

        int total = studentDAO.count(q);
        int totalPages = Math.max(1, (int) Math.ceil(total / (double) size));
        page = Math.min(Math.max(1, page), totalPages);
        int offset = (page - 1) * size;

        List<StudentDTO> items = studentDAO.getAllStudents(q, size, offset);
        req.setAttribute("page", new Page<>(items, page, size, total, totalPages));
        req.setAttribute("q", q);
        view(req, resp, "student/list");
    }

    private void studentNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view(req, resp, "student/form");
    }
    private void studentEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDTO s = studentDAO.getStudentById(id);
        studentDAO.updateStudent(s);
        view(req, resp, "student/form?mode=edit");
    }
    private void studentDetail(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        var s = studentDAO.getStudentById(id);
        if(s == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        req.setAttribute("student", s);
        view(req, resp, "student/detail");
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

package com.multi.dao;

import com.multi.config.MyBatisUtil;
import com.multi.dto.StudentDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDAO {
    public List<StudentDTO> getAllStudents(String q,int size,int offset){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            var p = new java.util.HashMap<String,Object>();
            p.put("q", q);
            p.put("limit", size);
            p.put("offset", offset);
            return s.selectList("com.multi.mapper.StudentMapper.getAllStudents", p);
        }
    }
    public StudentDTO getStudentById(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectOne("com.multi.dao.StudentMapper.getStudentById", id);
        }
    }
    public int insertStudent(StudentDTO student){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.insert("com.multi.dao.StudentMapper.insertStudent", student);
        }
    }
    public int updateStudent(StudentDTO student){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.update("com.multi.dao.StudentMapper.updateStudent", student);
        }
    }
    public int deleteStudent(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.delete("com.multi.dao.StudentMapper.deleteStudent", id);
        }
    }
    public int count(String q) {
        try (SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return s.selectOne("com.multi.mapper.StudentMapper.count", q);
        }
    }
}

package com.multi.dao;

import com.multi.config.MyBatisUtil;
import com.multi.dto.CourseDTO;
import com.multi.dto.StudentDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAO {
    public List<CourseDTO> findCourse(String q, Integer size, Integer offset) {
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            Map<String, Object> params = new HashMap<>();
            params.put("q", q);
            params.put("size", size);
            params.put("offset", offset);
            return s.selectList("com.multi.mapper.CourseMapper.findAllCourse", params);
        }

    }

    public List<CourseDTO> findAllCourse() {
        try (SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return s.selectList("com.multi.mapper.CourseMapper.findAll", Collections.emptyMap());
        }
    }


    public List<CourseDTO> findByStudentId(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectList("com.multi.mapper.CourseMapper.findByStudentId", id);
        }
    }
    public int insertCourse(CourseDTO courseDTO){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.insert("com.multi.mapper.CourseMapper.insertCourse", courseDTO);
        }
    }
    public int updateCourse(CourseDTO courseDTO){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.update("com.multi.mapper.CourseMapper.updateCourse", courseDTO);
        }
    }
    public int deleteCourse(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.delete("com.multi.mapper.CourseMapper.deleteCourse", id);
        }
    }

    public CourseDTO findCourse(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectOne("com.multi.mapper.CourseMapper.findCourse", id);
        }
    }

    public int count(String q) {
        try (SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return s.selectOne("com.multi.mapper.CourseMapper.count", q);
        }
    }

}

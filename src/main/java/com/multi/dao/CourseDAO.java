package com.multi.dao;

import com.multi.config.MyBatisUtil;
import com.multi.dto.CourseDTO;
import com.multi.dto.StudentDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourseDAO {
    public List<CourseDTO> findAllCourse(){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectList("com.multi.mapper.CourseMapper.findAllCourse");
        }
    }

    public List<CourseDTO> findByStudentId(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectList("com.multi.mapper.CourseMapper.findByStudentId", id);
        }
    }

    public CourseDTO findCourse(int id){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession()){
            return s.selectOne("com.multi.mapper.CourseMapper.findCourse", id);
        }
    }


}

package com.multi.dao;

import com.multi.config.MyBatisUtil;
import com.multi.dto.EnrollmentDTO;
import org.apache.ibatis.session.SqlSession;

public class EnrollmentDAO {
    public int insert(EnrollmentDTO enrollment){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            return s.insert("com.multi.mapper.EnrollmentMapper.insert", enrollment);
        }
    }
    public int delete(int studentId,int courseId){
        try(SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            var p = new java.util.HashMap<String,Object>();
            p.put("studentId", studentId);
            p.put("courseId", courseId);
            return s.delete("com.multi.mapper.EnrollmentMapper.delete",p);
        }
    }
}

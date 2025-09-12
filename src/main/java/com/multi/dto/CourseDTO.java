package com.multi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int id;
    private String code;
    private String title;
    private String professor;
    private int credit;
    private LocalDateTime createdAt;
}

package com.multi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String studentNo;
    private String name;
    private String email;
    private String dept;
    private LocalDateTime createdAt;
}

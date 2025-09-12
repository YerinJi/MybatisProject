package com.multi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime enrolledAt;
}

package com.consultaddtraining.javaproject.springboot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseBody
public class ErrorDTO {
    private String message;
    private int code;
    private boolean success;
}

package com.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DealResponse {

    private int status;
    private String error;
    private String message;
    private DealDTO data;

}

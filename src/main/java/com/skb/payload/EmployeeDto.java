package com.skb.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    public String name;
    private String emailId;
    private String mobile;
    private Date date;
}

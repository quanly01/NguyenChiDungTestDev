package com.dung.nguyenchidung.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Customer {
    private Integer id;
    private String name;
    private String number;
    private String email;
    private Date createdAt;
}

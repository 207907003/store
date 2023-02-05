package com.xyz.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private String createdUser;
    private Date createdTime ;
    private String modifiedUser;
    private Date modifiedTime;
}

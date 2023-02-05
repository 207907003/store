package com.xyz.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Favorite extends BaseEntity {
    private Integer fid ;
    private Integer uid ;
    private Integer pid ;
    private String title;
    private Integer price;
    private String images ;


}

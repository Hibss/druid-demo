package com.czkj.multi.druid.entity.one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5589910662874718788L;
    private Integer id;

    private String mobile;
}
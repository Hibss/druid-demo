package com.czkj.druid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String mobile;

    private String password;

    private Date loginTime;

    private String ip;

    private Integer customerType;

    private Integer customerId;

    private Date createTime;

    private Integer memberId;

    private Boolean confirmInvest;

    private Date confirmTime;

    private static final long serialVersionUID = 1L;
}
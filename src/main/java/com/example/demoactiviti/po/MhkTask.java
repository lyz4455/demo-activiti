package com.example.demoactiviti.po;

import lombok.Data;

import java.util.Date;

/**
 * @author yanzhongliu
 */
@Data
public class MhkTask {

    private String id;

    private String name;

    private String processDefinitionId;

    private String processInstanceId;

    private String assignee;

    private Date createTime;

}

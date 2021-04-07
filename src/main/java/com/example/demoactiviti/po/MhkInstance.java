package com.example.demoactiviti.po;

import lombok.Data;

import java.util.Date;

/**
 * @author yanzhongliu
 */
@Data
public class MhkInstance {

    private String id;

    private String activityId;

    private Integer processDefinitionVersion;

    private String processDefinitionId;

    private String processDefinitionKey;

    private String processDefinitionName;

    private String processInstanceId;

}

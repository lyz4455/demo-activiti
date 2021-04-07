package com.example.demoactiviti.po;

import lombok.Data;

import java.util.Date;

/**
 * @author yanzhongliu
 */
@Data
public class MhkLog {

    private String id;

    private String activityId;

    private String activityName;

    private String activityType;

    private String processDefinitionId;

    private String processInstanceId;

    private String executionId;

    private String taskId;

    private String calledProcessInstanceId;

    private String assignee;

    private Date startTime;

    private Date endTime;

    private Long durationInMillis;

}

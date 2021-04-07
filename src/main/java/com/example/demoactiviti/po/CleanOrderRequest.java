package com.example.demoactiviti.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanzhongliu
 * @email yanzhongliu@ctrip.com
 * @date 2020/5/20 14:07
 */
@Data
public class CleanOrderRequest {

    @ApiModelProperty("项目key")
    private String processDefinitionKey;

    @ApiModelProperty("实例id")
    private String instanceId;

    @ApiModelProperty("业务id")
    private String id;

    @ApiModelProperty("清扫人员")
    private String staffName;

    @ApiModelProperty("清扫人员编码")
    private String staffCode;

    @ApiModelProperty("验收人员")
    private String reviewName;

    @ApiModelProperty("验收人员编码")
    private String reviewCode;

    @ApiModelProperty("操作人员")
    private String operatorName;

    @ApiModelProperty("操作人员编码")
    private String operatorCode;

    @ApiModelProperty("是否通过")
    private String flag;
}

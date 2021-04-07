package com.example.demoactiviti.service.impl;

import com.example.demoactiviti.mapper.MhkWorkOrderMapper;
import com.example.demoactiviti.po.CleanOrderRequest;
import com.example.demoactiviti.po.MhkWorkOrder;
import com.example.demoactiviti.service.MobHouseKeepingService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MobHouseKeepingServiceImpl implements MobHouseKeepingService {

    @Autowired
    MhkWorkOrderMapper mhkWorkOrderMapper;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskservice;

    @Override
    public boolean addMhkWorkOrder(CleanOrderRequest request) {
        String processDefinitionKey = request.getProcessDefinitionKey();
        MhkWorkOrder record = new MhkWorkOrder();
        record.setPmsGroupCd("LC");
        record.setPmsHotelCd("LC01");
        record.setProcessUserCd(request.getStaffCode());

        record.setProcessUserName(request.getStaffName());
        record.setLastReviewUserCd(request.getReviewCode());
        record.setLastReviewUserName(request.getReviewName());
        record.setTimestamp(DateTime.now().toDate());
        record.setDispatchDate(DateTime.now().toDate());
        mhkWorkOrderMapper.insert(record);
        String businessKey = String.valueOf(record.getId());

        Map<String, Object> assigneeMap = new HashMap<>(4);
        assigneeMap.put("worker", request.getStaffName());
        assigneeMap.put("leader", request.getReviewName());
        // 启动流程实例，同时还要设置流程定义的assignee的值
        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, assigneeMap);

        List<ProcessInstance> list = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey).processInstanceBusinessKey(String.valueOf(record.getId()))
                .list();
        String instanceId = list.get(0).getProcessInstanceId();
        record.setInstanceId(instanceId);
        mhkWorkOrderMapper.updateByPrimaryKeySelective(record);
        return true;
    }

}

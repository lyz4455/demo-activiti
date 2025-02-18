package com.example.demoactiviti.controller;

import com.example.demoactiviti.po.CleanOrderRequest;
import com.example.demoactiviti.po.MhkInstance;
import com.example.demoactiviti.po.MhkLog;
import com.example.demoactiviti.po.MhkTask;
import com.example.demoactiviti.service.MobHouseKeepingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhongliu
 * @email yanzhongliu@ctrip.com
 * @date 2021-03-31 11:34
 */
@Api("移动工程部")
@RestController
@RequestMapping("/mhk")
public class MobHouseKeepingController {

    @Autowired
    MobHouseKeepingService mobHouseKeepingService;

    @Autowired
    TaskService taskService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RuntimeService runtimeService;

    private static final String PROCESS_DEFINITION_KEY = "Process_CleanOrder";

    @ApiOperation(value = "新建工单", notes = "新建工单")
    @RequestMapping(value = "/addMhkWorkOrder", method = RequestMethod.POST)
    public boolean addMhkWorkOrder(@RequestBody CleanOrderRequest request) {
        request.setProcessDefinitionKey(PROCESS_DEFINITION_KEY);
        return mobHouseKeepingService.addMhkWorkOrder(request);
    }

    @ApiOperation(value = "根据业务Key获取示例", notes = "根据业务Key获取示例")
    @RequestMapping(value = "/queryProcessInstance", method = RequestMethod.POST)
    public List<MhkInstance> queryProcessInstance(@RequestBody CleanOrderRequest request) {
        String businessKey = request.getId();
        List<ProcessInstance> instanceList = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY).processInstanceBusinessKey(businessKey)
                .list();
        List<MhkInstance> instances = new ArrayList<>();
        if (!CollectionUtils.isEmpty(instanceList)) {
            for (ProcessInstance item : instanceList) {
                MhkInstance instance = new MhkInstance();
                instance.setId(item.getId());
                instance.setProcessDefinitionId(item.getProcessDefinitionId());
                instance.setProcessDefinitionKey(item.getProcessDefinitionKey());
                instance.setProcessDefinitionName(item.getProcessDefinitionName());
                instance.setProcessInstanceId(item.getProcessInstanceId());
                instance.setProcessDefinitionVersion(item.getProcessDefinitionVersion());
                instance.setActivityId(item.getActivityId());
                instances.add(instance);
            }
        }
        return instances;
    }

    @ApiOperation(value = "查询任务根据业务key", notes = "查询任务根据业务key")
    @RequestMapping(value = "/queryTask", method = RequestMethod.POST)
    public List<MhkTask> queryTask(@RequestBody CleanOrderRequest request) {
        String businessKey = request.getId();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY).processInstanceBusinessKey(businessKey).list();
        List<MhkTask> tasks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(taskList)) {
            for (Task item : taskList) {
                MhkTask task = new MhkTask();
                task.setId(item.getId());
                task.setName(item.getName());
                task.setAssignee(item.getAssignee());
                task.setProcessDefinitionId(item.getProcessDefinitionId());
                task.setProcessInstanceId(item.getProcessInstanceId());
                task.setCreateTime(item.getCreateTime());
                tasks.add(task);
            }
        }
        return tasks;
    }

    @ApiOperation(value = "查询历史任务根据实例id", notes = "查询历史任务根据实例id")
    @RequestMapping(value = "/findHistoryInfo", method = RequestMethod.POST)
    public List<MhkLog> findHistoryInfo(@RequestBody CleanOrderRequest request) {
        HistoricActivityInstanceQuery instanceQuery =
                historyService.createHistoricActivityInstanceQuery();
        instanceQuery.processInstanceId(request.getInstanceId());
        // 增加排序操作,orderByHistoricActivityInstanceStartTime 根据开始时间排序 asc 升序
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        // 查询所有内容
        List<HistoricActivityInstance> activityInstanceList = instanceQuery.list();
        List<MhkLog> logs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(activityInstanceList)) {
            for (HistoricActivityInstance item : activityInstanceList) {
                MhkLog log = new MhkLog();
                log.setId(item.getId());
                log.setActivityId(item.getActivityId());
                log.setActivityName(item.getActivityName());
                log.setActivityType(item.getActivityType());
                log.setProcessDefinitionId(item.getProcessDefinitionId());
                log.setProcessInstanceId(item.getProcessInstanceId());
                log.setExecutionId(item.getExecutionId());
                log.setCalledProcessInstanceId(item.getCalledProcessInstanceId());
                log.setAssignee(item.getAssignee());
                log.setStartTime(item.getStartTime());
                log.setEndTime(item.getEndTime());
                log.setDurationInMillis(item.getDurationInMillis());
                logs.add(log);
            }
        }
        return logs;
    }

    @ApiOperation(value = "审核任务", notes = "审核任务")
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    public void completeTask(@RequestBody CleanOrderRequest request) {
        // 根据流程key 和 任务的负责人 查询任务
        // 返回一个任务对象
        String businessKey = String.valueOf(request.getId());
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .taskAssignee(request.getOperatorName()).processInstanceBusinessKey(businessKey)
                .singleResult();
        // 完成任务,参数：任务id
        taskService.complete(task.getId());
    }

    @ApiOperation(value = "审核任务带条件", notes = "审核任务带条件")
    @RequestMapping(value = "/completeTaskChoice", method = RequestMethod.POST)
    public void completeTaskChoice(@RequestBody CleanOrderRequest request) {
        // 根据流程key 和 任务的负责人 查询任务
        // 返回一个任务对象
        String businessKey = String.valueOf(request.getId());
        String operatorName = request.getOperatorName();
        Map<String, Object> assigneeMap = new HashMap<>();
        assigneeMap.put("orderStatus", request.getFlag());
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .taskAssignee(operatorName).processInstanceBusinessKey(businessKey)
                .singleResult();
        // 完成任务,参数：任务id
        taskService.complete(task.getId(), assigneeMap);
    }

    @ApiOperation(value = "待办任务列表", notes = "待办任务列表")
    @RequestMapping(value = "/todoListSomeOne", method = RequestMethod.POST)
    @ResponseBody
    public List<MhkTask> todoListSomeOne(@RequestBody CleanOrderRequest request) {
        // 根据流程key 和 任务的负责人 查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .taskAssignee(request.getOperatorName()).list();
        List<MhkTask> tasks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(taskList)) {
            for (Task item : taskList) {
                MhkTask task = new MhkTask();
                task.setId(item.getId());
                task.setName(item.getName());
                task.setAssignee(item.getAssignee());
                task.setProcessDefinitionId(item.getProcessDefinitionId());
                task.setProcessInstanceId(item.getProcessInstanceId());
                task.setCreateTime(item.getCreateTime());
                tasks.add(task);
            }
        }
        return tasks;
    }
}

package com.example.demoactiviti.common;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author yanzhongliu
 * @email yanzhongliu@ctrip.com
 * @date 2021-04-14 14:35
 */
public class MyExecutionListener implements ExecutionListener, TaskListener {
    /**
     * 流程监听(适用连线或者网关)
     * 监听开始结束
     * ExecutionListener类的实现
     * @param execution
     */
    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
        // ActivitiEventType.PROCESS_STARTED
        if ("start".equals(eventName)) {
            // 流程开始
            System.out.println("start......");
        } else if ("end".equals(eventName)) {
            // 流程结束
            System.out.println("end......");
        } else if ("take".equals(eventName)) {
            System.out.println("take......");
        }
    }

    /**
     * 任务节点的监听
     * TaskListener类实现
     * 说明: usertask2节点配置了处理人所以触发assignment事件监听，
     * 当任务运转到usertask2的时候触发了create事件。 这里我们也可以得出一个结论：assignment事件比create先执行。
     * 任务完成的时候，触发complete事件，因为任务完成之后，要从ACT_RU_TASK中删除这条记录，所以触发delete事件
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        // ActivitiEventType.PROCESS_STARTED
        if ("create".endsWith(eventName)) {
            System.out.println("create=========");
        } else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        } else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        } else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
        // delegateTask 可以操作activiti引擎的一些东西
    }
}

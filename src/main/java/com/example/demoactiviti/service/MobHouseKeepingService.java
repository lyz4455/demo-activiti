package com.example.demoactiviti.service;


import com.example.demoactiviti.po.CleanOrderRequest;

/**
 * @author yanzhongliu
 */
public interface MobHouseKeepingService {

    /**
     * 开始
     * @param request
     * @return
     */
    boolean addMhkWorkOrder(CleanOrderRequest request);
}

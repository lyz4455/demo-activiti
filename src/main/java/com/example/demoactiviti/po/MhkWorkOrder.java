package com.example.demoactiviti.po;

import lombok.Data;

import java.util.Date;

@Data
public class MhkWorkOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.id
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.pms_group_cd
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String pmsGroupCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.pms_hotel_cd
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String pmsHotelCd;


    /**
     * 实例id
     */
    private String instanceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.dispatch_date
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Date dispatchDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.process_user_cd
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String processUserCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.process_user_name
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String processUserName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.last_review_user_cd
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String lastReviewUserCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.last_review_user_name
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String lastReviewUserName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.work_order_type
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Integer workOrderType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.work_order_type_detail
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Integer workOrderTypeDetail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.room_no
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String roomNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.room_type_code
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String roomTypeCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.room_type_desc
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String roomTypeDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.building_area_code
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String buildingAreaCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.building_area_desc
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String buildingAreaDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.floor
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private String floor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.create_time
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order.modify_time
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mhk_work_order._timestamp
     *
     * @mbg.generated Tue Apr 06 14:41:32 CST 2021
     */
    private Date timestamp;

}
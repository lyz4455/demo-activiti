# demo-activiti
activiti和spring结合
mhk_work_order.sql 为建表语句

使用流程：
1.运用Camunda Modeler做流程图，要放到process目录下面。
2.启动项目后，会自动将流程部署到数据库中。
3.可以进行走流程。

一些总结：
activiti 的一大精髓在于将业务和流程分离开，来简化业务开发。但是业务和流程又是怎么联系起来那？业务主键id放入到框架流程中当做businessKey，
通过这个key将业务和流程关联起来。同时加业务需要通过加监听器来实现，并且耦合性也不大。

https://downloads.camunda.cloud/release/camunda-modeler/
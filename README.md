# shulaibaoSpringBoot

分布式抢单解决方案：
1、mysql锁实现
    锁：新增数据，主键或唯一索引
    解锁： 删除数据
   性能：阿里云，低性能，也依赖于配置
2、redis锁实现
    锁：setnx(set if not exists) key value
    解锁：del key
    
    
    问题一：redis宕机，业务瘫痪，导致死锁
    解决方案：设置key的过期时间，过期后，其他服务就可以加锁了
    
    setnx key value
    expire key 10
    问题：执行第一行完宕机，改成setnx key value expire key 10,保证原子操作，或者使用lura


    问题2：过期时间问题
    过期时间小于业务执行时间，线程2执行的时候，key已经过期
    看门狗：定时延期过期时间
    
    
    问题3：redis单节点故障
    一主二从三哨兵
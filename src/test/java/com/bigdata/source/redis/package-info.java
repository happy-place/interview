package com.bigdata.source.redis;

/**
 * Redis: 基于内存KV存储、worker计算线程是单线程、IO多路并发访问(epoll)是多线程;
 *
 *
 *
 *
 * 不要小看一个redis 任何一家公司的招聘信息都包含一段redis的需求。 不要小看一个redis 你能在互联网搜索到的很多文章都讲错了，面试会有很多坑。
 * 不要小看一个redis 搞懂它是你通向分布式、微服务的第一扇大门。
 * 1、redis的前世今生
 * 2、redis为什么是key，value的，为什么不是支持SQL的？
 * 3、redis的NIO&线程模型
 * 4、redis是多线程还是单线程？
 *  执行计算的worker是串行的(单线程)，访问数据的IO线程是多线程
 * 5、redis 5.x的安装部署方式
 * 6、redis的五大Value类型分析
 *  string
 *      set
 *      get
 *      strlen
 *      setbit
 *  list
 *      栈
 *      队列
 *  hash
 *      详情页数据聚合
 *  set
 *      去重
 *  zset
 *      排序集合
 * 1、细节见真知：计算向数据移动、而非数据向计算移动
 * 2、linux系统的支持：fork、copy on write
 * 3、redis的持久化：RDB、AOF、RDB&AOF混合使用
 * 4、AKF划分原则、CAP定理
 * 5、redis的集群：主从、哨兵机制
 */
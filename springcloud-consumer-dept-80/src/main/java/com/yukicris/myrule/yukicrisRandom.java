package com.yukicris.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class yukicrisRandom extends AbstractLoadBalancerRule {
        public yukicrisRandom() {
        }

        // 实现每个机器访问5次,然后换下一个机器提供服务(3个机器)

        //逻辑 total = 0,默认0,if = 5 ,指向下一个服务
        // index = 0,默认0,if total = 5 ,index=1

        private int total = 0;   // 服务调用次数
        private int index = 0;   // 当前是谁在提供服务

        //@SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
        public Server choose(ILoadBalancer lb, Object key) {
            if (lb == null) {
                return null;
            } else {
                Server server = null;

                while(server == null) {
                    if (Thread.interrupted()) {
                        return null;
                    }

                    List<Server> upList = lb.getReachableServers(); // 获得活着的服务
                    List<Server> allList = lb.getAllServers();   // 获取全部服务
                    int serverCount = allList.size();
                    if (serverCount == 0) {   //服务没了,直接返回
                        return null;
                    }

                   // int index = this.chooseRandomInt(serverCount);  // 生成区间随机数(全部服务的随机数)
                   // server = (Server)upList.get(index);  //从活着的服务中获取其中一个服务

                    //***************************************
                    // 自定义逻辑
                    if (total<5) {
                        server = upList.get(index);
                        total++;
                    }else {
                        total = 0;
                        index ++ ;
                        if (index >= upList.size()){
                            // 如果当前数量大于活着的服务数量(机器数量),这里是大于3次就归0
                            index = 0;
                        }
                        server  = upList.get(index);  //从活着的服务中,获取指定的服务进行操作
                    }




                    //***************************************
                    if (server == null) {
                        Thread.yield();
                    } else {
                        if (server.isAlive()) {
                            return server;
                        }

                        server = null;
                        Thread.yield();
                    }
                }

                return server;
            }
        }

        protected int chooseRandomInt(int serverCount) {
            return ThreadLocalRandom.current().nextInt(serverCount);
        }

        public Server choose(Object key) {
            return this.choose(this.getLoadBalancer(), key);
        }

        public void initWithNiwsConfig(IClientConfig clientConfig) {
        }
}

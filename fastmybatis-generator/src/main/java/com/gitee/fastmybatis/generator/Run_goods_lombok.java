package com.gitee.fastmybatis.generator;

import com.gitee.fastmybatis.generator.client.Client;

/**
 * 生成的实体类是lombok风格
 */
public class Run_goods_lombok {

    public static void main(String[] args) {
        Client client = new Client();
        client.genAll("cfg/goods_lombok.properties");
    }

}

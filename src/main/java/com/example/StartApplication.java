package com.example;


import org.wildfly.swarm.Swarm;

public class StartApplication {
    public static void main(String[] args) throws Exception {
        new Swarm().start().deploy();
    }
}

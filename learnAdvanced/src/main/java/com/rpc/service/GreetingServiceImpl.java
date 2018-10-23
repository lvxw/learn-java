package com.rpc.service;

public class GreetingServiceImpl implements IGreetingService {

    @Override
    public String sayHello(String str) {
        return str+"~~~";
    }
}

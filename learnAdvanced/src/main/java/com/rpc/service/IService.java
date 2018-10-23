package com.rpc.service;

import java.io.IOException;

public interface IService {

    void register(Class service,Class serviceImpl);

    void start() throws IOException;

    void stop();
}

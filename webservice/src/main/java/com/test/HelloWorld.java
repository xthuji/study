package com.test;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    public String sayHello(String name);
}

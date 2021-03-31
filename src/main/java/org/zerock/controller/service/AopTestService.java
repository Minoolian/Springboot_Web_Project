package org.zerock.controller.service;

import org.springframework.stereotype.Service;

@Service
public class AopTestService {

    public Integer doAdd(String str1, String Str2) throws Exception{
        return Integer.parseInt(str1) + Integer.parseInt(Str2);
    }
}

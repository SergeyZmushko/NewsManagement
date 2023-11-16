package com.mjc.school.service.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements ApplicationRunner {
    private final DbLoader dbLoader;

    public DemoData(DbLoader dbLoader){
        this.dbLoader = dbLoader;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbLoader.saveData(1000);
    }
}

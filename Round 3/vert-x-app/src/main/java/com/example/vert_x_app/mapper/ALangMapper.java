package com.example.vert_x_app.mapper;

import com.example.vert_x_app.entity.ALangAddRegistry;
import com.example.vert_x_app.entity.ALangMVData;

public class ALangMapper {

    public ALangMVData mapToALangMVData(String register, int currentValue) {

        ALangMVData mVData = new ALangMVData();
        mVData.setRegister(register);
        mVData.setCurrentValue(currentValue);
        return mVData;
    }

    public ALangAddRegistry mapToALangAddRegistry(String addStatement) {

        ALangAddRegistry addRegistry = new ALangAddRegistry();
        addRegistry.setAddStatement(addStatement);
        return addRegistry;
    }
}

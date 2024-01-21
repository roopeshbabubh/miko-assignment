package com.roopesh.springredisapp.mapper;

import com.roopesh.springredisapp.entity.ALangAddRegistry;
import com.roopesh.springredisapp.entity.ALangMVData;
import org.springframework.stereotype.Component;

@Component
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

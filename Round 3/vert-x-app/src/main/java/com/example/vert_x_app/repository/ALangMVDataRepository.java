package com.example.vert_x_app.repository;

import com.example.vert_x_app.entity.ALangMVData;

import java.util.ArrayList;
import java.util.HashMap;

public class ALangMVDataRepository {

  HashMap<String, ALangMVData> mVDataMapList = new HashMap<>();
  public ALangMVData save(ALangMVData prevMVData) {
    mVDataMapList.put(prevMVData.getRegister(), prevMVData);
    return prevMVData;
  }

  public Object findAll() {
      return new ArrayList<>(mVDataMapList.values());
  }

  public ALangMVData findByRegister(String register) {
    return mVDataMapList.get(register);
  }
}

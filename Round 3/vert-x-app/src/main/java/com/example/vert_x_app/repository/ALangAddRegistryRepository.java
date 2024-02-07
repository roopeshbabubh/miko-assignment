package com.example.vert_x_app.repository;

import com.example.vert_x_app.entity.ALangAddRegistry;

import java.util.ArrayList;

public class ALangAddRegistryRepository {

  ArrayList<ALangAddRegistry> aLangAddRegistryList = new ArrayList<>();
  public void save(ALangAddRegistry aLangAddRegistry) {
    aLangAddRegistryList.add(aLangAddRegistry);
  }

  public Object findAll() {
    return aLangAddRegistryList;
  }
}

package com.example.vert_x_app.entity;


import java.io.Serializable;

//@RedisHash("alang_mv_data")
public class ALangMVData implements Serializable {

    String mVId;
    String register;
    int currentValue;
    int previousValue;

  public ALangMVData() {
  }

  public ALangMVData(String mVId, String register, int currentValue, int previousValue) {
    this.mVId = mVId;
    this.register = register;
    this.currentValue = currentValue;
    this.previousValue = previousValue;
  }

  public String getmVId() {
    return mVId;
  }

  public void setmVId(String mVId) {
    this.mVId = mVId;
  }

  public String getRegister() {
    return register;
  }

  public void setRegister(String register) {
    this.register = register;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(int currentValue) {
    this.currentValue = currentValue;
  }

  public int getPreviousValue() {
    return previousValue;
  }

  public void setPreviousValue(int previousValue) {
    this.previousValue = previousValue;
  }

  @Override
    public String toString() {
        return "\n{\n" +
                "    mVId='" + mVId + "',\n" +
                "    register='" + register + "',\n" +
                "    currentValue=" + currentValue + ",\n" +
                "    previousValue=" + previousValue + "\n" +
                "}";
    }

}

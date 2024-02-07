package com.example.vert_x_app.entity;


import java.io.Serializable;

//@RedisHash("alang_add_registry")
public class ALangAddRegistry implements Serializable {

    String addId;
    String addStatement;

  public ALangAddRegistry() {
  }

  public ALangAddRegistry(String addId, String addStatement) {
    this.addId = addId;
    this.addStatement = addStatement;
  }

  public String getAddId() {
    return addId;
  }

  public void setAddId(String addId) {
    this.addId = addId;
  }

  public String getAddStatement() {
    return addStatement;
  }

  public void setAddStatement(String addStatement) {
    this.addStatement = addStatement;
  }

  @Override
    public String toString() {
        return "\n{\n" +
                "    addId: '" + addId + "',\n" +
                "    addStatement: '" + addStatement + "'\n" +
                "}";
    }

}

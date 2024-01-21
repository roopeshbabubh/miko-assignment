package com.roopesh.springredisapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash("alang_mv_data")
public class ALangMVData implements Serializable {

    @Id
    String mVId;
    String register;
    int currentValue;
    int previousValue;

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
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
@RedisHash("alang_add_registry")
public class ALangAddRegistry implements Serializable {

    @Id
    String addId;
    String addStatement;

    @Override
    public String toString() {
        return "\n{\n" +
                "    addId: '" + addId + "',\n" +
                "    addStatement: '" + addStatement + "'\n" +
                "}";
    }

}

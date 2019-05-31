package com.cui.redis.rediscache.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: cui
 * @Date: 2019/1/16 09:41
 * @Description:
 */
@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String name;
    private long money;

    public User() {
    }

    public User(String name, long money) {
        this.name = name;
        this.money = money;
    }
    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', money=%d}", id, name, money);
    }
}

package com.bytrees.web.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goods {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public Timestamp getCreateTime() {
    	return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
    	this.createTime = createTime;
    }
    public Timestamp getUpdateTime() {
    	return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
    	this.updateTime = updateTime;
    }
}

package com.mymc.hosweb.domain;


public class Emp {
    private Integer eid;
    private String ename;
    private String job;

    public Emp(Integer eid, String ename, String job) {
        this.eid = eid;
        this.ename = ename;
        this.job = job;
    }

    public Emp() {
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

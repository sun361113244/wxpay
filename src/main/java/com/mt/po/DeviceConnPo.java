package com.mt.po;

import java.util.Date;

public class DeviceConnPo {
    private Integer id;

    private String deviceId;

    private String ip;

    private Integer port;

    private Date createTime;

    @Override
    public String toString() {
        return "DeviceConnPo{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
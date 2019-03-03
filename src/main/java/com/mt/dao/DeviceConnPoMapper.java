package com.mt.dao;

import com.mt.po.DeviceConnPo;
import com.mt.po.DeviceConnPoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceConnPoMapper {
    int countByExample(DeviceConnPoExample example);

    int deleteByExample(DeviceConnPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceConnPo record);

    int insertSelective(DeviceConnPo record);

    List<DeviceConnPo> selectByExample(DeviceConnPoExample example);

    DeviceConnPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceConnPo record, @Param("example") DeviceConnPoExample example);

    int updateByExample(@Param("record") DeviceConnPo record, @Param("example") DeviceConnPoExample example);

    int updateByPrimaryKeySelective(DeviceConnPo record);

    int updateByPrimaryKey(DeviceConnPo record);
}
package com.cxs.test.mapper;

import com.cxs.dao.IDao;
import com.cxs.test.domain.User;
import com.cxs.test.domain.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends IDao<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

//    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
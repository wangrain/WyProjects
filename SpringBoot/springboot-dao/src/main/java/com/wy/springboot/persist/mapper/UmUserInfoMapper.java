package com.wy.springboot.persist.mapper;

import com.wy.springboot.persist.po.UmUserInfo;
import com.wy.springboot.persist.po.UmUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface UmUserInfoMapper {
    @SelectProvider(type=UmUserInfoSqlProvider.class, method="countByExample")
    long countByExample(UmUserInfoExample example);

    @DeleteProvider(type=UmUserInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmUserInfoExample example);

    @Insert({
        "insert into um_user_info (USER_GUID, USER_NAME, ",
        "USER_MOBILE, USER_EMAIL, ",
        "REGISTER_TIME)",
        "values (#{userGuid,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{userMobile,jdbcType=VARCHAR}, #{userEmail,jdbcType=VARCHAR}, ",
        "#{registerTime,jdbcType=TIMESTAMP})"
    })
    int insert(UmUserInfo record);

    @InsertProvider(type=UmUserInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UmUserInfo record);

    @SelectProvider(type=UmUserInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="USER_GUID", property="userGuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_MOBILE", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_EMAIL", property="userEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_TIME", property="registerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmUserInfo> selectByExampleWithRowbounds(UmUserInfoExample example, RowBounds rowBounds);

    @SelectProvider(type=UmUserInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="USER_GUID", property="userGuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_MOBILE", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_EMAIL", property="userEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_TIME", property="registerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmUserInfo> selectByExample(UmUserInfoExample example);

    @UpdateProvider(type=UmUserInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmUserInfo record, @Param("example") UmUserInfoExample example);

    @UpdateProvider(type=UmUserInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmUserInfo record, @Param("example") UmUserInfoExample example);
}
package com.wy.springboot.persist.mapper;

import com.wy.springboot.persist.po.UmUserInfo;
import com.wy.springboot.persist.po.UmUserInfoExample.Criteria;
import com.wy.springboot.persist.po.UmUserInfoExample.Criterion;
import com.wy.springboot.persist.po.UmUserInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class UmUserInfoSqlProvider {

    public String countByExample(UmUserInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("um_user_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(UmUserInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("um_user_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(UmUserInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("um_user_info");
        
        if (record.getUserGuid() != null) {
            sql.VALUES("USER_GUID", "#{userGuid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("USER_NAME", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getUserMobile() != null) {
            sql.VALUES("USER_MOBILE", "#{userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUserEmail() != null) {
            sql.VALUES("USER_EMAIL", "#{userEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.VALUES("REGISTER_TIME", "#{registerTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(UmUserInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("USER_GUID");
        } else {
            sql.SELECT("USER_GUID");
        }
        sql.SELECT("USER_NAME");
        sql.SELECT("USER_MOBILE");
        sql.SELECT("USER_EMAIL");
        sql.SELECT("REGISTER_TIME");
        sql.FROM("um_user_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        UmUserInfo record = (UmUserInfo) parameter.get("record");
        UmUserInfoExample example = (UmUserInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("um_user_info");
        
        if (record.getUserGuid() != null) {
            sql.SET("USER_GUID = #{record.userGuid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("USER_NAME = #{record.userName,jdbcType=VARCHAR}");
        }
        
        if (record.getUserMobile() != null) {
            sql.SET("USER_MOBILE = #{record.userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUserEmail() != null) {
            sql.SET("USER_EMAIL = #{record.userEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.SET("REGISTER_TIME = #{record.registerTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("um_user_info");
        
        sql.SET("USER_GUID = #{record.userGuid,jdbcType=VARCHAR}");
        sql.SET("USER_NAME = #{record.userName,jdbcType=VARCHAR}");
        sql.SET("USER_MOBILE = #{record.userMobile,jdbcType=VARCHAR}");
        sql.SET("USER_EMAIL = #{record.userEmail,jdbcType=VARCHAR}");
        sql.SET("REGISTER_TIME = #{record.registerTime,jdbcType=TIMESTAMP}");
        
        UmUserInfoExample example = (UmUserInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    protected void applyWhere(SQL sql, UmUserInfoExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
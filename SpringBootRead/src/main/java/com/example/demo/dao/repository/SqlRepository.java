package com.example.demo.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class SqlRepository {
    @Autowired
    private NamedParameterJdbcTemplate repository;

    /**
     * 单行单列查询
     *
     * @param sql         the SQL query to execute
     * @param paramMap    map of parameters to bind to the query
     * @param elementType the type that the result object is expected to match
     * @return the result object of the required type
     */
    public <T> T querySingle(String sql, Map<String, Object> paramMap, Class<T> elementType) {
        return repository.queryForObject(sql, paramMap, elementType);
    }

    /**
     * 实体查询
     *
     * @param sql         the SQL query to execute
     * @param paramMap    map of parameters to bind to the query
     * @param elementType the type that the result object is expected to match
     * @return the result object of the required type
     */
    public <T> T queryEntity(String sql, Map<String, Object> paramMap, Class<T> elementType) {
        return repository.query(sql, paramMap, new ResultSetExtractor<T>() {
            @Override
            public T extractData(ResultSet rs) throws SQLException, DataAccessException {
                T t = null;
                while (rs.next()) {
                    if (rs.isFirst()) {
                        t = getObject(rs, elementType);
                    }
                }
                return t;
            }
        });
    }

    /**
     * 列表查询
     *
     * @param sql         the SQL query to execute
     * @param paramMap    map of parameters to bind to the query
     * @param elementType the type that the result object is expected to match
     * @return the result list of the required type
     */
    public <T> List<T> queryList(String sql, Map<String, Object> paramMap, Class<T> elementType) {
        return repository.query(sql, paramMap, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getObject(rs, elementType);
            }
        });
    }

    /**
     * 分页列表查询
     *
     * @param sql         the SQL query to execute
     * @param paramMap    map of parameters to bind to the query
     * @param pageNumber  the page to be returned.
     * @param pageSize    the number of items to be returned.
     * @param elementType the type that the result object is expected to match
     * @return the result list of the required type
     */
    public <T> Page<T> queryPagingList(String sql, Map<String, Object> paramMap, int pageNumber, int pageSize, Class<T> elementType) {
        String sqlCount = "select count(1) from (" + sql + ") countTable";
        long totalElements = repository.queryForObject(sqlCount, paramMap, Long.class);
        int totalPages = pageSize == 0 ? 0 : (int) Math.ceil((double) totalElements / (double) pageSize);

        int begin = (pageNumber - 1) * pageSize;
        String sqlLimit = " limit " + begin + ", " + pageSize;
        List<T> list = repository.query(sql + sqlLimit, paramMap, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getObject(rs, elementType);
            }
        });

        return new Page<T>() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public int getTotalPages() {
                return totalPages;
            }

            @Override
            public long getTotalElements() {
                return totalElements;
            }

            @Override
            public List<T> getContent() {
                return list;
            }
        };
    }

    /**
     * 分页列表查询
     *
     * @param sql           the SQL query to execute
     * @param paramMap      map of parameters to bind to the query
     * @param pageNumber    the page to be returned.
     * @param pageSize      the number of items to be returned.
     * @param totalElements the total amount of elements.
     * @param elementType   the type that the result object is expected to match
     * @return the result list of the required type
     */
    public <T> Page<T> queryPagingList(String sql, Map<String, Object> paramMap, int pageNumber, int pageSize, long totalElements, Class<T> elementType) {
        int totalPages = pageSize == 0 ? 0 : (int) Math.ceil((double) totalElements / (double) pageSize);
        int begin = (pageNumber - 1) * pageSize;
        String sqlLimit = " limit " + begin + ", " + pageSize;
        List<T> list = repository.query(sql + sqlLimit, paramMap, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getObject(rs, elementType);
            }
        });

        return new Page<T>() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public int getTotalPages() {
                return totalPages;
            }

            @Override
            public long getTotalElements() {
                return totalElements;
            }

            @Override
            public List<T> getContent() {
                return list;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private <T> T getObject(ResultSet rs, Class<T> elementType) {
        try {
            Field[] fields = elementType.getDeclaredFields();
            Object obj = elementType.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                setFieldValue(obj, field, rs);
            }
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 设置实体字段值
    private void setFieldValue(Object obj, Field field, ResultSet rs) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        // 获取属性的名字
        String fieldName = field.getName();
        // 将属性的首字符大写，构造get，set方法
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        // 获取属性的类型
        Type fieldType = field.getType();
        if (fieldType == String.class) {
            String value = rs.getString(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{String.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == BigDecimal.class) {
            BigDecimal value = rs.getBigDecimal(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{BigDecimal.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == Integer.class || fieldType == int.class) {
            int value = rs.getInt(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{int.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == Long.class || fieldType == long.class) {
            long value = rs.getLong(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{long.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == Float.class || fieldType == float.class) {
            float value = rs.getFloat(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{float.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == Double.class || fieldType == double.class) {
            double value = rs.getDouble(fieldName);
            // 调用setter方法设置属性值
            Method mSet = obj.getClass().getMethod(methodName, new Class[]{double.class});
            mSet.invoke(obj, new Object[]{value});
        } else if (fieldType == LocalTime.class) {
            Time time = rs.getTime(fieldName);
            if (time != null) {
                LocalTime value = rs.getTime(fieldName).toLocalTime();
                // 调用setter方法设置属性值
                Method mSet = obj.getClass().getMethod(methodName, new Class[]{LocalTime.class});
                mSet.invoke(obj, new Object[]{value});
            }
        }
    }
}

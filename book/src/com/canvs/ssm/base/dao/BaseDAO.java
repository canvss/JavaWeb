package com.canvs.ssm.base.dao;


import com.canvs.ssm.exception.BaseDAOException;
import com.canvs.ssm.utils.JDBCUtils;
import com.canvs.ssm.utils.PackageScanner;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> {
    static String packageName;
    private Class<T> clazz = null;
    private Connection conn;

    {
        Type superclass = this.getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) superclass;
        Type[] arguments = type.getActualTypeArguments();
        clazz = (Class<T>) arguments[0];
    }

    {
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("数据库连接创建失败");
        }
    }

    public int executeUpdate(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            int len = ps.executeUpdate();
            if (len > 0){
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int autoIncrementKey = generatedKeys.getInt(1);
                    return autoIncrementKey;
                }
            }
            return len;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseDAOException("BaseDAO出错");
        } finally {
            JDBCUtils.closeResource(null, ps,generatedKeys);
        }
    }

    private static boolean isPojoType(String typeName) {
        try {
            List<String> packageNameList = PackageScanner.getAllClassesInPackage(packageName);
            return packageNameList.contains(typeName);
        } catch (IOException e) {
            throw new BaseDAOException("packageName出错....");
        }
    }

    // 通用的查询操作，用于返回数据表中的一条记录
    public T getBean(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取每一个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射给t对象指定的columnName熟悉赋值为columValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    String typeName = field.getType().getName();
                    if (isPojoType(typeName)) {
                        Class<?> typeNameClass = Class.forName(typeName);
                        Constructor<?> constructor = typeNameClass.getDeclaredConstructor(java.lang.Integer.class);
                        columnValue = constructor.newInstance(columnValue);
                    }
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseDAOException("BaseDAO出错");
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    //通用的查询操作，用于返回数据表中的多条记录构成的集合
    public List<T> getBeanList(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                //处理结果姐一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取每一个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射给t对象指定的columnName熟悉赋值为columValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    String typeName = field.getType().getName();
                    if (isPojoType(typeName)) {
                        Class<?> typeNameClass = Class.forName(typeName);
                        Constructor<?> constructor = typeNameClass.getDeclaredConstructor(java.lang.Integer.class);
                        columnValue = constructor.newInstance(columnValue);
                    }
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseDAOException("BaseDAO出错");
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
    }

    //用于查询特殊值的通用的方法
    public <E> E getValue(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseDAOException("BaseDAO出错");
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}

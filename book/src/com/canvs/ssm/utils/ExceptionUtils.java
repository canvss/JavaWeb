package com.canvs.ssm.utils;

import com.canvs.ssm.exception.BaseDAOException;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);
    public static void SQLException(SQLException e,String msg){
        logger.error("SQL异常发生：", e);
        throw new BaseDAOException(msg);
    }

    public static void Exception(Exception e,String msg){
        logger.error("Exception异常发生：", e);
        throw new BaseDAOException(msg);
    }

    public static void IOException(Exception e,String msg){
        logger.error("IOException异常发生：", e);
        throw new BaseDAOException(msg);
    }
}

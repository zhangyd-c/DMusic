package me.zhyd.music.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.validation.support.BindingAwareModelMap;

import java.lang.reflect.Method;

/**
 * AOP相关的工具
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/6/29 11:59
 * @since 1.0
 */
public class AspectUtil {

    /**
     * 获取以类路径为前缀的键
     *
     * @param point 当前切面执行的方法
     */
    public static String getKeyOfClassPrefix(ProceedingJoinPoint point, String prefix) {
        String keyPrefix = "";
        if (!StringUtils.isEmpty(prefix)) {
            keyPrefix += prefix;
        }
        keyPrefix += getClassName(point);
        return keyPrefix;
    }

    /**
     * 获取当前切面执行的方法所在的class
     *
     * @param point 当前切面执行的方法
     */
    public static String getClassName(ProceedingJoinPoint point) {
        return point.getTarget().getClass().getName().replaceAll("\\.", "_");
    }

    /**
     * 获取当前切面执行的方法的方法名
     *
     * @param point 当前切面执行的方法
     * @throws NoSuchMethodException
     */
    public static Method getMethod(ProceedingJoinPoint point) throws NoSuchMethodException {
        Signature sig = point.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        Object target = point.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

    /**
     * 获取切面缓存的key
     *
     * @param point  当前切面执行的方法
     * @param extra  额外的参数 （非必选）
     * @param prefix key前缀 （非必选）
     * @throws NoSuchMethodException
     */
    public static String getKey(ProceedingJoinPoint point, String extra, String prefix) throws NoSuchMethodException {
        Method currentMethod = AspectUtil.getMethod(point);
        String methodName = currentMethod.getName();
        StringBuilder key = new StringBuilder();
        key.append(getKeyOfClassPrefix(point, prefix));
        key.append("_");
        key.append(methodName);
        key.append(getMethodParamsKey(point.getArgs()));
        key.append(null == extra ? "" : extra);
        return key.toString();
    }

    /**
     * 获取方法参数组成的key
     *
     * @param params 参数数组
     */
    public static String getMethodParamsKey(Object... params) {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        StringBuilder key = new StringBuilder("(");
        for (Object obj : params) {
            if (obj.getClass().equals(BindingAwareModelMap.class)) {
                continue;
            }
            key.append(JSON.toJSONString(obj).replaceAll("\"", "'")).append(",");
        }
        key.deleteCharAt(key.length() - 1);
        key.append(")");
        return key.toString();
    }
}

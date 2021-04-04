package com.demo.common.threadlocal;

/**
 * <p>
 * 通过本地线程变量 保存日志记录实例
 * </p>
 *
 * @author liuhoujie
 * @date 2020/10/28
 */
@SuppressWarnings({"unused"})
public class RecordLocal {
    private static final ThreadLocal<Object> local = new ThreadLocal<>();

    /**
     * 获取 本地 日志记录实例
     *
     * @return RecorderInfoBO 日志记录实例
     */
    public static Object get() {
        return local.get();
    }

    /**
     * <p>
     * 最终 获取 本地 日志记录实例 <br>
     * 和get() 区别是 当确定最后一次获取变量后, 清除本地线程变量 <br>
     * 预防在某些场景下的线程短时间不会结束, 对象不会被回收从而引发OOM
     * </p>
     *
     * @return RecorderInfoBO 日志记录实例
     */
    public static Object finalGet() {
        Object obj = local.get();
        local.remove();
        return obj;
    }

    /**
     * 设置本地线程的日志记录实例
     *
     * @param obj 日志记录实例
     */
    public static void set(Object obj) {
        local.set(obj);
    }
}
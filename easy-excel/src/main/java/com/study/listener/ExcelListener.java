package com.study.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @date: 2021/11/4 10:28
 * @author: LiHaoHan
 * @program: com.study.listener
 */
public class ExcelListener<T> implements ReadListener<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<T> list = new ArrayList<>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private Object object;


    public ExcelListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        // demoDAO = new DemoDAO();
    }


    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param service
     */
    public ExcelListener(IService<T> service) {
        this.object = service;
    }


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param t    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(T t, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSONObject.toJSONString(t));
        list.add(t);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM


    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库

        saveData();
        // 存储完成清理 list
        list.clear();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        boolean saveBatch=false;
        try {
            Class<?> aClass = object.getClass();
            IService<T> o = (IService<T>) aClass.newInstance();
            saveBatch = o.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        if (!saveBatch) {
            LOGGER.error("存储数据库失败");
        }
        LOGGER.info("存储数据库成功！");
    }
}

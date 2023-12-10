package com.study.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.study.entity.ExcelEntityTactics;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * excel处理类
 *
 * @author Lihaohan
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<ExcelEntityTactics> {

    /**
     * 缓存的数据
     */
    private final List<ExcelEntityTactics> cachedDataList;


    public ExcelListener() {
        this.cachedDataList = new LinkedList<>();
    }

    public List<ExcelEntityTactics> getCachedDataList() {
        CopyOnWriteArrayList
        Collections.synchronizedList(list);
        return cachedDataList;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelEntityTactics data, AnalysisContext context) {
        // 需要提前处理数据的业务
        data.accept(data);
        cachedDataList.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("所有数据解析完成！");
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }
}
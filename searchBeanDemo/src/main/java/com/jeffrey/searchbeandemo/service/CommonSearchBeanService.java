package com.jeffrey.searchbeandemo.service;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapUtils;
import com.jeffrey.searchbeandemo.common.AllResultsPaginationProcessor;
import com.jeffrey.searchbeandemo.common.PaginationInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/1/5
 * @time 17:00
 * @week 星期五
 * @description 公共的searchBean服务类
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class CommonSearchBeanService {

    private final BeanSearcher beanSearcher;

    public <T> Flux<List<T>> searchListAllResult(HttpServletRequest request, Class<T> clazz) {
        AtomicInteger loadedSize = new AtomicInteger(0);
        AtomicLong page = new AtomicLong(0);
        return Flux.create(sink -> {
            try {
                do {
                    var d = beanSearcher.search(clazz, MapUtils.flatBuilder(request.getParameterMap()).page(page.get(), 2000).build());
                    loadedSize.addAndGet(d.getDataList().size());
                    sink.next(d.getDataList());
                    if (Objects.equals(d.getTotalCount(), loadedSize.get()) || loadedSize.get() > d.getTotalCount().intValue()
                            || d.getDataList().isEmpty()) {
                        break;
                    }
                    page.getAndIncrement();
                } while (true);
            } catch (Exception e) {
                sink.error(e);
            } finally {
                sink.complete();
            }
        });
    }

    public <T> List<T> searchList(HttpServletRequest request, Class<T> clazz) {
        return beanSearcher.searchList(clazz, MapUtils.flat(request.getParameterMap()));
    }

    public <T> Page<T> searchAndWarpToJpaPage(HttpServletRequest request, Class<T> clazz) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        SearchResult<T> search = beanSearcher.search(clazz, MapUtils.flat(request.getParameterMap()));
        Number totalCount = search.getTotalCount();
        if (totalCount.intValue() > 0) {
            List<T> dataList = search.getDataList();
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setPage(page);
            paginationInfo.setSize(size);
            return new AllResultsPaginationProcessor<>(dataList, paginationInfo).generatePageLimit(totalCount.intValue());
        } else {
            return new PageImpl<T>(new ArrayList<>(), PageRequest.of(page, size), 0);
        }
    }


    public <T> Page<T> searchAndWarpToJpaPage(HttpServletRequest request, Class<T> clazz, Map map) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        SearchResult<T> search = beanSearcher.search(clazz, MapUtils.flat(request.getParameterMap()));
        Number totalCount = search.getTotalCount();
        if (totalCount.intValue() > 0) {
            List<T> dataList = search.getDataList();
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setPage(page);
            paginationInfo.setSize(size);
            return new AllResultsPaginationProcessor<>(dataList, paginationInfo).generatePageLimit(totalCount.intValue());
        } else {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(page, size), 0);
        }
    }
}

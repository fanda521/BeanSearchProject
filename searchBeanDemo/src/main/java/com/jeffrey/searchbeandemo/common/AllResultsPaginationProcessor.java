package com.jeffrey.searchbeandemo.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;

public class AllResultsPaginationProcessor<T>  {
    private final PaginationInfo paginationInfo;
    private final List<T> allResults;

    public AllResultsPaginationProcessor(List<T> allResults, PaginationInfo paginationInfo) {
        this.paginationInfo = paginationInfo;
        this.allResults = allResults;
    }

    public Page<T> generatePage() {
        try {
            return new PageImpl<>(allResults.subList(getBeginIndex(), getEndIndex()), PageRequest.of(getPageNo(), getPageSize()), getTotalAmount());
        } catch (IndexOutOfBoundsException e) {
            throw  e;
        }
    }

    /**
     * 原生的limit 方式
     */
    public Page<T> generatePageLimit(int totalElements) {
        try {
            return new PageImpl<>(allResults, PageRequest.of(getPageNo(), getPageSize()), totalElements);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * 原生的limit 方式,当前页的数据
     */
    public Page<T> generatePageLimit(int totalElements, boolean refresh) {
        try {
            if (Objects.isNull(refresh)) refresh = false;
            if (refresh) {
                List<T> pageContent = getPageContent();
                return new PageImpl<>(pageContent, PageRequest.of(getPageNo(), getPageSize()), totalElements);
            }
            return new PageImpl<>(allResults, PageRequest.of(getPageNo(), getPageSize()), totalElements);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }


    public List<T> getPageContent() {
        return allResults.subList(getBeginIndex(), getEndIndex());
    }

    public int getTotalAmount() {
        return allResults.size();
    }

    private int getPageNo() {
        int page = paginationInfo.getPage();
        return page;
    }

    private int getPageSize() {
        int size = paginationInfo.getSize();
        return size;
    }

    private int getBeginIndex() {
//        return getPageNo() * getPageSize();
        return Math.min((getPageSize()) * (getTotalAmount()/getPageSize()),getPageNo() * getPageSize());
    }

    private int getEndIndex() {
        return Math.min(getTotalAmount(), getBeginIndex() + getPageSize());
    }
}

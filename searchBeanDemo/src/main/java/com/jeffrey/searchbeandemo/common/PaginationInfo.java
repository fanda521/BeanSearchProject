package com.jeffrey.searchbeandemo.common;

import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class PaginationInfo {
    private @Min(
            value = 0L,
            message = "{PaginationInfo.page.min}"
    ) int page;
    private @Min(
            value = 1L,
            message = "{PaginationInfo.size.min}"
    ) int size;
    private String sortBy;
    private Boolean descending;
    private int totalCount;

    public PaginationInfo(@Min(
            value = 0L,
            message = "{PaginationInfo.page.min}"
    ) int page, @Min(
            value = 1L,
            message = "{PaginationInfo.size.min}"
    ) int size) {
        this.page = page;
        this.size = size;
    }

    public Pageable ofPage() {
        return StringUtils.hasLength(this.sortBy) && !ObjectUtils.isEmpty(this.descending) ? PageRequest.of(this.page, this.size, Sort.by(this.descending ? Direction.DESC : Direction.ASC, new String[]{this.sortBy})) : PageRequest.of(this.page, this.size);
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public Boolean getDescending() {
        return this.descending;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public void setSortBy(final String sortBy) {
        this.sortBy = sortBy;
    }

    public void setDescending(final Boolean descending) {
        this.descending = descending;
    }

    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PaginationInfo)) {
            return false;
        } else {
            PaginationInfo other = (PaginationInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPage() != other.getPage()) {
                return false;
            } else if (this.getSize() != other.getSize()) {
                return false;
            } else if (this.getTotalCount() != other.getTotalCount()) {
                return false;
            } else {
                Object this$descending = this.getDescending();
                Object other$descending = other.getDescending();
                if (this$descending == null) {
                    if (other$descending != null) {
                        return false;
                    }
                } else if (!this$descending.equals(other$descending)) {
                    return false;
                }

                Object this$sortBy = this.getSortBy();
                Object other$sortBy = other.getSortBy();
                if (this$sortBy == null) {
                    if (other$sortBy != null) {
                        return false;
                    }
                } else if (!this$sortBy.equals(other$sortBy)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaginationInfo;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getPage();
        result = result * 59 + this.getSize();
        result = result * 59 + this.getTotalCount();
        Object $descending = this.getDescending();
        result = result * 59 + ($descending == null ? 43 : $descending.hashCode());
        Object $sortBy = this.getSortBy();
        result = result * 59 + ($sortBy == null ? 43 : $sortBy.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getPage();
        return "PaginationInfo(page=" + var10000 + ", size=" + this.getSize() + ", sortBy=" + this.getSortBy() + ", descending=" + this.getDescending() + ", totalCount=" + this.getTotalCount() + ")";
    }

    public PaginationInfo(final int page, final int size, final String sortBy, final Boolean descending, final int totalCount) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.descending = descending;
        this.totalCount = totalCount;
    }

    public PaginationInfo() {
    }
}


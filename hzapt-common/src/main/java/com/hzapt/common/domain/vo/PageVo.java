package com.hzapt.common.domain.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PageUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ApiModel("PageVo")
@Data
@Accessors(chain = true)
public class PageVo<T> implements Serializable {

    public final static long PAGE_NUM = 1;

    public final static long PAGE_SIZE = 10;

    @ApiModelProperty(value = "当前页码", required = true)
    private long pageNum;

    @ApiModelProperty(value = "每页条数", required = true)
    private long pageSize;

    @ApiModelProperty(value = "数据列表", required = true)
    private List<T> dataList;

    @ApiModelProperty(value = "当前页数量", required = true)
    private long size;

    @ApiModelProperty(value = "总记条数", required = true)
    private long total;

    @ApiModelProperty(value = "起始记录数", required = true)
    private long start;

    @ApiModelProperty(value = "总页数", required = true)
    private long pages;

    @ApiModelProperty(value = "是否有下一页", required = true)
    private boolean hasNext;

    @ApiModelProperty(value = "是否有上一页", required = true)
    private boolean hasPrevious;

    /**
     * 构造方法
     */
    public PageVo() {
    }

    /**
     * 构造方法
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录条数
     */
    public PageVo(long pageNum, long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.start = (this.pageNum - 1) * this.pageSize;
        this.total = 0;
        this.pages = getPages(pageSize, total);
        this.hasNext = pageNum < pages;
        this.hasPrevious = pageNum > 1;
    }

    /**
     * 构造方法
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录条数
     * @param total    总记录数
     * @param dataList 分页数据
     */
    public PageVo(long pageNum, long pageSize, long total, List<T> dataList) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.start = (this.pageNum - 1) * this.pageSize;
        this.total = total;
        this.pages = getPages(pageSize, total);
        this.hasNext = pageNum < pages;
        this.hasPrevious = pageNum > 1;
        this.dataList = dataList;
    }

    /**
     * 构造方法 只能配合PageHelper插件查出来的List数据进行分页
     *
     * @param dataList 分页数据
     */
    public PageVo(List<T> dataList) {
        PageInfo<T> pageInfo = new PageInfo<>(dataList);
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.start = (this.pageNum - 1) * this.pageSize;
        this.total = (int) pageInfo.getTotal();
        this.pages = getPages(pageSize, total);
        this.hasNext = pageNum < pages;
        this.hasPrevious = pageNum > 1;
        this.dataList = dataList;
    }

    private long getPages(long pageSize, long total) {
        return PageUtil.totalPage((int)total, (int)pageSize);
    }

    public long getSize() {
        return getDataList().size();
    }

    public List<T> getDataList() {
        return dataList != null ? dataList : CollUtil.toList();
    }

}

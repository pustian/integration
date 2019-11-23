package com.wotung.integration.gym.web.vo;

import com.wotung.integration.gym.web.vo.entity.RespEntity;

import java.util.List;

public class QueryRespEntity extends Response implements RespEntity {

    private static final long serialVersionUID = 1L;

    private String isSuccess;


    private long totalCount;

    private long current;



    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }


    private List queries;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List getQueries() {
        return queries;
    }

    public void setQueries(List queries) {
        this.queries = queries;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "QueryRespEntity{" +
                "respHeader=" + respHeader +
                ",respBody=" + respBody +
                ",isSuccess='" + isSuccess +
                ",totalCount='" + totalCount +
                ",queries='" + queries.toArray().toString() +
                '}';
    }

}

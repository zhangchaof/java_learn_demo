package com.zhang.excel;

/**
 * @ClassName WhereColumn
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/28 18:37
 **/

public class WhereColumn {
    public String getWhereCond() {
        return whereCond;
    }

    public void setWhereCond(String whereCond) {
        this.whereCond = whereCond;
    }

    private String whereCond;

    public WhereColumn() {
    }

    public WhereColumn(String whereCond, String updateOne, String updateTwo) {
        this.whereCond = whereCond;
        this.updateOne = updateOne;
        this.updateTwo = updateTwo;
    }

    public void setUpdateOne(String updateOne) {
        this.updateOne = updateOne;
    }

    public void setUpdateTwo(String updateTwo) {
        this.updateTwo = updateTwo;
    }

    public String getUpdateOne() {
        return updateOne;
    }

    public String getUpdateTwo() {
        return updateTwo;
    }

    private String updateOne;
    private String updateTwo;




}

package com.opus_bd.stockmanagement.Model.GrayFabric;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceivedGrayFabric {

    @SerializedName("receiveNo")
    @Expose
    private String receiveNo;
    @SerializedName("receiveDate")
    @Expose
    private String receiveDate;
    @SerializedName("inspectionDate")
    @Expose
    private String inspectionDate;
    @SerializedName("receiveBy")
    @Expose
    private String receiveBy;
    @SerializedName("monthName")
    @Expose
    private Object monthName;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("remarks")
    @Expose
    private Object remarks;
    @SerializedName("isFinal")
    @Expose
    private Object isFinal;
    @SerializedName("totalRoll")
    @Expose
    private Double totalRoll;
    @SerializedName("totalQTY")
    @Expose
    private Double totalQTY;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Object isDelete;
    @SerializedName("createdAt")
    @Expose
    private Object createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy;
    }

    public Object getMonthName() {
        return monthName;
    }

    public void setMonthName(Object monthName) {
        this.monthName = monthName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public Object getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Object isFinal) {
        this.isFinal = isFinal;
    }

    public Double getTotalRoll() {
        return totalRoll;
    }

    public void setTotalRoll(Double totalRoll) {
        this.totalRoll = totalRoll;
    }

    public Double getTotalQTY() {
        return totalQTY;
    }

    public void setTotalQTY(Double totalQTY) {
        this.totalQTY = totalQTY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = isDelete;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }
}

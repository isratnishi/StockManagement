
package com.opus_bd.stockmanagement.Model.GrayFabric.RackOut;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rackout {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("receiveNo")
    @Expose
    private Object receiveNo;
    @SerializedName("autoNo")
    @Expose
    private Object autoNo;
    @SerializedName("receiveDate")
    @Expose
    private Object receiveDate;
    @SerializedName("inspectionDate")
    @Expose
    private Object inspectionDate;
    @SerializedName("receiveBy")
    @Expose
    private Object receiveBy;
    @SerializedName("monthName")
    @Expose
    private Object monthName;
    @SerializedName("statusId")
    @Expose
    private Object statusId;
    @SerializedName("remarks")
    @Expose
    private Object remarks;
    @SerializedName("isFinal")
    @Expose
    private Object isFinal;
    @SerializedName("grayFebricsMasters")
    @Expose
    private Object grayFebricsMasters;
    @SerializedName("reveivedGrayFabrics")
    @Expose
    private Object reveivedGrayFabrics;
    @SerializedName("grayFebricsDetails")
    @Expose
    private Object grayFebricsDetails;
    @SerializedName("grayFebricsStorageMasters")
    @Expose
    private Object grayFebricsStorageMasters;
    @SerializedName("grayStorageMastersForBooking")
    @Expose
    private Object grayStorageMastersForBooking;
    @SerializedName("grayFabricRollViewModels")
    @Expose
    private Object grayFabricRollViewModels;
    @SerializedName("grayFabricRackingViewModels")
    @Expose
    private Object grayFabricRackingViewModels;
    @SerializedName("grayFebricsDetailViewModels")
    @Expose
    private Object grayFebricsDetailViewModels;
    @SerializedName("items")
    @Expose
    private Object items;
    @SerializedName("grayFabricIssueMasters")
    @Expose
    private List<GrayFabricIssueMaster> grayFabricIssueMasters = null;
    @SerializedName("grayFabricIssueSuccessMasters")
    @Expose
    private Object grayFabricIssueSuccessMasters;
    @SerializedName("grayFebricsMaster")
    @Expose
    private Object grayFebricsMaster;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(Object receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Object getAutoNo() {
        return autoNo;
    }

    public void setAutoNo(Object autoNo) {
        this.autoNo = autoNo;
    }

    public Object getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Object receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Object getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Object inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Object getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(Object receiveBy) {
        this.receiveBy = receiveBy;
    }

    public Object getMonthName() {
        return monthName;
    }

    public void setMonthName(Object monthName) {
        this.monthName = monthName;
    }

    public Object getStatusId() {
        return statusId;
    }

    public void setStatusId(Object statusId) {
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

    public Object getGrayFebricsMasters() {
        return grayFebricsMasters;
    }

    public void setGrayFebricsMasters(Object grayFebricsMasters) {
        this.grayFebricsMasters = grayFebricsMasters;
    }

    public Object getReveivedGrayFabrics() {
        return reveivedGrayFabrics;
    }

    public void setReveivedGrayFabrics(Object reveivedGrayFabrics) {
        this.reveivedGrayFabrics = reveivedGrayFabrics;
    }

    public Object getGrayFebricsDetails() {
        return grayFebricsDetails;
    }

    public void setGrayFebricsDetails(Object grayFebricsDetails) {
        this.grayFebricsDetails = grayFebricsDetails;
    }

    public Object getGrayFebricsStorageMasters() {
        return grayFebricsStorageMasters;
    }

    public void setGrayFebricsStorageMasters(Object grayFebricsStorageMasters) {
        this.grayFebricsStorageMasters = grayFebricsStorageMasters;
    }

    public Object getGrayStorageMastersForBooking() {
        return grayStorageMastersForBooking;
    }

    public void setGrayStorageMastersForBooking(Object grayStorageMastersForBooking) {
        this.grayStorageMastersForBooking = grayStorageMastersForBooking;
    }

    public Object getGrayFabricRollViewModels() {
        return grayFabricRollViewModels;
    }

    public void setGrayFabricRollViewModels(Object grayFabricRollViewModels) {
        this.grayFabricRollViewModels = grayFabricRollViewModels;
    }

    public Object getGrayFabricRackingViewModels() {
        return grayFabricRackingViewModels;
    }

    public void setGrayFabricRackingViewModels(Object grayFabricRackingViewModels) {
        this.grayFabricRackingViewModels = grayFabricRackingViewModels;
    }

    public Object getGrayFebricsDetailViewModels() {
        return grayFebricsDetailViewModels;
    }

    public void setGrayFebricsDetailViewModels(Object grayFebricsDetailViewModels) {
        this.grayFebricsDetailViewModels = grayFebricsDetailViewModels;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public List<GrayFabricIssueMaster> getGrayFabricIssueMasters() {
        return grayFabricIssueMasters;
    }

    public void setGrayFabricIssueMasters(List<GrayFabricIssueMaster> grayFabricIssueMasters) {
        this.grayFabricIssueMasters = grayFabricIssueMasters;
    }

    public Object getGrayFabricIssueSuccessMasters() {
        return grayFabricIssueSuccessMasters;
    }

    public void setGrayFabricIssueSuccessMasters(Object grayFabricIssueSuccessMasters) {
        this.grayFabricIssueSuccessMasters = grayFabricIssueSuccessMasters;
    }

    public Object getGrayFebricsMaster() {
        return grayFebricsMaster;
    }

    public void setGrayFebricsMaster(Object grayFebricsMaster) {
        this.grayFebricsMaster = grayFebricsMaster;
    }

}

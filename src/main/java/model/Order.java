package model;

import model.common.Entity;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-13 09:28
 * @Description:
 */
public class Order extends Entity {
    private String serialNumber;
    private String userId;
    private String consignee;
    private String consigneeAddress;
    private String phone;
    private Double amount;
    private String state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + getId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", deleteTime=" + getDeleteTime() +
                ", isDel='" + getIsDel() + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                ", state='" + state + '\'' +
                '}';
    }
}

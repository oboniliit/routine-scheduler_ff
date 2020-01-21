/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Farhin
 */
public class ShortRoutine {

    private final StringProperty uuid;
    private final StringProperty datetime;
    private final IntegerProperty order;

    public ShortRoutine(String uuid, String datetime, Integer order) {
        this.uuid = new SimpleStringProperty(uuid);
        this.datetime = new SimpleStringProperty(datetime);
        this.order = new SimpleIntegerProperty(order);
    }

    public String getUuid() {
        return uuid.get();
    }

    public String getDatetime() {
        return datetime.get();
    }

    public Integer getOrder() {
        return order.get();
    }

    public void setUuid(String value) {
        uuid.set(value);
    }

    public void setDatetime(String value) {
        datetime.set(value);
    }

    public void setOrder(Integer value) {
        order.set(value);
    }

    public StringProperty uuidProperty() {
        return uuid;
    }

    public StringProperty datetimeProperty() {
        return datetime;
    }

    public IntegerProperty orderProperty() {
        return order;
    }
}

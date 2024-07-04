/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jamil
 */
@ManagedBean(name = "dtEditView")
@ViewScoped
public class DtEditView implements Serializable {
    private List<Car> cars1;
    
    @PostConstruct
    public void init() {
        cars1 = createCar();
        
    }

    private List<Car> createCar() {
        if (cars1 != null && !cars1.isEmpty()) {
            this.setCars1(null);
        }
        List<Car> list = new ArrayList<>();
        for (int i = 0; i<10; i++) {
        list.add(new Car(String.valueOf(i), "Brand"+i, "2000", "Silver", "10000"));
        }
        return list;
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public void setCars1(List<Car> cars1) {
        this.cars1 = cars1;
    }
    public void onRowEdit(RowEditEvent event) {
        Car car = (Car)event.getObject();
        System.out.println(car.getId());
        //FacesMessage msg = new FacesMessage("Car Edited",car.getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        //this.setCars1(createCar());
    }
     
    public void onRowCancel(RowEditEvent event) {
        Car car = (Car)event.getObject();
        FacesMessage msg = new FacesMessage("Car canceled",car.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}

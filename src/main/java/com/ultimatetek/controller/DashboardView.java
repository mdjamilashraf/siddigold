/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.DateUtils;
import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.model.DashboardData;
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.services.DashboardService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Data;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jamil
 */
@Component
@ManagedBean
@ViewScoped
@Data
public class DashboardView implements Serializable {

    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");
    private String userCode = (String) JSFUtils.getFromSession("userCode");
    private DashboardModel model;
    private BarChartModel barModel;
    private DashboardData data;
    private List<OrderDetailsVO> todayOrdersDue;
    private List<OrderDetailsVO> topCustOrdrsByWeight;

    @Autowired
    private DashboardService dashboardService;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("CustomerOrder");
        column2.addWidget("ConfirmedOrder");
        column3.addWidget("OrderDeliverd");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        if (userGrp == 1) {
            this.data = dashboardService.generateDashboardData();
            this.todayDueOrder(null);//all user
            this.fetchTopCustOrdersByWeight();
        } else if (userGrp == 2) {
            this.data = dashboardService.generateWrkshpDashboardData(userCode);
            this.todayDueOrder(userCode);
            //this.fetchTopCustOrdersByWeight();
        } else {
            this.data = new DashboardData();
        }
        this.createBarModel();

    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex()
                + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
                "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
                "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Confirm");
        boys.set("Jan", 452);
        boys.set("Feb", 100);
        boys.set("Mar", 0);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Close");
        girls.set("Jan", 277);
        girls.set("Feb", 60);
        girls.set("Mar", 0);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Months");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Orders");
        yAxis.setMin(0);
        yAxis.setMax(500);
    }

    private void todayDueOrder(String userCode) {
        //OrderDetailsVO ordrDtlLst = new OrderDetailsVO();
        List<OrderDetailsVO> ordrDtlLst = dashboardService.getTodayOrderDue(userCode);
        this.setTodayOrdersDue(ordrDtlLst);
    }

    public void todayDueOrder() {
        if (userGrp == 1) {
            //this.data = dashboardService.generateDashboardData();
            this.todayDueOrder(null);//all user
            // this.fetchTopCustOrdersByWeight();
        } else if (userGrp == 2) {
            ///this.data = dashboardService.generateWrkshpDashboardData(userCode);
            this.todayDueOrder(this.userCode);
            //this.fetchTopCustOrdersByWeight();
        }
    }

    private void fetchTopCustOrdersByWeight() {
        Date startDate = DateUtils.getMonthDate("START");
        List<OrderDetailsVO> ordrDtlLst = dashboardService.getTopCustomersByWeight(startDate);
        this.setTopCustOrdrsByWeight(ordrDtlLst);
    }

}

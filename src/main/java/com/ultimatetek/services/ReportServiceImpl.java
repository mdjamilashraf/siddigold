/*
 * Copyright 2023 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ultimatetek.services;

import com.ultimatetek.model.OrderDetailsVO;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.margin;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author jamil
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Override
    public JasperPrint printOrderReport(HttpServletRequest request, HttpServletResponse response) throws JRException {
        HttpSession session = request.getSession(false);
        String rprtType = request.getParameter("typ");
        JasperPrint print = null;
        List<OrderDetailsVO> collection = (List<OrderDetailsVO>) session.getAttribute("OrderDtls");
        String[] listOfColumns = (String[]) session.getAttribute("ReportColumn");
        if (rprtType.equalsIgnoreCase("rprt")) {
//            jsprPath = ProjectConst.ROOT_DIR + File.separator + "OrderMgmnt" + File.separator
//                    + "reportDynRow.jasper";

            InputStream jasperRprtStream = getClass().getResourceAsStream("/reportDynRow.jasper");

            List<OrderDetailsVO> dynamicList = new ArrayList<>();
            for (OrderDetailsVO ordrDtl : collection) {
                if (ArrayUtils.contains(listOfColumns, "1")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Order No", ordrDtl.getOrderNo()));
                }
                if (ArrayUtils.contains(listOfColumns, "2")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "SL No", ordrDtl.getSrlNo()));
                }
                if (ArrayUtils.contains(listOfColumns, "3")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Item Name", (ordrDtl.getItemCode() + "-" + ordrDtl.getItemName())));
                }
                if (ArrayUtils.contains(listOfColumns, "4")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Weight", ordrDtl.getWeight()));
                }
                if (ArrayUtils.contains(listOfColumns, "5")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Size", ordrDtl.getItemSize()));
                }
                if (ArrayUtils.contains(listOfColumns, "6")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Qty", ordrDtl.getQty()));
                }
                if (ArrayUtils.contains(listOfColumns, "7")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "%", ordrDtl.getMeltPer()));
                }
                if (ArrayUtils.contains(listOfColumns, "8")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Stamp", ordrDtl.getStamp()));
                }
                if (ArrayUtils.contains(listOfColumns, "9")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Hook", ordrDtl.getHook()));
                }
                if (ArrayUtils.contains(listOfColumns, "10")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "DesignSample", ordrDtl.getDesignSample()));
                }
                if (ArrayUtils.contains(listOfColumns, "11")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Size Sample", ordrDtl.getSizeSample()));
                }
                if (ArrayUtils.contains(listOfColumns, "12")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Remarks", ordrDtl.getRemark()));
                }
                if (ArrayUtils.contains(listOfColumns, "13")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Due Date", ordrDtl.getDueDate()));
                }
                if (ArrayUtils.contains(listOfColumns, "14")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Customer", ordrDtl.getCustName()));
                }
                if (ArrayUtils.contains(listOfColumns, "15")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Cust Ref No.", ordrDtl.getRefNo()));
                }
                if (ArrayUtils.contains(listOfColumns, "16")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Type", ordrDtl.getType()));
                }
                if (ArrayUtils.contains(listOfColumns, "17")) {
                    dynamicList.add(new OrderDetailsVO(ordrDtl.getOrderDtlNo(), "Worker Name", ordrDtl.getWrkrName()));
                }
            }
//            File jasperReport = new File(demo);
            Map<String, Object> parameters = new HashMap<>();
            print = JasperFillManager.fillReport(jasperRprtStream, parameters, new JRBeanCollectionDataSource(dynamicList));

            return print;
        } else {
            //Style Declaration For List Report
            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder centre = stl.style(boldCenteredStyle).setFontSize(10);
            StyleBuilder columnTitleStyleForHeader = stl.style(centre).setBorder(stl.pen1Point());
            StyleBuilder columnTitleStyleForData = stl.style().setFontSize(10).setBorder(stl.pen1Point()).setHorizontalAlignment(HorizontalAlignment.CENTER);
            TextColumnBuilder<String> workerNm = col.column("wrkrName", "wrkrName", DataTypes.stringType());
            ColumnGroupBuilder columnGroup = grp.group(workerNm);

            List<String> wrkrName = new ArrayList<>();
            for (OrderDetailsVO ordr : collection) {
                if (ordr.getWrkrName() != null) {
                    wrkrName.add(ordr.getWrkrName());
                } else {
                    wrkrName.add("");
                }
            }
            List<String> sortedList = wrkrName.stream().sorted().distinct().collect(Collectors.toList());
//            Collections.sort(sortedList, Collections.reverseOrder());//for reverse order 
            //DataSource For List Report
            List<OrderDetailsVO> ordrDtls = new ArrayList<>();

            for (String sort : sortedList) {
                for (OrderDetailsVO ordrDtl : collection) {
                    if (ordrDtl.getWrkrName() == null) {
                        ordrDtl.setWrkrName("");
                    }
                    if (ordrDtl.getWrkrName() == null ? sort == null : ordrDtl.getWrkrName().equals(sort)) {
                        OrderDetailsVO obj = new OrderDetailsVO();
                        obj.setOrdrNo(ordrDtl.getOrderNo().toString());
                        obj.setSrlNo(ordrDtl.getSrlNo());
                        obj.setItemCode(ordrDtl.getItemCode() + "-" + ordrDtl.getItemName());
                        obj.setWeight(ordrDtl.getWeight());
                        obj.setItemSize(ordrDtl.getItemSize());
                        obj.setQty(ordrDtl.getQty());
                        obj.setMeltPer(ordrDtl.getMeltPer());
                        obj.setStamp(ordrDtl.getStamp());
                        obj.setDesignSample(ordrDtl.getDesignSample());
                        obj.setSizeSample(ordrDtl.getSizeSample());
                        obj.setRemark(ordrDtl.getRemark());
                        obj.setDueDate(ordrDtl.getDueDate());
                        obj.setCustName(ordrDtl.getCustName());
                        obj.setRefNo(ordrDtl.getRefNo());
                        obj.setType(ordrDtl.getType());
                        obj.setWrkrName(ordrDtl.getWrkrName());
                        ordrDtls.add(obj);
                    }
                }
            }

            //Create Column In Dynamic Way
            List<TextColumnBuilder> col = new ArrayList<>();
            if (ArrayUtils.contains(listOfColumns, "1")) {
                col.add(Columns.column("Order", "ordrNo", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(70));
            }
            if (ArrayUtils.contains(listOfColumns, "2")) {
                col.add(Columns.column("SL", "srlNo", DataTypes.shortType()).setStyle(columnTitleStyleForData).setWidth(50));
            }
            if (ArrayUtils.contains(listOfColumns, "3")) {
                col.add(Columns.column("Code", "itemCode", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(150));
            }
            if (ArrayUtils.contains(listOfColumns, "4")) {
                col.add(Columns.column("Wt", "weight", DataTypes.floatType()).setStyle(columnTitleStyleForData).setWidth(50));
            }
            if (ArrayUtils.contains(listOfColumns, "5")) {
                col.add(Columns.column("Size", "itemSize", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(50));
            }
            if (ArrayUtils.contains(listOfColumns, "6")) {
                col.add(Columns.column("Qty", "qty", DataTypes.integerType()).setStyle(columnTitleStyleForData).setWidth(50));
            }
            if (ArrayUtils.contains(listOfColumns, "7")) {
                col.add(Columns.column("%", "meltPer", DataTypes.floatType()).setStyle(columnTitleStyleForData).setWidth(50));
            }
            if (ArrayUtils.contains(listOfColumns, "8")) {
                col.add(Columns.column("Stamp", "stamp", DataTypes.stringType()).setStyle(columnTitleStyleForData));
            }
            if (ArrayUtils.contains(listOfColumns, "9")) {
                col.add(Columns.column("Hook", "hook", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(80));
            }
            if (ArrayUtils.contains(listOfColumns, "10")) {
                col.add(Columns.column("Design", "designSample", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(100));
            }
            if (ArrayUtils.contains(listOfColumns, "11")) {
                col.add(Columns.column("Size Sample", "sizeSample", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(100));
            }
            if (ArrayUtils.contains(listOfColumns, "12")) {
                col.add(Columns.column("Remarks", "remark", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(250));
            }
            if (ArrayUtils.contains(listOfColumns, "13")) {
                col.add(Columns.column("Due Date", "dueDate", DataTypes.dateType()).setStyle(columnTitleStyleForData).setPattern("dd/MM/yyyy").setWidth(125));
            }
            if (ArrayUtils.contains(listOfColumns, "14")) {
                col.add(Columns.column("Customer", "custName", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(150));
            }
            if (ArrayUtils.contains(listOfColumns, "15")) {
                col.add(Columns.column("Cust Ref No", "refNo", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(100));
            }
            if (ArrayUtils.contains(listOfColumns, "16")) {
                col.add(Columns.column("Type", "type", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(100));
            }
            if (ArrayUtils.contains(listOfColumns, "17")) {
                col.add(Columns.column("Worker Name", "wrkrName", DataTypes.stringType()).setStyle(columnTitleStyleForData).setWidth(150));
            }

            TextColumnBuilder column[] = new TextColumnBuilder[]{};
            column = col.toArray(column);
            JasperReportBuilder report = DynamicReports.report();//a new report
            report.setColumnTitleStyle(columnTitleStyleForHeader)
                    .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                    .setPageMargin(margin(20))
                    .columns(
                            column
                    )
                    .groupBy(columnGroup)
                    .title(//title of the report
                            Components.text("Order List").setStyle(boldCenteredStyle))
                    .pageFooter(Components.pageXofY().setStyle(boldCenteredStyle))//show page number on the page footer
                    .setDataSource(ordrDtls);

            try {
                print = report.toJasperPrint();
            } catch (DRException | HeadlessException e) {
            } catch (Exception e) {
            }
            return print;
        }

    }

//    @Override
//    public void setResourceLoader(ResourceLoader arg0) {
//        
//    }
}

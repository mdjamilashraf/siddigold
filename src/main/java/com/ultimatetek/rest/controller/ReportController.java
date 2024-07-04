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
package com.ultimatetek.rest.controller;

import com.ultimatetek.services.IReportService;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jamil
 */
@Controller
@RequestMapping("/print")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/orderReport", method = RequestMethod.GET)
    public void orderReport(ModelAndView model, HttpServletResponse response, HttpServletRequest request)
            throws IOException, JRException, SQLException {

        JasperPrint jasperPrint = null;
        OutputStream out = response.getOutputStream();

        try {
            jasperPrint = reportService.printOrderReport(request, response);

            if (jasperPrint != null) {
                jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
                final JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                pdfExporter.exportReport();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }
}

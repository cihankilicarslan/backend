package com.keral.inventoryManagementSystem.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generateReport() throws JRException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            InputStream jasperStream = resourceLoader.getResource("classpath:products_report.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            Map<String, Object> parameters = new HashMap<>();
            // Add parameters if needed
            // parameters.put("paramName", paramValue);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generateReportSale() throws JRException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            InputStream jasperStream = resourceLoader.getResource("classpath:sales_report.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            Map<String, Object> parameters = new HashMap<>();
            // Add parameters if needed
            // parameters.put("paramName", paramValue);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

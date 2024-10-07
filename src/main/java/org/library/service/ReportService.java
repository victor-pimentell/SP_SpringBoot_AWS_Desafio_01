package org.library.service;

import org.library.service.inter.Report;

public class ReportService {

    private Report report;

    public ReportService(Report report) {
        this.report = report;
    }

    public void generateReport() {
        report.generateReport();
    }
}

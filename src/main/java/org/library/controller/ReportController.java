package org.library.controller;

import org.library.service.ReportService;
import org.library.service.inter.BorrowedBooksReport;
import org.library.service.inter.MemberFineReport;

public class ReportController {

    private ReportService reportService;

    public void createBorrowedBookReport() {
        reportService = new ReportService(new BorrowedBooksReport());
        reportService.generateReport();
    }

    public void createMemberFineReport() {
        reportService = new ReportService(new MemberFineReport());
        reportService.generateReport();
    }
}

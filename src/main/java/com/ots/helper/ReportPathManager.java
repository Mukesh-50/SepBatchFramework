package com.ots.helper;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportPathManager {

    private static String reportPath;

    static 
    {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("hh_mm_ss_dd_MM_yyyy"));

        reportPath = "Reports/chaintest_" + timestamp + "/Index.html";

        new File(reportPath).getParentFile().mkdirs();

        System.setProperty(
            "chaintest.generator.simple.output-file",
            reportPath
        );
    }

    public static String getReportPath() {
        return reportPath;
    }
}

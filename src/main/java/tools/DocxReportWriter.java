package tools;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class DocxReportWriter {

    public static void main(String[] args) throws Exception {
        File out = new File("docs/Test_Report.docx");
        out.getParentFile().mkdirs();

        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph p = doc.createParagraph();
        p.createRun().setText("Test Automation Project Report - Sauce Demo (Swag Labs)");

        XWPFParagraph summary = doc.createParagraph();
        summary.createRun().setText("This document summarizes the current test framework state and how to open the Allure report.");

        // list features and scenarios found in src/test/resources/features
        Path featuresDir = Path.of("src/test/resources/features");
        if (Files.exists(featuresDir)) {
            List<Path> featureFiles = Files.list(featuresDir).collect(Collectors.toList());
            for (Path f : featureFiles) {
                XWPFParagraph fp = doc.createParagraph();
                fp.createRun().setText("Feature file: " + f.getFileName().toString());
                List<String> lines = Files.readAllLines(f);
                for (String line : lines) {
                    line = line.trim();
                    if (line.startsWith("Scenario:" ) || line.startsWith("Scenario Outline:")) {
                        XWPFParagraph sp = doc.createParagraph();
                        sp.createRun().setText("  - " + line);
                    }
                }
            }
        }

        XWPFParagraph allure = doc.createParagraph();
        allure.createRun().setText("Allure report (HTML) is available at: target/site/allure-report/index.html\nYou can generate it locally with: allure generate allure-results -o target/site/allure-report --clean\nOr serve it: allure serve target/allure-results");

        try (FileOutputStream fos = new FileOutputStream(out)) {
            doc.write(fos);
        }
        doc.close();
        System.out.println("Wrote docs/Test_Report.docx");
    }
}

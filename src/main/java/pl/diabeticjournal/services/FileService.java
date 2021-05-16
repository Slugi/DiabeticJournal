package pl.diabeticjournal.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.GlucoseMeasurement;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Data
@Transactional
public class FileService {

    private void writeMeasurement(GlucoseMeasurement aMeasurement, Row row) {

        Cell cell = row.createCell(1);
        cell.setCellValue(aMeasurement.getInsulin().getName());

        cell = row.createCell(2);
        cell.setCellValue(aMeasurement.getInsulinUnits());

        cell = row.createCell(3);
        cell.setCellValue(aMeasurement.getGlucoseLevel());

        cell = row.createCell(4);
        cell.setCellValue(aMeasurement.getMeasurementDateTime());

    }


    public ByteArrayOutputStream writeExcelMeasurements(List<GlucoseMeasurement> measurements) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;

        for (GlucoseMeasurement aMeasurement : measurements) {
            Row row = sheet.createRow(++rowCount);
            writeMeasurement(aMeasurement, row);
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream;
        }

    }


}

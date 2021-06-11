package com.pfs.riskmodel.excel;

import com.pfs.riskmodel.dto.RiskModelReportDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 27-May-21.
 */
public class RiskEvaluationReportExcelGen {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<RiskModelReportDTO> riskModelReportDTOS;

    public RiskEvaluationReportExcelGen(List<RiskModelReportDTO> riskModelReportDTOS) {
        this.riskModelReportDTOS= riskModelReportDTOS;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Risk Evaluations");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Risk Evaluation ID", style);
        createCell(row, 1, "Loan Number", style);
        createCell(row, 2, "Project Name ", style);
        createCell(row, 3, "Project Type", style);
        createCell(row, 4, "Project Phase", style);
        createCell(row, 5, "Initiating Department", style);
        createCell(row, 6, "Loan Contract Amount", style);
        createCell(row, 7, "Total Disbursement Amount", style);
        createCell(row, 8, "Initiator", style);
        createCell(row, 9, "Creation Date", style);
        createCell(row, 10, "Creation Time", style);
        createCell(row, 11, "Process Date", style);
        createCell(row, 12, "Process Time", style);
        createCell(row, 13, "Final Rating", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() throws ParseException {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (RiskModelReportDTO riskModelReportDTO : riskModelReportDTOS) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            if (riskModelReportDTO.getRiskModelId() != null)
                createCell(row, columnCount++, riskModelReportDTO.getRiskModelId().toString(), style);
            else
                createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getLoanNumber() != null)
                createCell(row, columnCount++, riskModelReportDTO.getLoanNumber(), style);
            else
                createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getProjectName() != null)
            createCell(row, columnCount++, riskModelReportDTO.getProjectName(), style);
            else
                createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getProjectType() != null)
            createCell(row, columnCount++, riskModelReportDTO.getProjectType(), style);
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getProjectPhase() != null)
            createCell(row, columnCount++, riskModelReportDTO.getProjectPhase(), style);
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getInitiatingDepartment() != null)
            createCell(row, columnCount++, riskModelReportDTO.getInitiatingDepartment(), style);
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getLoanContractAmount() != null)
            createCell(row, columnCount++, riskModelReportDTO.getLoanContractAmount().toString(), style);
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getTotalLoanDisbursedAmount() != null)
            createCell(row, columnCount++, riskModelReportDTO.getTotalLoanDisbursedAmount().toString(), style);
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getInitiator() != null)
                createCell(row, columnCount++, riskModelReportDTO.getInitiator().toString(), style);
            else
                createCell(row, columnCount++, "", style);

            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd/MM/yyyy");
            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");

            if (riskModelReportDTO.getCreateDate() != null) {
                String date =  formatter.parse(formatter.format(riskModelReportDTO.getCreateDate())).toString();
                createCell(row, columnCount++, date.substring(0,10), style);
            }
            else
            createCell(row, columnCount++, "", style);


            if (riskModelReportDTO.getCreateDate() != null) {
                String time = localDateFormat.format(riskModelReportDTO.getCreateDate()).toString();

                createCell(row, columnCount++, time, style);
            }
            else
                createCell(row, columnCount++, "", style);



            if (riskModelReportDTO.getProcessDate() != null){
                String date =  formatter.parse(formatter.format(riskModelReportDTO.getProcessDate())).toString();

                createCell(row, columnCount++, date.toString().substring(0,10), style);
            }
            else
            createCell(row, columnCount++, "", style);

            if (riskModelReportDTO.getProcessDate() != null){
                String time = localDateFormat.format(riskModelReportDTO.getProcessDate()).toString();

                createCell(row, columnCount++, time, style);
            }
            else
                createCell(row, columnCount++, "", style);




            if (riskModelReportDTO.getFinalRating() != null)
                createCell(row, columnCount++, riskModelReportDTO.getFinalRating(), style);
            else
            createCell(row, columnCount++, "", style);

        }
    }

    public void export(HttpServletResponse response) throws IOException  {
        writeHeaderLine();
        try {
            writeDataLines();
        } catch (ParseException p){
            System.out.println(p.toString());
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

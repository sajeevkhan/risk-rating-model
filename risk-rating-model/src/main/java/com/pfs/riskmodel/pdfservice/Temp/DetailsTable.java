package com.pfs.riskmodel.pdfservice.Temp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskModelTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class DetailsTable {

    Font valueFont = new Font(Font.FontFamily.HELVETICA);

    public String [] employeesArray = new String[20];

    public  int[] old = new int[4];
    public static int count = 0;

    public DetailsTable() {

        employeesArray[0] = "Rajan R";
        employeesArray[1] = "Maheem M";
        employeesArray[2] = "Hamsa Thatchacode";
        employeesArray[3] = "Shanavas Khan ";
        employeesArray[4] = "Jamaludheen A ";
        employeesArray[5] = "Muthu swamy";
        employeesArray[6] = "Selvan Muthu";
        employeesArray[7] = "Ibrahim K";
        employeesArray[8] = "Thangal K ";
        employeesArray[9] = "Palanisamy K";
        employeesArray[10] = "Velaayudhan R";
        employeesArray[11] = "Sivamuthu P";
        employeesArray[12] = "Raju Kumaravel";
        employeesArray[13] = "Thanga Muthu ";
        employeesArray[14] = "Paneerselvan G";
        employeesArray[15] = "Parameswaran Kottayam";
        employeesArray[16] = "Sivan Paneerselvam ";
        employeesArray[17] = "Samad A";
        employeesArray[18] = "Mahendram Swamy";
        employeesArray[19] = "Kandasamy P";
//        employeesArray[20] = "Sivamani K";

    }

    public Document buildDetails(Document doc, Integer calendarMonth) throws Exception {


        // Header Font
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(8);

        // Value Font
        valueFont.setColor(BaseColor.WHITE);
        valueFont.setSize(8);

        // Value Font
        Font holidayFont = new Font();
        holidayFont.setColor(BaseColor.GRAY);
        holidayFont.setSize(8);

        //Project Details Table
        float[] columnWidths = {3, 6, 5, 3,3,4 };
        PdfPTable detailsTable = new PdfPTable(columnWidths);
        detailsTable.setWidthPercentage(100.0f);

        detailsTable = addHeaderEntry(detailsTable);


        Calendar cal = new GregorianCalendar(2019,calendarMonth-1,1);

        //cal.set(2018, calendarMonth, 1); //Year, month and day of month
        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        int i = 0;
        int daysOfMonth = this.getDaysofMonth(cal,calendarMonth);

        System.out.println("------------Printing for Month:" + calendarMonth.toString() + "Days in Month" + daysOfMonth);
        System.out.println("------------DATE  " + sdf.format(cal.getTime()));

        for (i =1 ; i<=daysOfMonth; i++) {

                    
            String dateAsString = sdf.format(cal.getTime());
                     

            if( this.checkHoliday(cal) == true) {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPhrase(new Phrase("H    O    L    I    D     A    Y" + dateAsString, holidayFont));
                cell.setColspan(6);
                detailsTable.addCell(cell);
                detailsTable.completeRow();

            }

            else {
                String[] employees = determineEmployees(cal);

                detailsTable = this.addEntry(detailsTable, doc, dateAsString, employees[0], "Band saw worker");
                detailsTable = this.addEntry(detailsTable, doc, dateAsString, employees[1], "Resaw saw worker");
                detailsTable = this.addEntry(detailsTable, doc, dateAsString, employees[2], "Resaw saw worker");
                detailsTable = this.addEntry(detailsTable, doc, dateAsString, employees[3], "Planing ");

                detailsTable = this.addEntry(detailsTable, doc, dateAsString, "Temporary ", "Helper");
                detailsTable = this.addEntry(detailsTable, doc, dateAsString, "Temporary ", "Helper");

                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPhrase(new Phrase(" ", valueFont));
                cell.setColspan(6);
                detailsTable.addCell(cell);
                detailsTable.completeRow();

                detailsTable.addCell(cell);
                detailsTable.completeRow();

                detailsTable.addCell(cell);
                detailsTable.completeRow();

            }

            if (i != daysOfMonth) {
                cal.add(Calendar.DATE, 1);
                detailsTable = addHeaderEntry(detailsTable);
            }

        }

        doc.add(detailsTable);


        return doc;
    }

    private int getDaysofMonth(Calendar cal, Integer calendarMonth) {

        int daysOfMonth = 0;

        int year = cal.get(Calendar.YEAR);
        if (calendarMonth==1 || calendarMonth == 3  || calendarMonth == 4 || calendarMonth == 6
                || calendarMonth == 8 || calendarMonth == 10 || calendarMonth == 12){
            daysOfMonth = 31;
        }
        else
            daysOfMonth = 30;

        if (calendarMonth == 2) {
            if (year/ 4 != 0)
            daysOfMonth = 28;
            else
            daysOfMonth = 29;
        }
        return daysOfMonth;
    }

    private PdfPTable addHeaderEntry(PdfPTable detailsTable) {
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.GRAY);
        cell1.setPhrase(new Phrase("Date",valueFont));

        PdfPCell cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell2.setPhrase(new Phrase("Full Name",valueFont));

        PdfPCell cell3 = new PdfPCell();
        cell3.setBackgroundColor(BaseColor.GRAY);
        cell3.setPhrase(new Phrase("Type of Work",valueFont));

        PdfPCell cell4 = new PdfPCell();
        cell4.setBackgroundColor(BaseColor.GRAY);
        cell4.setPhrase(new Phrase("Present(X)",valueFont));

        PdfPCell cell5 = new PdfPCell();
        cell5.setBackgroundColor(BaseColor.GRAY);
        cell5.setPhrase(new Phrase("Daily Wage(Rs.)",valueFont));


        PdfPCell cell6 = new PdfPCell();
        cell6.setBackgroundColor(BaseColor.GRAY);
        cell6.setPhrase(new Phrase("Overtime Wage(Rs.)",valueFont));


        detailsTable.addCell(cell1);
        detailsTable.addCell(cell2);
        detailsTable.addCell(cell3);

        detailsTable.addCell(cell4);
        detailsTable.addCell(cell5);
        detailsTable.addCell(cell6);
        detailsTable.completeRow();

        return detailsTable;

    }

    private String[] determineEmployees( Calendar cal) {

        Integer day = cal.get(Calendar.DAY_OF_WEEK);
        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);


        String[] employees = new String[4];


//        if (year == 2019) {
//            employees[0] = getRandom();
//            employees[1] = getRandom();
//            employees[2] = getRandom();
//            employees[3] = getRandom();
//
//
//            for (int i =0 ; i<=3 ;++ i){
//                System.out.println(employees[i]);
//            }
//            System.out.println("------------------------------------------------------------------------");
//
//            return employees;
//        }

        if (year == 2019) {

            switch (month) {
                case Calendar.MARCH:
                    if (day/3 == 0) {

                        employees[0] = "Jamaludheen A";
                        employees[1] = "Selvan Muthu";
                        employees[2] = "Ibrahim";
                        employees[3] = "Maheem M";
                        break;
                    }

                    if (day/2 ==0) {
                        employees[0] = "Rajan R";
                        employees[1] = "Muthu swamy";
                        employees[2] = "Thangal K";
                        employees[3] = "Maheem M";
                        break;

                    }

                    if (day/5 ==0) {
                        employees[0] = "Rajan R";
                        employees[1] = "Muthu swamy";
                        employees[2] = "Thangal K";
                        employees[3] = "Shanavas Khan";
                        break;

                    }

                    employees[0] = "Pandi Durai";
                        employees[1] = "Selvan Muthu";
                        employees[2] = "Thangal K";
                        employees[3] = "Maheem M";
                        break;


                case Calendar.JUNE:
                    if (day/10 == 0) {

                        employees[0] = "Shanavas Khan";
                        employees[1] = "Selvan Muthu";
                        employees[2] = "Rajan R";
                        employees[3] = "Maheem M";
                        break;
                    }

                    employees[0] = "Mutusawamy";
                    employees[1] = "Selvan Muthu";
                    employees[2] = "Velaayudhaan K";
                    employees[3] = "Thangal";
                    break;

                case Calendar.JANUARY:



                    if (day/10 == 0) {

                    employees[0] = "Shanavas Khan";
                    employees[1] = "Selvan Muthu";
                    employees[2] = "Rajan R";
                    employees[3] = "Maheem M";
                    break;
                }

                    employees[0] = "Shanavas Khan";
                    employees[1] = "Pandi Durai";
                    employees[2] = "Thangal K";
                    employees[3] = "Maheem M";
                    break;

                case Calendar.FEBRUARY:

                    if (day <= 15) {
                        employees[0] = "Rajan R";
                        employees[1] = "Shanavas Khan";
                        employees[2] = "Thangal";
                        employees[3] = "Maheem M";
                    } else {
                        employees[0] = "Muthu swamy";
                        employees[1] = "Pandi Durai";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;

                case Calendar.MAY:

                    if (day <= 15) {
                        employees[0] = "Jamaludheen A";
                        employees[1] = "Ibrahim";
                        employees[2] = "Muthu swamy";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Shanavas Khan";
                        employees[1] = "Pandi Durai";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;
                case Calendar.DECEMBER:

                    if (day <= 10) {
                        employees[0] = "Pandi Durai";
                        employees[1] = "Ibrahim";
                        employees[2] = "Velaayudhan R";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Rajan R";
                        employees[1] = "Pandi Durai";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;

                case Calendar.NOVEMBER: //Onam and Bakrid
                    if (day <= 20) {
                        employees[0] = "Jamaludheen";
                        employees[1] = "Rajan R";
                        employees[2] = "Velaayudhan R";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Rajan R";
                        employees[1] = "Pandi Durai";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;


                case Calendar.OCTOBER:
                    if (day <= 12) {
                        employees[0] = "Muthu swamy";
                        employees[1] = "Shanavas Khan";
                        employees[2] = "Pandi Durai";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Rajan R";
                        employees[1] = "Pandi Durai";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;
                case Calendar.AUGUST:

                    if (day <= 12) {
                        employees[0] = "Muthu swamy";
                        employees[1] = "Velaayudhan R";
                        employees[2] = "Pandi Durai";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Shanavas Khan";
                        employees[1] = "Thanga Vel Muthu";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;
                case Calendar.SEPTEMBER:

                    if (day <= 15) {
                        employees[0] = "Muthu swamy";
                        employees[1] = "Thanga Vel Muthu";
                        employees[2] = "Pandi Durai";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Rajan R";
                        employees[1] = "Ibrahim ";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;

                case Calendar.APRIL:

                    if (day <= 15) {
                        employees[0] = "Raju Kumaravel";
                        employees[1] = "Thanga Vel Muthu";
                        employees[2] = "Pandi Durai";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Raju Kumaravel";
                        employees[1] = "Shanavas";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Thangal K";
                    }
                    break;

                case Calendar.JULY:

                    if (day <= 15) {
                        employees[0] = "Muthu swamy";
                        employees[1] = "Thanga Vel Muthu";
                        employees[2] = "Shanavas Khan";
                        employees[3] = "Thangal K";
                    } else {
                        employees[0] = "Rajan R";
                        employees[1] = "Shanavas Khan";
                        employees[2] = "Jamaludheen A";
                        employees[3] = "Maheem M";
                    }
                    break;
            }


        }
        return employees;
    }





    private PdfPTable addEntry ( PdfPTable detailsTable,  Document doc, String date, String name, String workType) throws Exception {

        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.WHITE);
        cell1.setPhrase(new Phrase(date,valueFont));

        PdfPCell cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.WHITE);
        cell2.setPhrase(new Phrase(name,valueFont));

        PdfPCell cell3 = new PdfPCell();
        cell3.setBackgroundColor(BaseColor.WHITE);
        cell3.setPhrase(new Phrase(workType,valueFont));

        PdfPCell cell4 = new PdfPCell();
        cell4.setBackgroundColor(BaseColor.WHITE);
        cell4.setPhrase(new Phrase(" ",valueFont));

        PdfPCell cell5 = new PdfPCell();
        cell5.setBackgroundColor(BaseColor.WHITE);
        cell5.setPhrase(new Phrase(" ",valueFont));


        PdfPCell cell6 = new PdfPCell();
        cell6.setBackgroundColor(BaseColor.WHITE);
        cell6.setPhrase(new Phrase(" ",valueFont));


        detailsTable.addCell(cell1);
        detailsTable.addCell(cell2);
        detailsTable.addCell(cell3);

        detailsTable.addCell(cell4);
        detailsTable.addCell(cell5);
        detailsTable.addCell(cell6);
        detailsTable.completeRow();

        return detailsTable;

    }



    private boolean checkHoliday( Calendar cal) {

        Boolean skipDate = false;

        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);



        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            skipDate = true;
            return skipDate;
        }


        if (year == 2018 ){

            switch (month){
                case Calendar.JANUARY:

                    if (day == 26) //Republic day
                        return true;

                    break;

                case Calendar.FEBRUARY:
                    if (day ==13 )
                        return true; //Shivarathri

                    break;
                case Calendar.MARCH:
                        if (day == 30)
                            return true; //Good Friday

                    break;
                case Calendar.APRIL:
                    if (day == 15)
                        return true; //Vishu
                    break;
                case Calendar.MAY:
                    if (day == 1)
                        return true; //May Day

                    break;
                case Calendar.JUNE:
                    if (day == 15)
                        return true; // id
                    break;
                case Calendar.JULY:
                    break;
                case Calendar.AUGUST:
                    System.out.println("August : " + day );
                    if (day == 21 || day == 22 || day == 23 || day == 24 || day == 25 || day == 26 || day == 27 || day == 28  )
                        return true; //Good Friday

                    if (day == 11) //Muhurram
                        return true;

                    break;
                case Calendar.SEPTEMBER:
                    break;
                case Calendar.OCTOBER:
                    break;
                case Calendar.NOVEMBER:
                    break;
                case Calendar.DECEMBER:

                    if (day == 25) //Xmas
                        return true;
                    break;
            }

        }



        if (year == 2019 ){

            switch (month){
                case Calendar.JANUARY:

                    if (day == 26) //Republic day
                        return true;

                    break;

                case Calendar.FEBRUARY:
                    if (day ==13 )
                        return true; //Shivarathri

                    break;
                case Calendar.MARCH:
                    if (day == 30)
                        return true; //Good Friday

                    break;
                case Calendar.APRIL:
                    if (day == 15)
                        return true; //Vishu
                    break;
                case Calendar.MAY:
                    if (day == 1)
                        return true; //May Day

                    break;
                case Calendar.JUNE:
                    if (day == 05)
                        return true; // id
                    break;
                case Calendar.JULY: //Onam and Bakrid
                    break;
                case Calendar.AUGUST:
                    if (day == 11) //Muhurram
                        return true;

                    break;
                case Calendar.SEPTEMBER:
                    if (day == 10 || day == 11 || day == 12 || day == 13 || day == 14 || day == 15 || day == 16 || day == 17  )
                        return true; //Good Friday
                case Calendar.OCTOBER:
                    break;
                case Calendar.NOVEMBER:
                    if (day == 9) //Muhurram
                        return true;

                    break;
                case Calendar.DECEMBER:

                    if (day == 25) //Xmas
                        return true;
                    break;
            }

        }


        return skipDate;
    }



    public String getRandom( ) {

        if (count == 4){ old = new int[4]; }

        int rnd = new Random().nextInt(employeesArray.length);

        for (int i=0; i<=3; ++i){
            if (old[i] == rnd ){
                rnd = new Random().nextInt(employeesArray.length);
                rnd = new Random().nextInt(employeesArray.length);
                rnd = new Random().nextInt(employeesArray.length);
                old[i] = rnd;
            }
        }

        count = count + 1;
        return employeesArray[rnd];

    }


}

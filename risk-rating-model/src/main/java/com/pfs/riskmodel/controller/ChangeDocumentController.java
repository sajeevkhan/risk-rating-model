package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.repository.ChangeDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class ChangeDocumentController {


    @Autowired
    private ChangeDocumentRepository changeDocumentRepository;

    @GetMapping("/changedocument")
    public ResponseEntity getChangeDocumentForLoan(@RequestParam("loanNumber") String loanNumber, HttpServletRequest request) {

        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumber(loanNumber);

        return  ResponseEntity.ok(changeDocuments);
    }


    @GetMapping("/changedocument/dateRange")
    public ResponseEntity getChangeDocumentForLoanDateRange(@RequestParam("loanNumber") String loanNumber,
                                                            @RequestParam("dateFrom") String dateFromString,
                                                            @RequestParam("dateTo") String dateToString,
                                                                            HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format( new Date()   );
        Date dateFrom       = format.parse ( dateFromString);
        Date dateTo       = format.parse ( dateToString);


        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumberAndDateBetween(loanNumber, dateFrom, dateTo );

        return  ResponseEntity.ok(changeDocuments);
    }


    @GetMapping("/changedocument/date")
    public ResponseEntity getChangeDocumentForLoanDateRange(@RequestParam("loanNumber") String loanNumber,
                                                            @RequestParam("date") String date,
                                                            HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format( new Date()   );
        Date dateOn       = format.parse ( date);


        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumberAndDate(loanNumber, dateOn );

        return  ResponseEntity.ok(changeDocuments);
    }

}

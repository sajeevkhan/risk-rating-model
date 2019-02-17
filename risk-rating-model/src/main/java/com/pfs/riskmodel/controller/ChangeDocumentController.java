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
import java.util.ArrayList;
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


    @GetMapping("/changedocuments")
    public ResponseEntity getChangeDocuments(@RequestParam("riskModelId") Long riskModelId,
                                             @RequestParam("loanNumber") String loanNumber,
                                             @RequestParam("dateFrom") String dateFromString,
                                             @RequestParam("dateTo") String dateToString,
                                             HttpServletRequest request) throws Exception{

        List<ChangeDocument> changeDocuments = new ArrayList<>();

        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null)
            changeDocuments = this.getChangeDocumentForRiskModelLoanDateRange(riskModelId,loanNumber,dateFromString,dateToString,request);

        if (riskModelId != null && loanNumber == null && dateFromString == null && dateToString == null) {
            changeDocuments = this.getChangeDocumentForRiskModel(riskModelId,request);
        }

        if (riskModelId == null && loanNumber != null && dateFromString == null && dateToString == null){
            changeDocuments = this.getChangeDocumentForLoan(loanNumber,request);
        }
        if (riskModelId != null && loanNumber == null && dateFromString != null && dateToString != null) {
            changeDocuments = this.getChangeDocumentForRiskModelDateRange(riskModelId,dateFromString,dateToString,request);
        }
        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null) {
            changeDocuments = this.getChangeDocumentForLoanDateRange(loanNumber,dateFromString,dateToString,request);
        }

        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null) {
            changeDocuments = this.getChangeDocumentForLoanDateRange(loanNumber,dateFromString,dateToString,request);
        }

        if (riskModelId != null && loanNumber == null && dateFromString != null && dateToString == null) {
            changeDocuments = this.getChangeDocumentForRiskModelDate(riskModelId,dateFromString,request);
        }
        if (riskModelId == null && loanNumber != null && dateFromString != null && dateToString == null) {
            changeDocuments = this.getChangeDocumentForLoanDate(loanNumber,dateFromString,request);
        }
        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString == null) {
            changeDocuments = this.getChangeDocumentForRiskModelLoanDate(riskModelId,loanNumber,dateFromString,request);
        }

        return ResponseEntity.ok(changeDocuments);

    }


    private List<ChangeDocument> getChangeDocumentForRiskModel(Long id, HttpServletRequest request) {

        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateId(id);
        return changeDocuments;
    }

    private List<ChangeDocument> getChangeDocumentForLoan(String loanNumber, HttpServletRequest request) {

        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumber(loanNumber);
        return changeDocuments;
    }



    private List<ChangeDocument> getChangeDocumentForLoanDateRange(String loanNumber,
                                                                   String dateFromString,
                                                                   String dateToString,
                                                                   HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFrom = format.parse(dateFromString);
        Date dateTo = format.parse(dateToString);
        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumberAndDateBetween(loanNumber, dateFrom, dateTo);
        return changeDocuments;
    }



     private List<ChangeDocument> getChangeDocumentForLoanDate(String loanNumber,
                                                                    String date,
                                                                    HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByLoanNumberAndDate(loanNumber, dateOn );
        return changeDocuments;
    }


    private List<ChangeDocument> getChangeDocumentForRiskModelDate(Long riskModelId,
                                                              String date,
                                                              HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateIdAndDate(riskModelId, dateOn );

        return changeDocuments;
    }
    private List<ChangeDocument> getChangeDocumentForRiskModelLoanDate(Long riskModelId, String loanNumber,
                                                                   String date,
                                                                   HttpServletRequest request) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateIdAndLoanNumberAndDate(
                riskModelId, loanNumber, dateOn );

        return changeDocuments;
    }


    private List<ChangeDocument> getChangeDocumentForRiskModelLoanDateRange(Long riskModelId,
                                                            String loanNumber,
                                                            String dateFromString,
                                                            String dateToString,
                                                            HttpServletRequest request) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom       = format.parse ( dateFromString);
        Date dateTo       = format.parse ( dateToString);

        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateIdAAndLoanNumberAndDateBetween
                                                                (riskModelId,loanNumber, dateFrom, dateTo );
       return changeDocuments;
    }

    private List<ChangeDocument> getChangeDocumentForRiskModelDateRange(Long riskModelId,
                                                                     String dateFromString,
                                                                     String dateToString,
                                                                     HttpServletRequest request) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom       = format.parse ( dateFromString);
        Date dateTo       = format.parse ( dateToString);

        List<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateIdAndDateBetween(
                                                    riskModelId, dateFrom  ,  dateTo);

       return changeDocuments;
    }


}

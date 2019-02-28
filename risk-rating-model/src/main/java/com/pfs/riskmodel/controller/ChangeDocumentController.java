package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.repository.ChangeDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/changedocuments")
    public ResponseEntity getChangeDocuments(@RequestParam(value = "riskModelId", required = false) Long riskModelId,
                                             @RequestParam(value = "loanNumber", required = false) String loanNumber,
                                             @RequestParam(value = "dateFrom", required = false) String dateFromString,
                                             @RequestParam(value = "dateTo", required = false) String dateToString,
                                             Pageable pageable) throws Exception{


        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null)
            return ResponseEntity.ok(this.getChangeDocumentForRiskModelLoanDateRange(riskModelId,loanNumber,dateFromString,dateToString, pageable));

        if (riskModelId != null && loanNumber == null && dateFromString == null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForRiskModel(riskModelId, pageable));
        }

        if (riskModelId == null && loanNumber != null && dateFromString == null && dateToString == null){
            return ResponseEntity.ok(this.getChangeDocumentForLoan(loanNumber,pageable));
        }
        if (riskModelId != null && loanNumber == null && dateFromString != null && dateToString != null) {
            return ResponseEntity.ok(this.getChangeDocumentForRiskModelDateRange(riskModelId,dateFromString,dateToString,pageable));
        }
        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null) {
            return ResponseEntity.ok(this.getChangeDocumentForLoanDateRange(loanNumber,dateFromString,dateToString,pageable));
        }

        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString != null) {
            return ResponseEntity.ok(this.getChangeDocumentForLoanDateRange(loanNumber,dateFromString,dateToString,pageable));
        }

        if (riskModelId != null && loanNumber == null && dateFromString != null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForRiskModelDate(riskModelId,dateFromString,pageable));
        }
        if (riskModelId == null && loanNumber != null && dateFromString != null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForLoanDate(loanNumber,dateFromString, pageable));
        }
        if (riskModelId != null && loanNumber != null && dateFromString != null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForRiskModelLoanDate(riskModelId,loanNumber,dateFromString,pageable));
        }

        return ResponseEntity.noContent().build();

    }


    private Page<ChangeDocument> getChangeDocumentForRiskModel(Long id,  Pageable pageable) {

        Page<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateId(id,pageable);

        return changeDocuments;
    }

    private Page<ChangeDocument> getChangeDocumentForLoan(String loanNumber, Pageable pageable) {

        return changeDocumentRepository.findByLoanNumber(loanNumber, pageable);
    }



    private Page<ChangeDocument> getChangeDocumentForLoanDateRange(String loanNumber,
                                                                   String dateFromString,
                                                                   String dateToString,
                                                                   Pageable pageable) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFrom = format.parse(dateFromString);
        Date dateTo = format.parse(dateToString);
        return changeDocumentRepository.findByLoanNumberAndDateBetween(loanNumber, dateFrom, dateTo,pageable);
    }



     private Page<ChangeDocument> getChangeDocumentForLoanDate(String loanNumber,
                                                                    String date,
                                                                    Pageable pageable) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        return changeDocumentRepository.findByLoanNumberAndDate(loanNumber, dateOn, pageable);
    }


    private Page<ChangeDocument> getChangeDocumentForRiskModelDate(Long riskModelId,
                                                              String date,
                                                              Pageable pageable) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        return changeDocumentRepository.findByRiskModelTemplateIdAndDate(riskModelId, dateOn, pageable);
    }

    private Page<ChangeDocument> getChangeDocumentForRiskModelLoanDate(Long riskModelId, String loanNumber,
                                                                   String date,
                                                                   Pageable pageable) throws ParseException {

        //http://localhost:8090/api/changedocument?loanNumber=10003001&dateFrom=2019-02-03&dateTo=2019-02-03
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn       = format.parse ( date);
        return changeDocumentRepository.findByRiskModelTemplateIdAndLoanNumberAndDate(
                riskModelId, loanNumber, dateOn, pageable);

    }


    private Page<ChangeDocument> getChangeDocumentForRiskModelLoanDateRange(Long riskModelId,
                                                            String loanNumber,
                                                            String dateFromString,
                                                            String dateToString, Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom       = format.parse ( dateFromString);
        Date dateTo       = format.parse ( dateToString);

        return changeDocumentRepository.findByRiskModelTemplateIdAndLoanNumberAndDateBetween
                                                                (riskModelId,loanNumber, dateFrom, dateTo, pageable);
    }

    private Page<ChangeDocument> getChangeDocumentForRiskModelDateRange(Long riskModelId,
                                                                     String dateFromString,
                                                                     String dateToString,
                                                                     Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom       = format.parse ( dateFromString);
        Date dateTo       = format.parse ( dateToString);


        List<ChangeDocument>  changeDocumentList = changeDocumentRepository.
                                                    findByRiskModelTemplateIdAndDateBetween(riskModelId, dateFrom  ,  dateTo);

        Page<ChangeDocument> changeDocuments = changeDocumentRepository.findByRiskModelTemplateIdAndDateBetween(
                                                    riskModelId, dateFrom  ,  dateTo, pageable);

        return changeDocuments;

    }


}

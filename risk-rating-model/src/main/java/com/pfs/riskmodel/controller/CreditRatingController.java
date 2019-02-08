package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.domain.CreditRatingMap;
import com.pfs.riskmodel.domain.CreditRatingSource;
import com.pfs.riskmodel.repository.ChangeDocumentRepository;
import com.pfs.riskmodel.repository.CreditRatingMapRepository;
import com.pfs.riskmodel.repository.CreditRatingSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CreditRatingController {


    @Autowired
    private CreditRatingSourceRepository creditRatingSourceRepository;

    @Autowired
    private CreditRatingMapRepository creditRatingMapRepository;

    @GetMapping("/ratingSources")
    public ResponseEntity fetchAllRatingSources(HttpServletRequest request) {

        List<CreditRatingSource> creditRatingSources = creditRatingSourceRepository.findAll();

        return  ResponseEntity.ok(creditRatingSources);
    }


    @GetMapping("/creditRatings")
    public ResponseEntity getRatingsForSource(@RequestParam("ratingSource") String ratingSource,
                                              @RequestParam("natureOfRatingOfParentFirm") Character natureOfRatingOfParentFirm,
                                              HttpServletRequest request) {

        CreditRatingSource creditRatingSource = creditRatingSourceRepository.findByCode(ratingSource);
        if (creditRatingSource == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Character natureOfRating = (Character)natureOfRatingOfParentFirm;

        List<CreditRatingMap> creditRatingMaps =
                creditRatingMapRepository.findByCreditRatingSourceAndNatureOfRatingOfParentFirm(
                        creditRatingSource,natureOfRatingOfParentFirm);

        return  ResponseEntity.ok(creditRatingMaps);
    }

    @GetMapping("/creditRatingGrade")
    public ResponseEntity getSingleCreditRating(@RequestParam("ratingSource") String ratingSource,
                                                @RequestParam ("creditRating") String creditRating,
                                                @RequestParam("natureOfRatingOfParentFirm") Character natureOfRatingOfParentFirm,
                                                HttpServletRequest request) {

        CreditRatingSource creditRatingSource = creditRatingSourceRepository.findByCode(ratingSource);
        if (creditRatingSource == null) {
            return (ResponseEntity) ResponseEntity.notFound();
        }

        CreditRatingMap creditRatingMap = new CreditRatingMap();

            creditRatingMap =
                    creditRatingMapRepository.findByCreditRatingSourceAndCreditRatingAndNatureOfRatingOfParentFirm(
                    creditRatingSource,
                    creditRating,
                    natureOfRatingOfParentFirm);


        if ( creditRatingMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(creditRatingMap.getGradeAsNumber());
        }

    }

}

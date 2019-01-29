import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

@Injectable()
export class LoanEnquiryService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLoanApplicaton: EnquiryApplicationModel;

    selectedLoanApplicationId: BehaviorSubject<string>;
    
    /**
     * 
     * @param _http: HttpClient
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * searchLoanEnquiries()
     * Fetches a list of loan applications based on the request parameters.
     * @param request: any
     */
    public searchLoanEnquiries(request: any): Observable<any> {
        let str = '/api/loanApplications/search';
        str += '?projectName=' + request.partyName;
        return this._http.get<any>(str);
    }
}

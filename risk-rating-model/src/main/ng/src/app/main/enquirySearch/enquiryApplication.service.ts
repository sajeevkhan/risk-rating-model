import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoanEnquiryService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLoanApplicationId: BehaviorSubject<string>;

    /**
     * 
     * @param _http 
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * searchLoanEnquiries()
     * Fetches a list of loan applications based on the request parameters.
     * @param request
     */
    public searchLoanEnquiries(request: any): Observable<any> {
        console.log(request);
        let str = '/api/loanApplications/search';
        str += '?projectName=' + request.partyName;
        return this._http.get<any>(str);
    }
}

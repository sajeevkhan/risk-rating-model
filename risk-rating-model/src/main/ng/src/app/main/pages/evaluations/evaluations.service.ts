import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class EvaluationService {

    public evaluationList: BehaviorSubject<any>;

    selectedEvaluation: BehaviorSubject<string>;

    /**
     * 
     * @param _http: HttpClient
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * 
     * @param loanNumber: string
     */
    public fetchEvaluations(loanNumber: string): Observable<any> {
        return this._http.get<any>('/api/riskModel/loanNumber/' + loanNumber);
    }

    /**
     * 
     */
    public getProjectTypes(): Observable<any> {
        return this._http.get<any>('api/projectTypes');
    }  
    
    /**
     * 
     */
    public getRiskLevels(): Observable<any> {
        return this._http.get<any>('api/projectRiskLevels');
    }

    /**
     * 
     */
    public getPurposes(): Observable<any> {
        return this._http.get<any>('api/purposes');
    }
}

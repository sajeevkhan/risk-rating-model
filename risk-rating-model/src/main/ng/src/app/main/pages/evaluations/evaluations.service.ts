import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';

@Injectable()
export class EvaluationService implements Resolve<any> {

    public evaluationList: BehaviorSubject<any>;

    selectedEvaluation: BehaviorSubject<any>;

    /**
     * 
     * @param _http: HttpClient
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * 
     * @param route: ActivatedRouteSnapshot
     */
    resolve(route: ActivatedRouteSnapshot): Observable<any> {
        return this.fetchEvaluations(route.params['projectId']);
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

    /**
     * fetchModelPDF()
     * @param evaluation: any
     */
    public fetchModelPDF(evaluation: any): Observable<any> {
        return this._http.get<any>('api/riskModelPDF?id=' + evaluation.id);
    }
}

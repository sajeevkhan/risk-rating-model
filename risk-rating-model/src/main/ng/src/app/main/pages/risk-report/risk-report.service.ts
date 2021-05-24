import {RiskReportModel} from "../../model/riskReport.model";
import {Observable, BehaviorSubject, forkJoin} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";



@Injectable()
export class RiskReportService {

    riskReportResults: BehaviorSubject<RiskReportModel[]>;

    public selectedRiskReportItem : BehaviorSubject<RiskReportModel> = new BehaviorSubject(new RiskReportModel({}));

    public riskReport: BehaviorSubject<RiskReportModel>;

    selectedRiskReportItemId: BehaviorSubject<string>;

 

    /**
     * constructor()
     * @param _http
     */
  constructor(private _http: HttpClient) { }


    /**
     * resolve()
     * Router resolver, fetches data before the ui is created.
     * @param route
     * @param state
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
        if (route.routeConfig.path === 'riskReport') {

        }
        else {
            return forkJoin([
             ]);
        }
    }

    /**
     * fetchRiskReport()
     * @param Array[loanNumber, projectName, projectPhase]
     */
    public getRiskReport(request: Array<string>): Observable<RiskReportModel[]> {
        let queryParams;

        request.forEach(function(value) {
            if (value != undefined){
                queryParams = queryParams + value + "&";
            }
        });

        return new Observable(observer => {

            const riskReportResults = new Array<RiskReportModel>();

            this._http.get<RiskReportModel[]>('risk/api/riskModel/report?' + queryParams).subscribe(result => {
                result.map(riskReportModel => {
                    riskReportResults.push(new RiskReportModel(riskReportModel));
                });
                observer.next(riskReportResults);
                observer.complete();
            });
        });
     }

}

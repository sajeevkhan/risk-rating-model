import {RiskReportModel} from "../../model/riskReport.model";
import {Observable, BehaviorSubject, forkJoin} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


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
     * fetchRiskReport()
     * @param Array[loanNumber, projectName, projectPhase]
     */
    public getRiskReport(request: Array<string>): Observable<RiskReportModel[]> {
        let queryParams = '';

        let i = 0;

        request.forEach(function(value) {
            if (value != undefined){
                switch (i) {
                    case 0: {
                        queryParams = queryParams + "loanNumber=" + value + "&";
                        break;
                    }
                    case 1: {
                        queryParams = queryParams + "projectName=" + value + "&";
                        break;
                    }
                    case 2: {
                        queryParams = queryParams + "riskProjectTypeCode=" + value + "&";
                        break;
                    }
                    // case 3: {
                    //     queryParams = queryParams + "projectPhase=" + value + "&";
                    //     break;
                    // }
                }
            }
            i++;
        });

        console.log("queryParams :" + queryParams)

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

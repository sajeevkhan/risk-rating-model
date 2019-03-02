import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class RiskModelUIService {

    riskModelTemplate: BehaviorSubject<any> = new BehaviorSubject({});

    constructor(private _httpClient: HttpClient) {
    }

    /**
     * getRiskModelTemplate()
     * @param projectType: string
     * @param projectRiskLevel: string
     */
    getRiskModelTemplate(projectType: string, projectRiskLevel: string): Observable<any> {
        return this._httpClient.get<any>('api/riskModelTemplate?projectType=' + projectType +
            '&projectRiskLevel=' + projectRiskLevel);
    }

    /**
     * getRiskModelTemplate()
     * @param templateId: string
     */
    getRiskModelTemplateById(templateId: string): Observable<any> {
        return this._httpClient.get<any>('api/riskModelTemplate/id/' + templateId);
    }

    /**
     * evaluateTemplate()
     * @param template: any
     */
    evaluateTemplate(template: any): Observable<any> {

        return this._httpClient.post<any>('api/riskModel?action=1', template);
    }

    /**
     * approveTemplate()
     * @param template: any
     */
    approveTemplate(template: any): Observable<any> {
        return this._httpClient.post<any>('api/riskModel?action=2', template);
    }

    /**
     * 
     */
    public getPurposes(): Observable<any> {
        return this._httpClient.get<any>('api/purposes');
    }

    /**
     * getRatingSources()
     */
    public getRatingSources(): Observable<any> {
        return this._httpClient.get<any>('api/ratingSources');
    }

    /**
     * getCreditRatings()
     * @param ratingSource: string 
     * @param natureOfRatingOfParentFirm: number
     */
    public getCreditRatings(ratingSource: string, natureOfRatingOfParentFirm: number): Observable<any> {
        return this._httpClient.get<any>('api/creditRatings?ratingSource=' + ratingSource + '&natureOfRatingOfParentFirm=' +
            natureOfRatingOfParentFirm);
    }

    /**
     * getCreditRatingGrade()
     * @param ratingSource: string
     * @param natureOfRatingOfParentFirm: number
     * @param creditRating: string
     */
    public getCreditRatingGrade(ratingSource: string, natureOfRatingOfParentFirm: number, creditRating: string) {
        return this._httpClient.get<any>('api/creditRatingGrade?ratingSource=' + ratingSource + '&natureOfRatingOfParentFirm=' +
            natureOfRatingOfParentFirm + '&creditRating=' + creditRating);
    }
}

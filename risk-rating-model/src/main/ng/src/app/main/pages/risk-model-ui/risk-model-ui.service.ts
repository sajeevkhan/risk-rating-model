import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class RiskModelUIService {

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
}

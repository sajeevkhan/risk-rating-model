import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class RiskModelUIService {

    constructor(private _httpClient: HttpClient) { }

    /**
     * getRiskModelTemplate()
     * @param projectType: string
     * @param projectRiskLevel: string
     */
    getRiskModelTemplate(projectType: string, projectRiskLevel: string): Observable<any> {
        return this._httpClient.get<any>('http://localhost:8080/api/riskModelTemplate?projectType=' + projectType +
            '&projectRiskLevel=' + projectRiskLevel);
    }

    /**
     * getRiskModelTemplate()
     * @param templateId: string
     */
    getRiskModelTemplateById(templateId: string): Observable<any> {
        return this._httpClient.get<any>('http://localhost:8080/api/riskModelTemplate/id/' + templateId);
    }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ChangeDocumentService {

    constructor(private _httpClient: HttpClient) { }

    fetchChangeDocuments(loanNumber: string, riskModelId: string, dateFrom: Date, dateTo: Date, page: number, size: number): Observable<any> {
        let api = 'api/changedocuments?page=' + page + '&size=' + size;
        if (loanNumber !== null && loanNumber !== '') {
            api += '&loanNumber=' + loanNumber;
        }
        if (riskModelId !== null && riskModelId !== '') {
            api += '&riskModelId=' + riskModelId;
        }
        if (dateFrom !== null) {
            api += '&dateFrom=' + dateFrom;
        }
        if (dateTo !== null) {
            api += '&dateTo=' + dateTo;
        }
        return this._httpClient.get<any>(api);
    }
}

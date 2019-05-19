import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';

@Injectable({
    providedIn: 'root'
})
export class ChangeDocumentService {

    constructor(private _httpClient: HttpClient) { }

    fetchChangeDocuments(loanNumber: string, riskModelId: string, dateFrom: Date, dateTo: Date, page: number, size: number): Observable<any> {
        let api = 'risk/api/changedocuments?page=' + page + '&size=' + size;
        if (loanNumber !== null && loanNumber !== '') {
            api += '&loanNumber=' + loanNumber;
        }
        if (riskModelId !== null && riskModelId !== '') {
            api += '&riskModelId=' + riskModelId;
        }
        if (dateFrom !== null) {
            api += '&dateFrom=' + this.parseDateToString(dateFrom);
        }
        if (dateTo !== null) {
            api += '&dateTo=' + this.parseDateToString(dateTo);
        }
        return this._httpClient.get<any>(api);
    }

    parseDateToString(sdate: Date): string {
        // const day = date.getDate();
        // const month = date.getMonth() + 1;
        // const year = date.getFullYear();
        // return `${year}-${month}-${day}`;
        return moment(sdate).format('YYYY-MM-DD');
    }
}

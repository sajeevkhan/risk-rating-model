import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Resolve } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class ChangePasswordService {

    /**
     * constructor()
     * @param _httpClient: HttpClient
     */
    constructor(private _httpClient: HttpClient) { }

    /**
     * changePassword()
     */
    changePassword(userResource: any): Observable<any> {
        return this._httpClient.put<any>('risk/api/password/modify', userResource);
    }
}

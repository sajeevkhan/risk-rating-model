import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Resolve } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AppService {

    userDetails: any = new Object({});

    /**
     * constructor()
     * @param _httpClient: HttpClient
     */
    constructor(private _httpClient: HttpClient) { }

    /**
     * fetchUserDetails()
     */
    fetchUserDetails(): Observable<any> {
        return new Observable<any>(observer => {
            this._httpClient.get<any>('api/welcome').subscribe(data => {
                this.userDetails = data;
                observer.next(data);
            });
        });
    }
}

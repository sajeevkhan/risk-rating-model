import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class InboxService implements Resolve<any> {

    /**
     * constructor()
     * @param _httpClient: HttpClient
     */
    constructor(private _httpClient: HttpClient) { }

    /**
     * resolve()
     */
    resolve(): Observable<any> {
        return this.fetchTasks();
    }

    /**
     * fetchTasks()
     */
    fetchTasks(): Observable<any> {
        return this._httpClient.get<any>('api/tasklist');
    }
}

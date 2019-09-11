import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AppService } from './app.service';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

    constructor(private _router: Router, private _appService: AppService) {
        _appService.fetchUserDetails().subscribe();
    }

    /**
     * 
     * @param next : { ActivatedRouteSnapshot }
     * @param state : { RouterStateSnapshot }
     */
    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        return Observable.create(observer => {
            this._appService.fetchUserDetails().subscribe(data => {
                if (data.role === 'TR0100') {
                    window.alert('You are not authorized to view this application.');
                    window.location.href = '/risk/logout';
                    observer.next(false);
                }
                else
                    observer.next(true);
                observer.complete();
            })
        });
    }
}

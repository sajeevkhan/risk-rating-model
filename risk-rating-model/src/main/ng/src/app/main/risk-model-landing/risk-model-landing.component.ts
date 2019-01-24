import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-risk-model-landing',
    templateUrl: './risk-model-landing.component.html',
    styleUrls: ['./risk-model-landing.component.scss']
})
export class RiskModelLandingComponent implements OnInit {

    constructor(private _router: Router) { 
    }

    ngOnInit(): void {
    }

    processItem(): void {
        this._router.navigate(['riskModelTemplate']);
    }
}

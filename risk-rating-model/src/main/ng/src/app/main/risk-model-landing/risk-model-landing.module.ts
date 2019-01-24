import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskModelLandingComponent } from './risk-model-landing.component';
import { RiskModelListComponent } from './risk-model-list/risk-model-list.component';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';

const routes = [
    {
        path: 'riskModelList',
        component: RiskModelLandingComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatButtonModule
    ],
    declarations: [
        RiskModelLandingComponent,
        RiskModelListComponent
    ]
})
export class RiskModelLandingModule { 
}

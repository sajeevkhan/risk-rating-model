import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelUIComponent } from './risk-model-ui.component';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { MatExpansionModule, MatTabsModule, MatCardModule, MatRadioModule, MatSlideToggleModule, MatInputModule, MatButtonModule, MatIconModule, MatSelectModule, MatSnackBarModule, MatCheckboxModule } from '@angular/material';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';
import { AppService } from 'app/app.service';

const routes = [
    {
        path: 'riskModelTemplate/:mode/:projectType/:riskLevel/:purpose',
        component: RiskModelUIComponent
    },
    {
        path: 'riskModelTemplate/:mode/:riskModelId',
        component: RiskModelUIComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatButtonModule,
        MatCardModule,
        MatCheckboxModule,
        MatExpansionModule,
        MatIconModule,
        MatInputModule,
        MatRadioModule,
        MatSelectModule,
        MatSlideToggleModule,
        MatSnackBarModule,
        MatTabsModule
    ],
    declarations: [
        RiskModelUIComponent,
        RiskModelTemplateComponent
    ],
    providers: [
        AppService,
        RiskModelUIService,
        LoanEnquiryService
    ]
})
export class RiskModelUIModule {
}

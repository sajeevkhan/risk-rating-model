import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelUIComponent } from './risk-model-ui.component';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { MatExpansionModule, MatTabsModule, MatCardModule, MatRadioModule, MatSlideToggleModule, MatInputModule, MatButtonModule, MatIconModule, MatSelectModule, MatSnackBarModule } from '@angular/material';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';

const routes = [
    {
        path: 'riskModelTemplate/:projectType/:riskLevel/:purpose',
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
        RiskModelUIService,
        LoanEnquiryService
    ]
})
export class RiskModelUIModule {
}

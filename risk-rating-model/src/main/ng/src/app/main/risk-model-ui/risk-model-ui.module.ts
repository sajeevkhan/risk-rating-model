import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelUIComponent } from './risk-model-ui.component';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { MatExpansionModule, MatTabsModule, MatCardModule, MatRadioModule, MatSlideToggleModule, MatFormFieldModule, MatInputModule } from '@angular/material';

const routes = [
    {
        path: 'sample',
        component: RiskModelUIComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatCardModule,
        MatExpansionModule,
        MatInputModule,
        MatRadioModule,
        MatSlideToggleModule,
        MatTabsModule
    ],
    declarations: [
        RiskModelUIComponent,
        RiskModelTemplateComponent
    ],
    providers: [
        RiskModelUIService
    ]
})
export class RiskModelUIModule { 
}

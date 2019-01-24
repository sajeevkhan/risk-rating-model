import { Component, OnInit, ViewChild } from '@angular/core';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';

@Component({
    selector: 'app-risk-model-ui',
    templateUrl: './risk-model-ui.component.html',
    styleUrls: ['./risk-model-ui.component.scss']
})
export class RiskModelUIComponent implements OnInit {

    riskModelTemplate: any = {};

    @ViewChild(RiskModelTemplateComponent) riskModelTemplateComponent: RiskModelTemplateComponent;

    constructor(_riskModelService: RiskModelUIService) {

        _riskModelService.getRiskModelTemplate('03', '01').subscribe(response => {
            this.riskModelTemplate = response;
        });

        // _riskModelService.getRiskModelTemplateById('10').subscribe(response => {
        //     this.riskModelTemplate = response;
        //     console.log(response);
        // });
    }

    ngOnInit(): void {
    }

    evaluateTemplate(): void {
        this.riskModelTemplateComponent.evaluateTemplate();
    }
}

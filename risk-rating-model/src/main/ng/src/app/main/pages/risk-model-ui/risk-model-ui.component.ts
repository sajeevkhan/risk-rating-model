import { Component, OnInit, ViewChild } from '@angular/core';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-risk-model-ui',
    templateUrl: './risk-model-ui.component.html',
    styleUrls: ['./risk-model-ui.component.scss']
})
export class RiskModelUIComponent implements OnInit {

    riskModelTemplate: any = {};

    projectType: string;
    riskLevel: string;
    purpose: string;

    @ViewChild(RiskModelTemplateComponent) riskModelTemplateComponent: RiskModelTemplateComponent;

    constructor(_riskModelService: RiskModelUIService, _route: ActivatedRoute) {

        _route.params.subscribe(params => {
            // Fetch route parameters.
            this.projectType = params['projectType'];
            this.riskLevel = params['riskLevel'];
            this.purpose = params['purpose'];

            // Fetch risk model template.
            _riskModelService.getRiskModelTemplate(this.projectType, this.riskLevel).subscribe(response => {
                console.log(response);
                this.riskModelTemplate = response;
            });
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

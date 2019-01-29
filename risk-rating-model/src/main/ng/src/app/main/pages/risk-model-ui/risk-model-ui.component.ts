import { Component, OnInit, ViewChild } from '@angular/core';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { ActivatedRoute } from '@angular/router';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';

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

    constructor(_riskModelService: RiskModelUIService, _loanEnquiryService: LoanEnquiryService, _route: ActivatedRoute) {

        _route.params.subscribe(params => {
            // Fetch route parameters.
            this.projectType = params['projectType'];
            this.riskLevel = params['riskLevel'];
            this.purpose = params['purpose'];

            // Fetch risk model template.
            _riskModelService.getRiskModelTemplate(this.projectType, this.riskLevel).subscribe(response => {
                // Set id to undefined.
                response.id = undefined;
                
                // Initialize project details
                response.projectName = _loanEnquiryService.selectedLoanApplicaton.projectName;
                response.loanNumber = _loanEnquiryService.selectedLoanApplicaton.loanContractId;
                response.loanAmountInCrores = _loanEnquiryService.selectedLoanApplicaton.loanAmount;

                // Initialize purpose code.
                response.purposeCode = this.purpose;
                
                // Initialize riskModelTemplate
                this.riskModelTemplate = response;
                console.log('this.riskModelTemplate', this.riskModelTemplate);
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

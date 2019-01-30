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

    mode: string;
    projectType: string;
    riskLevel: string;
    purpose: string;
    riskModelId: string;

    @ViewChild(RiskModelTemplateComponent) riskModelTemplateComponent: RiskModelTemplateComponent;

    constructor(_riskModelService: RiskModelUIService, _loanEnquiryService: LoanEnquiryService, _route: ActivatedRoute) {

        _route.params.subscribe(params => {
            // Fetch mode parameter from route parameters.
            this.mode = params['mode'];

            if (this.mode === 'new') {
                // Fetch other route parameters
                this.projectType = params['projectType'];
                this.riskLevel = params['riskLevel'];
                this.purpose = params['purpose'];

                // Fetch risk model template if the mode is new
                _riskModelService.getRiskModelTemplate(this.projectType, this.riskLevel).subscribe(response => {
                    // Set id to undefined.
                    response.id = undefined;

                    // Initialize project details
                    response.projectName = _loanEnquiryService.selectedLoanApplicaton.projectName;
                    response.loanNumber = _loanEnquiryService.selectedLoanApplicaton.loanContractId;
                    response.loanAmountInCrores = _loanEnquiryService.selectedLoanApplicaton.loanAmount;

                    // Initialize purpose code.
                    response.purposeCode = this.purpose;

                    // Remove spaces in response.riskParentalNotchUps[0].riskParentalConditions (value) attribute
                    response.riskParentalNotchUps[0].riskParentalConditions.map(condition => {
                        condition.value = '';
                    });

                    // Initialize this.riskModelTemplate
                    this.riskModelTemplate = response;
                    console.log('this.riskModelTemplate', this.riskModelTemplate);
                });
            }
            else {
                // Fetch riskModelId parameter from route parameters.
                console.log('fetching existing risk model');
                this.riskModelId = params['riskModelId'];

                // Fetch existing risk model and initialize this.riskModelTemplate
                _riskModelService.getRiskModelTemplateById(this.riskModelId).subscribe(response => {
                    this.riskModelTemplate = response;
                    console.log('this.riskModelTemplate', this.riskModelTemplate);
                });
            }
        });
    }

    ngOnInit(): void {
    }

    /**
     * evaluateTemplate()
     */
    evaluateTemplate(): void {
        this.riskModelTemplateComponent.evaluateTemplate();
    }

    /**
     * evaluateTemplate()
     */
    sendTemplateForApproval(): void {
        this.riskModelTemplateComponent.sendTemplateForApproval();
    }

    /**
     * displayAsPDF()
     */
    displayAsPDF(): void {
        console.log(this.riskModelTemplate.projectName);
        (window as any).open('api/riskModelPDF?id=' + this.riskModelTemplate.id, '_blank');
    }

    /**
     * 
     */
    validateTemplate(): boolean {
        let isTemplateValid = true;
        if (this.riskModelTemplate === undefined) {
            isTemplateValid = false;
        }
        this.riskModelTemplate.riskTypes.map(riskType => {
            riskType.riskComponents.map(riskComponent => {
                riskComponent.riskFactors.map(riskFactor => {
                    if (this.checkRiskSubFactorSelection(riskFactor) === false) {
                        isTemplateValid = false;
                    }
                });
            });
        });
        return isTemplateValid;
    }

    /**
     * 
     * @param riskFactor: any
     */
    checkRiskSubFactorSelection(riskFactor: any): boolean {
        let subFactorSelections = 0;
        riskFactor.riskSubFactors.map(riskSubFactor => {
            riskSubFactor.riskSubFactorAttributes.map(riskSubFactorAttribute => {
                if (riskSubFactorAttribute.isSelected) {
                    subFactorSelections++;
                }
            });
        });
        return (subFactorSelections === riskFactor.riskSubFactors.length);
    }
}

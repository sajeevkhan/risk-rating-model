import { Component, OnInit, ViewChild, Input } from '@angular/core';
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

    _riskModelTemplate: any = {};

    disablePDFButton = true;

    disableSendForApprovalButton = false;
    
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

                    // Initialize this._riskModelTemplate
                    this._riskModelTemplate = response;
                    console.log('this._riskModelTemplate', this._riskModelTemplate);
                });
            }
            else {
                // Fetch riskModelId parameter from route parameters.
                console.log('fetching existing risk model');
                this.riskModelId = params['riskModelId'];

                // Fetch existing risk model and initialize this._riskModelTemplate
                _riskModelService.getRiskModelTemplateById(this.riskModelId).subscribe(response => {
                    this._riskModelTemplate = response;
                    // Enable the PDF button.
                    this.disablePDFButton = (this._riskModelTemplate.id === undefined || this._riskModelTemplate === null) ? true : false;
                    console.log('this._riskModelTemplate', this._riskModelTemplate);
                });
            }
        });

        _riskModelService.riskModelTemplate.subscribe((riskModelTemplate: any) => {
            // Enable or disable the PDF button.
            this.disablePDFButton = (riskModelTemplate.id === undefined || riskModelTemplate === null) ? true : false;
            // Save the id as it is required to generate the PDF.
            this._riskModelTemplate.id = riskModelTemplate.id;
        })
    }

    ngOnInit(): void {
        this.disablePDFButton = true;
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
        console.log(this._riskModelTemplate.projectName);
        (window as any).open('api/riskModelPDF?id=' + this._riskModelTemplate.id, '_blank');
    }

    /**
     * validateTemplate()
     */
    validateTemplate(): boolean {
        let isTemplateValid = true;
        // Check the validity of riskModelTemplate itself. (Not required I guess, need to delete it)        
        if (this._riskModelTemplate === undefined) {
            isTemplateValid = false;
        }
        // Check validity of each riskTypes and riskFactors.
        this._riskModelTemplate.riskTypes.map(riskType => {
            riskType.riskComponents.map(riskComponent => {
                riskComponent.riskFactors.map(riskFactor => {
                    if (this.checkRiskSubFactorSelection(riskFactor) === false) {
                        isTemplateValid = false;
                    }
                });
            });
        });
        // Check validity of ratingModifiers.
        if (this._riskModelTemplate.applyRatingModifiers) {
            if (this.checkRatingModifiers(this._riskModelTemplate.riskRatingModifiers) === false) {
                isTemplateValid = false;
            }
        }
        // Check validity of parentalNotchups.
        if (this._riskModelTemplate.applyParentalNotchup) {
            if (this.checkRiskSubFactorSelection(this._riskModelTemplate.riskParentalNotchUps[0]) === false) {
                isTemplateValid = false;
            }
        }
        return isTemplateValid;
    }

    /**
     * checkRiskSubFactorSelection()
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

    /**
     * checkRatingModifiers()
     * @param riskRatingModifiers: any
     */
    checkRatingModifiers(riskRatingModifiers: any): boolean {
        let ratingModifiersSelection = 0;
        riskRatingModifiers.map(riskRatingModifier => {
            riskRatingModifier.riskRatingModifierAttributes.map(riskRatingAttribute => {
                if (riskRatingAttribute.yesOrNoIndicator === 'Y') {
                    ratingModifiersSelection++;
                }
            });
        });
        return (ratingModifiersSelection > 0);
    }
}

import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { RiskModelUIService } from './risk-model-ui.service';
import { RiskModelTemplateComponent } from './risk-model-template/risk-model-template.component';
import { ActivatedRoute } from '@angular/router';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';
import { AppService } from 'app/app.service';

@Component({
    selector: 'app-risk-model-ui',
    templateUrl: './risk-model-ui.component.html',
    styleUrls: ['./risk-model-ui.component.scss']
})
export class RiskModelUIComponent implements OnInit {

    _riskModelTemplate: any = {};

    disablePDFButton = true;

    //Expert Button - Enable this only, if the user's department is Risk Department
    displayPDFDebugModeButton = true;  

    disableSendForApprovalButton = false;

    mode: string;
    projectType: string;
    riskLevel: string;
    purpose: string;
    riskModelId: string;

    @ViewChild(RiskModelTemplateComponent) riskModelTemplateComponent: RiskModelTemplateComponent;

    constructor(_riskModelService: RiskModelUIService, _loanEnquiryService: LoanEnquiryService, _route: ActivatedRoute, private _appService: AppService) {

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

                    response.loanEnquiryId = _loanEnquiryService.selectedLoanApplicaton.enquiryNumber;



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

                    if (this._appService.userDetails.riskDepartment == "02") {
                        this.displayPDFDebugModeButton = false;
                    }
                    
                });
            }
        });

        _riskModelService.riskModelTemplate.subscribe((riskModelTemplate: any) => {
            // Enable or disable the PDF button.
            this.disablePDFButton = (riskModelTemplate.id === undefined || riskModelTemplate === null) ? true : false;
            // Save the id as it is required to generate the PDF.
            this._riskModelTemplate.id = riskModelTemplate.id;
            // Save the workFlowStatus to enable/disable sendForApproval button.
            this._riskModelTemplate.workflowStatusCode = riskModelTemplate.workflowStatusCode;
        })
    }

    ngOnInit(): void {
        this.disablePDFButton = true;
        this.displayPDFDebugModeButton = true;
        
    }

    /**
     * disableApprovalButton()
     */
    disableApprovalButton(): boolean {
        let disableButton = true;

        if (this.disableSendForApprovalButton) {
            disableButton = true;
        }
        else if (this._riskModelTemplate.id === undefined) {
            console.log("this._riskModelTemplate.id == undefined")
            disableButton = true;
            console.log("Line 114 diasableButton" + disableButton)

        }
        else if (this._riskModelTemplate.workflowStatusCode !== '01') {
            console.log("this._riskModelTemplate.workflowStatusCode !== '01'")

            disableButton = true;
            console.log("Line 119 diasableButton" + disableButton)

        }
        else if (this._riskModelTemplate.workflowStatusCode === '01' && this.validateTemplate()) {
            console.log("this._riskModelTemplate.workflowStatusCode === '01' && this.validateTemplate()")
            disableButton = false;
            console.log("Line 124 diasableButton" + disableButton)

        }

        //TODO
        // If the status is rejected and and the current user is the initiator 
        if (this._appService.userDetails.email == this._riskModelTemplate.createdByUserId &&
            this._riskModelTemplate.workflowStatusCode == "04") {
            console.log("Current Processord Id : " +this._riskModelTemplate.currentProcessorUserId);
            console.log("Workflow Status code  : " +this._riskModelTemplate.workflowStatusCode);
            console.log("diasableButton" + disableButton)

            disableButton = false
            console.log("Line 140 diasableButton" + disableButton)

        }


        // Added by Sajeev - If CurrentProcessor is the Creator and the Status is Rejected
        if (this._riskModelTemplate.createdByUserId == this._riskModelTemplate.currentProcessorUserId &&
            this._riskModelTemplate.workflowStatusCode == "04" ){
            console.log("Current Processord Id : " +this._riskModelTemplate.currentProcessorUserId);
            console.log("Workflow Status code  : " +this._riskModelTemplate.workflowStatusCode);
            console.log("current workflow level: " +this._riskModelTemplate.currentWorkflowLevel);

            disableButton = false;
            console.log("Line 153 diasableButton" + disableButton)
            
        }

        //if (this._riskModelTemplate.cu)

        // If 3rd Level Approval is completed, SendForApproval Button should be disbale.
        if (this._riskModelTemplate.workFlowStatusCode == "08") {
            disableButton = true;
        }

        // if (this._riskModelTemplate.createdByUserId == "" ||
        //     this._riskModelTemplate.createdByUserId == undefined &&
        //     this._riskModelTemplate.workflowStatusCode == "01") {
        //     disableButton = false;
        //     console.log("Line 168 diasableButton" + disableButton);
        //
        // }
        // if (this._riskModelTemplate.workflowStatusCode == "01" &&
        //     this._riskModelTemplate.id != undefined) {
        //     disableButton = false;
        // }
            
        console.log("Finally.....Disable Approval Button :" + disableButton);

        return disableButton;
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
        (window as any).open('risk/api/riskModelPDF?id=' + this._riskModelTemplate.id, '_blank');
    }

    /**
     * displayAsPDFDebugMode()
     */
    displayAsPDFDebugMode(): void {
        console.log(this._riskModelTemplate.projectName);
        (window as any).open('risk/api/riskModelPDFDebugMode?id=' + this._riskModelTemplate.id, '_blank');
    }

    /**
     * validateTemplate()
     */
    validateTemplate(): boolean {
        let isTemplateValid = true;

        // If the creaetdByUserId is the same as currentProcessUser Id and Workflow Status = "Rejected" 
        if (this._riskModelTemplate.createdByUserId == this._riskModelTemplate.currentProcessorUserId && 
            this._riskModelTemplate.workflowStatusCode == "04"){
            isTemplateValid = true;
            //return isTemplateValid;
        }



        // Check if user.email is the same as the currentProcessorUserId
        if (this._riskModelTemplate.currentProcessorUserId !== null && this._riskModelTemplate.currentProcessorUserId !== this._appService.userDetails.email) {
            isTemplateValid = false;
        }
        else {
            // Check the validity of riskModelTemplate itself. (Not required I guess, need to delete it)        
            if (this._riskModelTemplate === undefined) {
                isTemplateValid = false;
            }
            // Check validity of each riskTypes and riskFactors.
            this._riskModelTemplate.riskTypes.map(riskType => {
                riskType.riskComponents.map(riskComponent => {
                    // Check if risk component is 'Account Conduct' and isApplicable is true.
                    if (riskComponent.description === 'Account Conduct Risk') { // Check above comment.
                        if (riskComponent.isApplicable === true) { // Check above comment.
                            riskComponent.riskFactors.map(riskFactor => {
                                if (this.checkRiskSubFactorSelection(riskFactor) === false) {
                                    isTemplateValid = false;
                                }
                            });
                        }
                    }
                    else { // If risk component is not 'Account Conduct'.
                        riskComponent.riskFactors.map(riskFactor => {
                            if (this.checkRiskSubFactorSelection(riskFactor) === false) {
                                isTemplateValid = false;
                            }
                        });
                    }
                });
            });
            
            // Commented as validity of ratingModifiers need not be checked as per issue risk-rating-model/issues/68
            // Check validity of ratingModifiers.
            // if (this._riskModelTemplate.applyRatingModifiers) {
            //     if (this.checkRatingModifiers(this._riskModelTemplate.riskRatingModifiers) === false) {
            //         isTemplateValid = false;
            //     }
            // }

            // Check validity of parentalNotchups.
            if (this._riskModelTemplate.applyParentalNotchup) {
                if (this.checkRiskSubFactorSelection(this._riskModelTemplate.riskParentalNotchUps[0]) === false) {
                    isTemplateValid = false;
                }
            }
        }
        
        // If third level approval is completed, no modifications are allowed anymore 
        if (this._riskModelTemplate.workflowStatusCode == "08" )
            isTemplateValid = false;

        // If second level approval is completed, user is a department head
        if (this._appService.userDetails.departmentHead == true &&  this._riskModelTemplate.workflowStatusCode == "06"){
            isTemplateValid = true;
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

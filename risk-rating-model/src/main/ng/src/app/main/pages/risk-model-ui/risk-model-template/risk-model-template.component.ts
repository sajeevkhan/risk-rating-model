import { Component, OnInit, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { RiskModelUIService } from '../risk-model-ui.service';
import { MatSnackBar, MatCheckboxChange } from '@angular/material';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';
import { AppService } from 'app/app.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-risk-model-template',
    templateUrl: './risk-model-template.component.html',
    styleUrls: ['./risk-model-template.component.scss']
})
export class RiskModelTemplateComponent implements OnInit, OnChanges {

    @Input()
    riskModelTemplate: any;

    @Input()
    disableSendForApprovalButton: boolean;

    department: string;

    ratingSources: any;
    creditRatings: any;

    riskRatingModifierAttributeSelected: boolean = false;

    // The top most selected tab index.
    selectedIndex = 0;

    selectedLoanApplication: EnquiryApplicationModel;

    purposes: any;

    savingTemplate: boolean;

    constructor(private _appService: AppService, private _riskModelService: RiskModelUIService, private _matSnackBar: MatSnackBar) {
        console.log('_appService', _appService.userDetails);

        // Fetch purposes.
        _riskModelService.getPurposes().subscribe(response => {
            this.purposes = response;
        });

        // Fetch rating sources.
        _riskModelService.getRatingSources().subscribe(response => {
            this.ratingSources = response;
        });
    }

    ngOnInit(): void {
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.department = this.getDepartment();
    }

    selectedIndexChange(val: number): void {
        this.selectedIndex = val;
    }

    /**
     * applyAccountConductToTemplate()
     * @param event 
     */
    applyAccountConductToTemplate(event: any): void {
        this.riskModelTemplate.riskTypes[0].riskComponents.map(riskComponent => {
            if (riskComponent.description === 'Account Conduct') {
                riskComponent.isApplicable = event.checked;
            }
        });
        if (this.riskModelTemplate.riskTypes[1] !== undefined) {
            this.riskModelTemplate.riskTypes[1].riskComponents.map(riskComponent => {
                if (riskComponent.description === 'Account Conduct') {
                    riskComponent.isApplicable = event.checked;
                }
            });
        }
        console.log(this.riskModelTemplate);
    }

    /**
     * riskSubFactorSelectionChanged()
     * @param event: any
     * @param riskSubFactorAttributes: any
     */
    riskSubFactorSelectionChanged(event: any, riskSubFactorAttributes: any): void {
        // Change the isSelected value to true of the selected attribute only. Set others to false.
        console.log(event);
        riskSubFactorAttributes.map(riskSubFactorAttribute => {
            if (riskSubFactorAttribute.itemNo === event.value) {
                riskSubFactorAttribute.isSelected = true;
            }
            else {
                riskSubFactorAttribute.isSelected = false;
            }
        });
        console.log(this.riskModelTemplate);
    }

    /**
     * ratingModifierSelectionChanged()
     * @param event: any
     * @param riskRatingModifierAttribute: any
     */
    ratingModifierSelectionChanged(event: any, riskRatingModifierAttribute: any): void {
        // Change the yesOrNoIndicator attribute of riskRatingModifierAttribute
        if (event.checked === true) {
            riskRatingModifierAttribute.yesOrNoIndicator = 'Y';
            this.riskRatingModifierAttributeSelected = true;
        }
        else {
            riskRatingModifierAttribute.yesOrNoIndicator = 'N';
        }
        console.log(this.riskModelTemplate);
    }

    /**
     * parentalNotchUpRiskSubFactorSelectionChanged()
     * @param event: any
     * @param riskSubFactorAttributes: any
     */
    parentalNotchUpRiskSubFactorSelectionChanged(event: any, riskSubFactorAttributes: any): void {
        // Change the isSelected value to true of the selected attribute only. Set others to false.
        riskSubFactorAttributes.map(riskSubFactorAttribute => {
            if (riskSubFactorAttribute.itemNo === event.value) {
                riskSubFactorAttribute.isSelected = true;
            }
            else {
                riskSubFactorAttribute.isSelected = false;
            }
        });
        console.log(this.riskModelTemplate.riskParentalNotchUps);
    }

    /**
     * 
     */
    evaluateTemplate(): void {
        this.savingTemplate = true;

        // Change modelType to 1 in case it is 0.
        if (this.riskModelTemplate.modelType === 0) {
            this.riskModelTemplate.modelType = 1;
        }
        // 
        this._riskModelService.evaluateTemplate(this.riskModelTemplate).subscribe(response => {
            // Save the response.
            this.riskModelTemplate = response;
            // Broadcast the changes
            this._riskModelService.riskModelTemplate.next(response);
            // Make the first tab as active and display an alert to the user.
            this.selectedIndex = 0;
            this._matSnackBar.open('Evaluated and saved', 'Ok', { duration: 7000 });
            this.savingTemplate = false;
        },
            error => {
                this.handleError(error);
            });
    }

    /**
     * sendTemplateForApproval()
     */
    sendTemplateForApproval(): void {
        this.savingTemplate = true;

        this._riskModelService.approveTemplate(this.riskModelTemplate).subscribe(response => {
            // Save the response.
            this.riskModelTemplate = response;
            // Broadcast the changes
            this._riskModelService.riskModelTemplate.next(response);
            // Make the first tab as active and display an alert to the user.
            this.selectedIndex = 0;
            this._matSnackBar.open('Evaluated, saved & sent for approval', 'Ok', { duration: 7000 });
            this.savingTemplate = false;
            this.disableSendForApprovalButton = true;
        },
            error => {
                this.handleError(error);
            });
    }

    /**
     * 
     * @param riskType: any
     */
    checkRiskComponentSelection(riskType: any): boolean {
        let riskComponentSelections = 0;
        riskType.riskComponents.map(riskComponent => {
            if (riskComponent.description === 'Account Conduct') {
                if (riskComponent.isApplicable) {
                    if (this.checkRiskFactorSelection(riskComponent) === true) {
                        riskComponentSelections++;
                    }
                }
            }
            else {
                if (this.checkRiskFactorSelection(riskComponent) === true) {
                    riskComponentSelections++;
                }
            }
        });

        if (riskType.isAccountConductRiskComponentPresent === false)
            return (riskComponentSelections === riskType.riskComponents.length);
        else {
            if (riskType.isAccountConductRiskApplicable === true)
                return (riskComponentSelections === riskType.riskComponents.length);
            else
                return (riskComponentSelections === riskType.riskComponents.length - 1);
        }

        // if (riskType.isAccountConductRiskApplicable === true) {
        //     return (riskComponentSelections === riskType.riskComponents.length);
        // }
        // else {
        //     // Account Conduct tab should not be validated.
        //     return (riskComponentSelections === riskType.riskComponents.length - 1);
        // }
    }

    /**
     * 
     * @param riskComponent: any
     */
    checkRiskFactorSelection(riskComponent: any): boolean {
        let riskFactorSelections = 0;
        riskComponent.riskFactors.map(riskFactor => {
            if (this.checkRiskSubFactorSelection(riskFactor) === true) {
                riskFactorSelections++;
            }
        });
        return (riskFactorSelections === riskComponent.riskFactors.length);
    }

    /**
     * 
     * @param riskSubFactors: any
     */
    checkParentalNotchUpSelection(riskSubFactors: any): boolean {
        let parentalNotchUpSelections = 0;
        riskSubFactors.map(riskSubFactor => {
            riskSubFactor.riskSubFactorAttributes.map(riskSubFactorAttribute => {
                if (riskSubFactorAttribute.isSelected) {
                    parentalNotchUpSelections++;
                }
            });
        });
        return (parentalNotchUpSelections === riskSubFactors.length);
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

    /**
     * fetchObligatorRatingGrades()
     */
    fetchObligatorRatingGrades() {
        let ratingSource = this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[0].value;
        let natureOfRatingOfParentFirm = this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[1].natureOfRatingOfParentFirm;
        if (ratingSource !== '') {
            this._riskModelService.getCreditRatings(ratingSource, natureOfRatingOfParentFirm).subscribe(response => {
                this.creditRatings = response;
            });
        }
    }

    /**
     * fetchCreditRatingGrade()
     */
    fetchCreditRatingGrade() {
        let ratingSource = this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[0].value;
        let natureOfRatingOfParentFirm = this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[1].natureOfRatingOfParentFirm;
        let creditRating = this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[2].value;
        if (ratingSource !== '' && creditRating !== '') {
            this._riskModelService.getCreditRatingGrade(ratingSource, natureOfRatingOfParentFirm, creditRating).subscribe(response => {
                this.riskModelTemplate['riskParentalNotchUps'][0].riskParentalConditions[3].value = response;
            });
        }
    }

    /**
     * applyRatingModifiers()
     * @param event 
     */
    applyRatingModifiers(event: MatCheckboxChange): void {
        if (event.checked === false) {
            this.riskModelTemplate['riskRatingModifiers'].map(riskRatingModifier => {
                riskRatingModifier.riskRatingModifierAttributes.map(riskRatingModifierAttribute => {
                    riskRatingModifierAttribute.yesOrNoIndicator = 'N';
                });
            });
        }
    }

    /**
     * getDepartment()
     */
    getDepartment(): string {
        let department = '';
        this.purposes.map(purpose => {
            console.log(purpose.code, this.riskModelTemplate.purposeCode);
            if (purpose.code === this.riskModelTemplate.purposeCode) {
                department = purpose.description;
            }
        });
        return department;
    }

    private handleError(error: HttpErrorResponse) {
        this.savingTemplate = false;
        this._matSnackBar.open(error.message, 'Ok', { duration: 7000 });
    }
}

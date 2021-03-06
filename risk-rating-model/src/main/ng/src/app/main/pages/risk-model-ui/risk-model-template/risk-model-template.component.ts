import { Component, OnInit, Input } from '@angular/core';
import { RiskModelUIService } from '../risk-model-ui.service';
import { MatSnackBar } from '@angular/material';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

@Component({
    selector: 'app-risk-model-template',
    templateUrl: './risk-model-template.component.html',
    styleUrls: ['./risk-model-template.component.scss']
})
export class RiskModelTemplateComponent implements OnInit {

    @Input()
    riskModelTemplate: any;

    // The top most selected tab index.
    selectedIndex = 0;

    selectedLoanApplication: EnquiryApplicationModel;

    purposes: any;

    constructor(private _riskModelService: RiskModelUIService, private _matSnackBar: MatSnackBar) {
        // Fetch purposes.
        _riskModelService.getPurposes().subscribe(response => {
            this.purposes = response;
        });
    }

    ngOnInit(): void {
    }

    selectedIndexChange(val: number): void {
        this.selectedIndex = val;
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
        // Change modelType to 1 in case it is 0.
        if (this.riskModelTemplate.modelType === 0) {
            this.riskModelTemplate.modelType = 1;
        }

        // 
        this._riskModelService.evaluateTemplate(this.riskModelTemplate).subscribe(response => {
            // Save the response.
            this.riskModelTemplate = response;
            // Make the first tab as active and display an alert to the user.
            this.selectedIndex = 0;
            this._matSnackBar.open('Evaluated and saved', 'Ok', { duration: 7000 });
        });
    }

    /**
     * sendTemplateForApproval()
     */
    sendTemplateForApproval(): void {
        // 
        this._riskModelService.approveTemplate(this.riskModelTemplate).subscribe(response => {
            // Save the response.
            this.riskModelTemplate = response;
            // Make the first tab as active and display an alert to the user.
            this.selectedIndex = 0;
            this._matSnackBar.open('Evaluated, saved & sent for approval', 'Ok', { duration: 7000 });
        });
    }

    /**
     * 
     * @param riskType: any
     */
    checkRiskComponentSelection(riskType: any): boolean {
        let riskComponentSelections = 0;
        riskType.riskComponents.map(riskComponent => {
            if (this.checkRiskFactorSelection(riskComponent) === true) {
                riskComponentSelections++;
            }
        });
        return (riskComponentSelections === riskType.riskComponents.length);
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

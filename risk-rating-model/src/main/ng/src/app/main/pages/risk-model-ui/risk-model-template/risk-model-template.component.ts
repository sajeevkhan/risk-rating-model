import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RiskModelUIService } from '../risk-model-ui.service';
import { MatSnackBar } from '@angular/material';

@Component({
    selector: 'app-risk-model-template',
    templateUrl: './risk-model-template.component.html',
    styleUrls: ['./risk-model-template.component.scss']
})
export class RiskModelTemplateComponent implements OnInit {

    @Input()
    riskModelTemplate: any;

    displayValues: FormGroup;

    selectedIndex: number;

    constructor(private _formBuilder: FormBuilder, private _riskModelService: RiskModelUIService, private _matSnackBar: MatSnackBar) {
    }

    ngOnInit(): void {
        this.displayValues = this._formBuilder.group({
            projectName: [this.riskModelTemplate.projectName || '']
        });
    }

    /**
     * riskSubFactorSelectionChanged()
     * @param event: any
     * @param riskSubFactorAttributes: any
     */
    riskSubFactorSelectionChanged(event: any, riskSubFactorAttributes: any): void {
        // Change the isSelected value to true of the selected attribute only. Set others to false.
        riskSubFactorAttributes.map(riskSubFactorAttribute => {
            if (riskSubFactorAttribute.id === event.value) {
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
            if (riskSubFactorAttribute.id === event.value) {
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
        this._riskModelService.evaluateTemplate(this.riskModelTemplate).subscribe(response => {
            // Save the response.
            this.riskModelTemplate = response;
            // Make the first tab as active and display an alert to the user.
            this.selectedIndex = 0;
            this._matSnackBar.open('Evaluated and saved', 'Ok', { duration: 7000 });
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

import { Component, ViewEncapsulation, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EvaluationService } from '../evaluations/evaluations.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-new-evaluation-dialog-componenet',
    templateUrl: './new-evaluation-dialog.component.html',
    styleUrls: ['./new-evaluation-dialog.component.scss'],
    encapsulation: ViewEncapsulation.None,
})
export class NewEvaluationDialogComponent {

    /**
     * Reactive form on the dialog.
     */
    newEvaluationForm: FormGroup;

    /**
     * 
     */
    projectTypes: any;
    purposes: any;
    riskLevels: any;

    //
    projectId: string;

    /**
     * constructor()
     * @param _dialogRef: MatDialogRef 
     * @param _formBuilder: FormBuilder
     * @param _service: EvaluationService
     * @param _router: Router
     */
    constructor(public _dialogRef: MatDialogRef<NewEvaluationDialogComponent>, @Inject(MAT_DIALOG_DATA) _data: any,
        _formBuilder: FormBuilder, _service: EvaluationService, private _router: Router) {

        // Initialize the form.
        this.newEvaluationForm = _formBuilder.group({
            projectType: [''],
            riskLevel: [''],
            purpose: ['']
        });

        // Fetch project types.
        _service.getProjectTypes().subscribe(response => {
            this.projectTypes = response;
        });

        // Fetch risk levels.
        _service.getRiskLevels().subscribe(response => {
            this.riskLevels = response;
        });

        // Fetch purposes.
        _service.getPurposes().subscribe(response => {
            this.purposes = response;
        });

        // Retrieve projectId from the dialog data.
        this.projectId = _data.projectId;
    }

    /**
     * 
     */
    fetchEvaluationTemplate(): void {
        // Navigate to risk model template.
        this._router.navigate(['riskModelTemplate/' + this.newEvaluationForm.value.projectType +
            '/' + this.newEvaluationForm.value.riskLevel + '/' + this.newEvaluationForm.value.purpose]);
        // Close the dialog.
        this._dialogRef.close();
    }
}

import { Component, ViewEncapsulation, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'app/app.service';
import { LoanEnquiryService } from '../enquiryApplication.service';

@Component({
    selector: 'app-assign-processors-dialog-componenet',
    templateUrl: './assign-processors-dialog.component.html',
    styleUrls: ['./assign-processors-dialog.component.scss'],
    encapsulation: ViewEncapsulation.None,
})
export class AssignProcessorsDialogComponent {

    /**
     * Reactive form on the dialog.
     */
    assignProcessorsForm: FormGroup;

    projectUsers: Array<any> = [];
    monitoringUsers: Array<any> = [];

    constructor(public _dialogRef: MatDialogRef<AssignProcessorsDialogComponent>, @Inject(MAT_DIALOG_DATA) private _data: any,
        _formBuilder: FormBuilder, private _service: LoanEnquiryService, private _appService: AppService, private _matSnackBar: MatSnackBar) {

        // Initialize the form.
        this.assignProcessorsForm = _formBuilder.group({
            projectDepartmentInitiator: [''],
            monitoringDepartmentInitiator: ['']
        });

        // Get Project department users.
        _service.getProjectDepartmentUsers().subscribe(response => {
            this.projectUsers = response;
        });

        // Get Monitoring department users.
        _service.getMonitoringDepartmentUsers().subscribe(response => {
            this.monitoringUsers = response;
        });
    }

    /**
     * updateLoanApplication()
     */
    updateLoanApplication(): void {
        if (!this.assignProcessorsForm.invalid) {
            this._service.updateProcessors(this._data, this.assignProcessorsForm.value).subscribe(response => {
                this._matSnackBar.open('Loan application update with processors.', 'OK', {
                    duration: 5000
                });
                this._dialogRef.close();
            });
        }
    }
}

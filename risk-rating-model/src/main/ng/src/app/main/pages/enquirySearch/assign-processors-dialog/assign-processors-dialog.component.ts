import { Component, ViewEncapsulation, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'app/app.service';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { HttpErrorResponse } from '@angular/common/http';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

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
    riskUsers: Array<any> = [];

    projectDepartmentInitiatorReadonly: boolean = false;
    monitoringDepartmentInitiatorReadonly: boolean = false;
    riskDepartmentInitiatorReadonly: boolean = false;


    projectDepartmentInitiatorDisabled: boolean = true;
    monitoringDepartmentInitiatorDisabled : boolean = true;
    riskDepartmentInitiatorDisabled : boolean = true;


    // TODO
    //  - Default the values with already existing values on the loan application
    projectDepartmentInitiatorSelected: string;
    monitoringDepartmentInitiatorSelected: string;
    riskDepartmentOfficerSelected: string;


    constructor(public _dialogRef: MatDialogRef<AssignProcessorsDialogComponent>, @Inject(MAT_DIALOG_DATA) private _data: any,
        _formBuilder: FormBuilder, private _service: LoanEnquiryService, private _appService: AppService, private _matSnackBar: MatSnackBar) {



        // Initialize the form.
        this.assignProcessorsForm = _formBuilder.group({
            projectDepartmentInitiator: [''],
            monitoringDepartmentInitiator: [''],
            riskDepartmentInitiator: ['']

        });

        // Get Project department users.
        _service.getProjectDepartmentUsers().subscribe(response => {
            this.projectUsers = response;
        },
        error => {
            this.handleError(error);
        });

        // Get Monitoring department users.
        _service.getMonitoringDepartmentUsers().subscribe(response => {
            this.monitoringUsers = response;
        },
        error => {
            this.handleError(error);
        });

         // Get Risk department users.
        _service.getRiskDepartmentUsers().subscribe(response => {
                this.riskUsers = response;
            },
            error => {
                this.handleError(error);
            });

        this.projectDepartmentInitiatorSelected = _service.selectedLoanApplicaton.projectDepartmentInitiator;
        this.monitoringDepartmentInitiatorSelected = _service.selectedLoanApplicaton.monitoringDepartmentInitiator;
        this.riskDepartmentOfficerSelected = _service.selectedLoanApplicaton.riskDepartmentInitiator;

        //console.log( "User's Department : " + _appService.userDetails.riskDepartment )

        if (_appService.userDetails.riskDepartment == "01") {
            this.projectDepartmentInitiatorReadonly = true;
            
            this.projectDepartmentInitiatorDisabled = false;
            this.monitoringDepartmentInitiatorDisabled = true;
            this.riskDepartmentInitiatorDisabled = true;

        }

        if (_appService.userDetails.riskDepartment == "02") {
            this.riskDepartmentInitiatorReadonly = true;

            this.monitoringDepartmentInitiatorDisabled = true;
            this.projectDepartmentInitiatorDisabled = true;
            this.riskDepartmentInitiatorDisabled = false;

        }

        if (_appService.userDetails.riskDepartment == "03") {

            this.monitoringDepartmentInitiatorReadonly = true;
           
            this.riskDepartmentInitiatorDisabled = true;
            this.projectDepartmentInitiatorDisabled = true;
            this.monitoringDepartmentInitiatorDisabled = false;

        }

        //TODO Check Logic 
        // if (_service.selectedLoanApplicaton.functionalStatus == 6 || _service.selectedLoanApplicaton.functionalStatus === 7 || 
        //     _service.selectedLoanApplicaton.functionalStatus === 8)
        // {
        //     this.projectDepartmentInitiatorReadonly = false;
        //     this.monitoringDepartmentInitiatorReadonly = true;
        //     this.riskDepartmentInitiatorReadonly = true;
        // }
        // else {
        //     this.projectDepartmentInitiatorReadonly = true;
        //     this.monitoringDepartmentInitiatorReadonly = false;
        //     this.riskDepartmentInitiatorReadonly = true;
        // }
    }

    /**
     * updateLoanApplication()
     */
    updateLoanApplication(): void {
        if (!this.assignProcessorsForm.invalid) {
            
            //console.log("assignProcessorsForm" , this.assignProcessorsForm);
            //console.log("this.assignProcessorsForm.value", this.assignProcessorsForm.value);


            this._service.updateProcessors(this._data, this.assignProcessorsForm.value).subscribe(response => {
                this._matSnackBar.open('Loan application update with processors.', 'OK', {
                    duration: 5000
                });
                this._dialogRef.close();
            },
            error => {
                this.handleError(error);
            });
        }
    }

    private handleError(error: HttpErrorResponse) {
        this._matSnackBar.open(error.message, 'Ok', { duration: 7000 });
    }
}

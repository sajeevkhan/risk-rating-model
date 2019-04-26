import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router } from '@angular/router';
import { LoanEnquiryService } from './enquiryApplication.service';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';
import { AppService } from 'app/app.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { AssignProcessorsDialogComponent } from './assign-processors-dialog/assign-processors-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchComponent implements OnChanges {

    displaySpinner: boolean;

    enquirySearchForm: FormGroup;

    enquiryList: EnquiryApplicationModel[];

    ngOnChanges(changes: SimpleChanges): void {
        console.log('changes', changes);
    }

    constructor(_formBuilder: FormBuilder, private _dialog: MatDialog, public _service: LoanEnquiryService,
        public _appService: AppService, private _router: Router, private _matSnackBar: MatSnackBar) {

        this.enquirySearchForm = _formBuilder.group({
            loanNumberFrom: [],
            loanNumberTo: [],
            projectName: [],
        });

        _service.selectedLoanApplicationId = undefined;
        _service.selectedLoanApplicaton = undefined;
    }

    /**
     * searchEnquiries()
     */
    searchEnquiries(): void {
        this.displaySpinner = true;
        this._service.searchLoanEnquiries(this.enquirySearchForm.value).subscribe((result) => {
            const enquiryApplications = new Array<EnquiryApplicationModel>();
            result.body.map(loanApplicationResourceModel => {
                if (this._appService.userDetails.role === "ZLM023" || this._appService.userDetails.riskDepartment === '02' || this._appService.userDetails.departmentHead === true) {
                    // Return all applications if the user is an admin or if he is from the risk assesssment department (02) or if he is a department head.
                    enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
                }
                else if (this._appService.userDetails.riskDepartment === '01') {
                    // If the user is from the project assesssment department (01), return applications where application projectDepartmentInitiator is himself.
                    if (loanApplicationResourceModel.loanApplication.projectDepartmentInitiator === this._appService.userDetails.email) {
                        enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
                    }
                }
                else if (this._appService.userDetails.riskDepartment === '03') {
                    // If the user is from the monitoring assesssment department (03), return applications where application monitoringDepartmentInitiator is himself.
                    if (loanApplicationResourceModel.loanApplication.monitoringDepartmentInitiator === this._appService.userDetails.email) {
                        enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
                    }
                }
            });
            console.log(this._appService.userDetails);
            console.log(enquiryApplications);
            this.enquiryList = enquiryApplications;
            this.displaySpinner = false;
        }, error => {
            this.handleError(error);
        });
    }

    /**
     * searchEnquiries()
     */
    searchEnquiriesTest(): void {
        const enquiryApplications = new Array<EnquiryApplicationModel>();
        this._service.testEnquiries.map(loanApplicationResourceModel => {
            enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
        });
        this.enquiryList = enquiryApplications;
    }

    /**
     * 
     */
    fetchEvaluations(): void {
        if (this._service.selectedLoanApplicaton.monitoringDepartmentInitiator === '' && this._service.selectedLoanApplicaton.projectDepartmentInitiator === '') {
            this._matSnackBar.open('Project Officer and Monitoring Officer is not assigned to the Loan. Please request your department ' +
                'head to assign the officers', 'Ok', { duration: 7000 });
        }
        else {
            if (this._service.selectedLoanApplicaton.loanContractId === null) {
                this._router.navigate(['/evaluations', 'enquiry', this._service.selectedLoanApplicaton.enquiryNumber]);
            }
            else {
                this._router.navigate(['/evaluations', 'loan', this._service.selectedLoanApplicationId.value]);
            }
        }
    }

    /**
     * assignProcessors()
     */
    assignProcessors(): void {
        // this._router.navigate(['/riskModelTemplate']);
        const dialogRef = this._dialog.open(AssignProcessorsDialogComponent, {
            panelClass: 'app-assign-processors-dialog',
            data: {
                'selectedEnquiry': this._service.selectedLoanApplicaton
            },
            width: '500px'
        });
        dialogRef.afterClosed().subscribe(() => {
            // Refresh list here.
            this.searchEnquiries();
            this._service.selectedLoanApplicaton = undefined;
        });
    }

    private handleError(error: HttpErrorResponse) {
        this._matSnackBar.open(error.message, 'Ok', { duration: 7000 });
    }
}

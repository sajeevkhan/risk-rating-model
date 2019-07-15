import { Component, Inject } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { EvaluationService } from './evaluations.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';
import { Router, ActivatedRoute } from '@angular/router';
import { AppService } from 'app/app.service';

@Component({
    selector: 'fuse-evaluations',
    templateUrl: './evaluations.component.html',
    styleUrls: ['./evaluations.component.scss'],
    animations: fuseAnimations
})
export class EvaluationComponent {

    projectId: string;

    loanApplicaton: EnquiryApplicationModel;

    evaluations: any;

    /**
     * constructor()
     * @param _service: EvaluationService
     * @param _dialog: MatDialog
     * @param _loanEnquiryService: LoanEnquiryService
     */
    constructor(public _service: EvaluationService, private _dialog: MatDialog, _loanEnquiryService: LoanEnquiryService,
        private _router: Router, _route: ActivatedRoute, private _matSnackBar: MatSnackBar, public _appService: AppService) {

        //
        _service.selectedEvaluation = undefined;

        //
        this.loanApplicaton = _loanEnquiryService.selectedLoanApplicaton;

        // Fetch evaluations from route resolved data.
        _route.data.subscribe((data) => {
            this.evaluations = data.routeResolvedData;
        },
        error => {
            this._matSnackBar.open(error.message, 'Ok', { duration: 7000 });
        });
    }

    /**
     * 
     */
    newEvaluation(): void {
        // this._router.navigate(['/riskModelTemplate']);
        if (this.loanApplicaton.functionalStatus == 6 || this.loanApplicaton.functionalStatus == 7 || this.loanApplicaton.functionalStatus == 8)
        {
            if (this.loanApplicaton.monitoringDepartmentInitiator === null || this.loanApplicaton.monitoringDepartmentInitiator === '')
            {
                this._matSnackBar.open('Loan is already disbursed and a monitoring officer is not assigned. Please assign a monitoring officer before you create a risk model evaluation.', 'Ok', { duration: 7000 });
            }
            else {
                const dialogRef = this._dialog.open(NewEvaluationDialogComponent, {
                    panelClass: 'new-evaluation-dialog',
                    data: {
                        'loanApplication': this.loanApplicaton
                    },
                    width: '500px'
                });
            }
        }
        else {
            const dialogRef = this._dialog.open(NewEvaluationDialogComponent, {
                panelClass: 'new-evaluation-dialog',
                data: {
                    'loanApplication': this.loanApplicaton
                },
                width: '500px'
            });
        }
    }

    /**
     * displayAsPDF()
     */
    displayAsPDF(): void {
        // this._service.fetchModelPDF(this._service.selectedEvaluation.value).subscribe();
        // this.document.location.href = 'risk/api/riskModelPDF?id=' + this._service.selectedEvaluation.value.id;
        (window as any).open('risk/api/riskModelPDF?id=' + this._service.selectedEvaluation.value.id, '_blank');
    }

    /**
     * editEvaluation()
     */
    editEvaluation(): void {
        this._router.navigate(['riskModelTemplate/edit/' + this._service.selectedEvaluation.value.id]);
    }
}

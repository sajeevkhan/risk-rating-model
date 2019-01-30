import { Component, Inject } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { EvaluationService } from './evaluations.service';
import { MatDialog } from '@angular/material';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';
import { DOCUMENT } from '@angular/common';
import { Router } from '@angular/router';

@Component({
    selector: 'fuse-evaluations',
    templateUrl: './evaluations.component.html',
    styleUrls: ['./evaluations.component.scss'],
    animations: fuseAnimations
})
export class EvaluationComponent {

    projectId: string;

    loanApplicaton: EnquiryApplicationModel;

    /**
     * constructor()
     * @param _service: EvaluationService
     * @param _dialog: MatDialog
     * @param _loanEnquiryService: LoanEnquiryService
     */
    constructor(public _service: EvaluationService, private _dialog: MatDialog, private _loanEnquiryService: LoanEnquiryService,
        @Inject(DOCUMENT) private document: any, private _router: Router) {

        //
        _service.selectedEvaluation = undefined;

        //
        this.loanApplicaton = _loanEnquiryService.selectedLoanApplicaton;
    }

    /**
     * 
     */
    newEvaluation(): void {
        // this._router.navigate(['/riskModelTemplate']);
        const dialogRef = this._dialog.open(NewEvaluationDialogComponent, {
            panelClass: 'new-evaluation-dialog',
            data: {
                'loanApplication': this.loanApplicaton
            },
            width: '500px'
        });
    }

    /**
     * displayAsPDF()
     */
    displayAsPDF(): void {
        console.log(this.document);
        // this._service.fetchModelPDF(this._service.selectedEvaluation.value).subscribe();
        // this.document.location.href = 'api/riskModelPDF?id=' + this._service.selectedEvaluation.value.id;
        (window as any).open('api/riskModelPDF?id=' + this._service.selectedEvaluation.value.id, '_blank');
    }

    /**
     * editEvaluation()
     */
    editEvaluation(): void {
        this._router.navigate(['riskModelTemplate/edit/' + this._service.selectedEvaluation.value.id]);
    }
}

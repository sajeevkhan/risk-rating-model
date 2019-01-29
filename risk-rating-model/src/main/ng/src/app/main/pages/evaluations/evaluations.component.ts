import { Component } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { Router, ActivatedRoute } from '@angular/router';
import { EvaluationService } from './evaluations.service';
import { MatDialog } from '@angular/material';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

@Component({
    selector: 'fuse-evaluations',
    templateUrl: './evaluations.component.html',
    styleUrls: ['./evaluations.component.scss'],
    animations: fuseAnimations
})
export class EvaluationComponent {

    evaluationList: any;

    projectId: string;

    loanApplicaton: EnquiryApplicationModel;

    constructor(public _service: EvaluationService, private _route: ActivatedRoute, private _router: Router,
        private _dialog: MatDialog, private _loanEnquiryService: LoanEnquiryService) {

        //
        _service.selectedEvaluation = undefined;

        //
        _route.params.subscribe(params => {
            this.projectId = params['projectId'];
            _service.fetchEvaluations(this.projectId).subscribe(response => {
                this.evaluationList = response;
                console.log(response);
            });
        });

        this.loanApplicaton = _loanEnquiryService.selectedLoanApplicaton;
    }

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
}

import { Component } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { Router, ActivatedRoute } from '@angular/router';
import { EvaluationService } from './evaluations.service';
import { MatDialog } from '@angular/material';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';

@Component({
    selector: 'fuse-evaluations',
    templateUrl: './evaluations.component.html',
    styleUrls: ['./evaluations.component.scss'],
    animations: fuseAnimations
})
export class EvaluationComponent {

    evaluationList: any;

    projectId: string;

    constructor(public _service: EvaluationService, private _route: ActivatedRoute, private _router: Router,
        private _dialog: MatDialog) {

        //
        _service.selectedEvaluation = undefined;

        //
        _route.params.subscribe(params => {
            this.projectId = params['projectId'];
            _service.fetchEvaluations(this.projectId).subscribe(response => {
                console.log(response);
            });
        });
    }

    newEvaluation(): void {
        // this._router.navigate(['/riskModelTemplate']);
        const dialogRef = this._dialog.open(NewEvaluationDialogComponent, {
            panelClass: 'new-evaluation-dialog',
            data: {
                projectId: this.projectId
            },
            width: '500px'
        });
    }
}

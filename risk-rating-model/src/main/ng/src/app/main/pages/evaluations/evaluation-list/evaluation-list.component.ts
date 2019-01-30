import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { ActivatedRoute } from '@angular/router';
import { EvaluationService } from '../evaluations.service';
import { BehaviorSubject } from 'rxjs';

@Component({
    selector: 'app-evaluation-list',
    templateUrl: './evaluation-list.component.html',
    styleUrls: ['./evaluation-list.component.scss'],
    animations: fuseAnimations
})
export class EvaluationListComponent implements OnInit {
    
    evaluations: any;

    selectedEvaluation: any;

    displayedColumns = [
        'ratingDate', 'workflowStatusDescription', 'projectRiskLevelDescription', 'purposeDescription', 'overallProjectGrade', 
        'modifiedProjectGrade', 'afterParentalNotchUpGrade', 'finalProjectGrade'
    ];

    constructor(private _service: EvaluationService, _route: ActivatedRoute) { 
        // Fetch evaluations from route resolved data.
        _route.data.subscribe((data) => {
            this.evaluations = data.routeResolvedData;
            console.log('constructor', this.evaluations);
        });
    }

    ngOnInit(): void {
    }

    onSelect(evaluation: any): void {
        this.selectedEvaluation = evaluation;
        this._service.selectedEvaluation = new BehaviorSubject(evaluation);
    }
}

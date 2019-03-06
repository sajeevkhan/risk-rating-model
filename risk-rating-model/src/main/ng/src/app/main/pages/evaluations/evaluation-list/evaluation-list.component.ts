import { Component, OnInit, Input, OnChanges, ViewChild } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { ActivatedRoute } from '@angular/router';
import { EvaluationService } from '../evaluations.service';
import { BehaviorSubject } from 'rxjs';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
    selector: 'app-evaluation-list',
    templateUrl: './evaluation-list.component.html',
    styleUrls: ['./evaluation-list.component.scss'],
    animations: fuseAnimations
})
export class EvaluationListComponent implements OnInit {
    
    dataSource: MatTableDataSource<any>;

    @ViewChild(MatPaginator) paginator: MatPaginator;

    @Input()
    set evaluations(evaluations: any) {
        this.dataSource = new MatTableDataSource(evaluations);
        this.dataSource.paginator = this.paginator;
    }

    pageSizeOptions: number[] = [10, 25, 50, 100];

    selectedEvaluation: any;

    displayedColumns = [
        'id', 'ratingDate', 'workflowStatusDescription', 'projectRiskLevelDescription', 'purposeDescription', 'overallProjectGrade', 
        'modifiedProjectGrade', 'afterParentalNotchUpGrade', 'finalProjectGrade'
    ];

    constructor(private _service: EvaluationService, private _route: ActivatedRoute) { 
        // Fetch evaluations from route resolved data.
        // _route.data.subscribe((data) => {
        //     this.evaluations = new MatTableDataSource(data.routeResolvedData);
        //     this.evaluations.paginator = this.paginator;
        // });
    }

    ngOnInit(): void {
    }

    onSelect(evaluation: any): void {
        this.selectedEvaluation = evaluation;
        this._service.selectedEvaluation = new BehaviorSubject(evaluation);
    }
}

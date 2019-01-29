import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { BehaviorSubject } from 'rxjs';
import { EvaluationService } from '../evaluations.service';

@Component({
    selector: 'fuse-enquiry-search-list',
    templateUrl: './enquirySearchList.component.html',
    styleUrls: ['./enquirySearchList.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set enquiryList(evaluations: any) {
        this.dataSource = new MatTableDataSource(evaluations);
        this.dataSource.sort = this.sort;
    }

    displayedColumns = [
        'functionalStatusDescription'
    ];

    selectedEvaluation: any;

    /**
     * constructor()
     */
    constructor(private _service: EvaluationService) {
    }
    
    /**
     * ngOnInit()
     */
    ngOnInit(): void {
        /**
         * this.sort will not be initialized in the constructor phase. It will be undefined and hence sorting
         * will not work. The below line has to be in ngOnInit() which is executed after all initializations.
         */
        this.dataSource.sort = this.sort;
    }

    /**
     * 
     * @param enquiry: any
     */
    onSelect(evaluation: any): void {
        this.selectedEvaluation = evaluation;
        this._service.selectedEvaluation = new BehaviorSubject(evaluation.id);
    }
}

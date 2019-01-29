import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-evaluation-list',
    templateUrl: './evaluation-list.component.html',
    styleUrls: ['./evaluation-list.component.scss']
})
export class EvaluationListComponent implements OnInit {

    @Input() evaluations: any;

    displayedColumns = [
        'projectPhase', 'projectGrade', 'modifiedGrade'
    ];

    constructor() { }

    ngOnInit(): void {
    }
}

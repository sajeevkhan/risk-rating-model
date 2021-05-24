import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {RiskReportModel} from "../../model/riskReport.model";
import {MatPaginator, MatSnackBar, MatSort, MatTableDataSource} from "@angular/material";
import {BehaviorSubject} from "rxjs/Rx";
import {fuseAnimations } from '@fuse/animations';
import {animate, state, style, transition, trigger} from '@angular/animations';

import {RiskReportService} from 'risk-report.service';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-risk-report',
  templateUrl: './risk-report.component.html',
  styleUrls: ['./risk-report.component.scss'],
    animations: [
        fuseAnimations,
        trigger('detailExpand', [
            state('collapsed', style({height: '0px', minHeight: '0'})),
            state('expanded', style({height: '*'})),
            transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
        ]),
    ],
})
export class RiskReportComponent implements OnInit {

    dataSource: MatTableDataSource<any>;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set riskReportList(riskReportList: RiskReportModel[]) {
        this.dataSource = new MatTableDataSource(riskReportList);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

    }


    displayedColumns = [
        'loanNumber', 'projectName', 'projectType', 'projectPhase','initiatingDepartment', 'loanContractAmount', 'totalLoanDisbursedAmount' , 'initiator', 'createDate', 'processDate', 'finalRating'
    ];
    columnsToDisplay = [ 'loanNumber', 'projectName', 'projectType', 'projectPhase','initiatingDepartment', 'loanContractAmount', 'totalLoanDisbursedAmount' , 'initiator', 'createDate', 'processDate', 'finalRating'];



    selectedRiskReportItem: RiskReportModel;

    riskReportSearchForm: FormGroup;

    expandPanel = true;

    pageSizeOptions: number[] = [10, 25, 50, 100];

    constructor(private _service: RiskReportService,_formBuilder: FormBuilder, private _router: Router,private _matSnackBar: MatSnackBar) {
        this.riskReportSearchForm = _formBuilder.group({
            loanNumber: [],
            projectName: [],
            projectPhase: []

        });
        _service.selectedRiskReportItemId = undefined;
    }

    ngOnInit() {
    }

    /*
        Fetch Risk Report
     */
    fetchRiskReport():void{
        const searchForm = this.riskReportSearchForm.value;

        let searchParameters: Array<string> = [ searchForm.loanNumber,
                                                searchForm.projectName,
                                                searchForm.projectPhase];


        this._service.getRiskReport(searchParameters).subscribe((result) => {
            const riskReports = new Array<RiskReportModel>();
            if (result.length == 0 ){
                this._matSnackBar.open('No risk evaluations found', 'OK', {duration: 2000});
                return;
            }

            result.map(riskReportResourceModel => {
                // console.log("RESULT:" + riskReportResourceModel);
                // console.log("Risk Report RESOUCRCE:" + riskReportResourceModel);

                riskReports.push(new RiskReportModel(riskReportResourceModel));
            });
            console.log("Risk Report Item" + riskReports.values());
            this.riskReportList = riskReports;
            this.expandPanel = true;
        });
    }



}

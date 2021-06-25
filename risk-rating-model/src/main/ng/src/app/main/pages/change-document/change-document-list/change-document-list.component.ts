import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { PageEvent, MatTableDataSource, MatSort } from '@angular/material';
import { ChangeDocumentService } from '../change-document.service';
import {
	ResizeEvent
} from 'angular-resizable-element';

@Component({
    selector: 'app-change-document-list',
    templateUrl: './change-document-list.component.html',
    styleUrls: ['./change-document-list.component.scss'],
    animations: fuseAnimations
})
export class ChangeDocumentListComponent implements OnInit {

    changeDocumentItems: any;

    @ViewChild(MatSort) sort1: MatSort;

    onResizeEnd(event: ResizeEvent, columnName): void {
		if (event.edges.right) {
			const cssValue = event.rectangle.width + 'px';
			const columnElts = document.getElementsByClassName('mat-column-' + columnName);
			for (let i = 0; i < columnElts.length; i++) {
				const currentEl = columnElts[i] as HTMLDivElement;
				currentEl.style.width = cssValue;
			}
		}
	}

    dataSource1: MatTableDataSource<any>;

    searchParameters: any;

    @Input()
    set changeDocuments(changeDocuments: any) {
        this.dataSource1 = new MatTableDataSource(changeDocuments);
        this.dataSource1.sort = this.sort1;
    }

    @Input()
    page: any = {
        totalElements: 0
    };

    /**
     * Capture changes in the search parameters
     */
    @Input()
    set formValues(formValues: any) {
        this.searchParameters = formValues;
    }

    changeDocumentColumns: string[] = [
        'date', 'riskModelTemplateId', 'userName', 'loanNumber', 'action'
    ];

    pageSizeOptions: number[] = [10, 25, 50, 100];

    itemColumns: string[] = [
        'itemNo', 'entityDescription', 'entityName', 'attributeName', 'tableKey', 'oldValue', 'newValue'
    ];

    selectedDocument: any;
    selectedItem: any;

    constructor(private _service: ChangeDocumentService) { 
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
        this.dataSource1 = new MatTableDataSource(this.changeDocuments);
        this.dataSource1.sort = this.sort1;
    }

    /**
     * onDocumentSelect()
     * @param changeDocument: any
     */
    onDocumentSelect(changeDocument: any): void {
        this.selectedDocument = changeDocument;
        this.changeDocumentItems = this.selectedDocument.changeDocumentItems;
    }

    /**
     * onItemSelect()
     * @param documentItem: any
     */
    onItemSelect(documentItem: any): void {
        this.selectedItem = documentItem;
    }

    /**
     * pageOptionsChanged()
     * @param event: PageEvent
     */
    pageOptionsChanged(event: PageEvent): void {
        this._service.fetchChangeDocuments(this.searchParameters.loanNumber,
            this.searchParameters.riskModelId,
            this.searchParameters.dateFrom,
            this.searchParameters.dateTo,
            event.pageIndex, event.pageSize).subscribe(data => {
                //
                this.dataSource1 = data.content;
                this.page = data.pageable;
                this.page.totalElements = data.totalElements;
                this.selectedDocument = undefined;
            });
    }
}

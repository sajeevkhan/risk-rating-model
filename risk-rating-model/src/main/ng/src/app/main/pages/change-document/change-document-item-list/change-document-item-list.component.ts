import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { PageEvent, MatTableDataSource, MatSort } from '@angular/material';
import { ChangeDocumentService } from '../change-document.service';
import {
	ResizeEvent
} from 'angular-resizable-element';

@Component({
    selector: 'app-change-document-item-list',
    templateUrl: './change-document-item-list.component.html',
    styleUrls: ['./change-document-item-list.component.scss'],
    animations: fuseAnimations
})
export class ChangeDocumentItemListComponent implements OnInit {

    @ViewChild(MatSort) sort2: MatSort;

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

    dataSource2: MatTableDataSource<any>;

    searchParameters: any;

    @Input()
    set changeDocumentItems(changeDocumentItems: any) {
        console.log('changeDocumentItems', changeDocumentItems);
        this.dataSource2 = new MatTableDataSource(changeDocumentItems);
        this.dataSource2.sort = this.sort2;
    }

    itemColumns: string[] = [
        'itemNo', 'entityDescription', 'entityName', 'attributeName', 'tableKey', 'oldValue', 'newValue'
    ];

    selectedItem: any;

    constructor(private _service: ChangeDocumentService) { 
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
        // this.dataSource2 = new MatTableDataSource([]);
        // this.dataSource2.sort = this.sort2;
    }

    /**
     * onItemSelect()
     * @param documentItem: any
     */
    onItemSelect(documentItem: any): void {
        this.selectedItem = documentItem;
    }
}

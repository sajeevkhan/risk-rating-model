import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { ChangeDocumentService } from './change-document.service';

@Component({
    selector: 'app-change-document',
    templateUrl: './change-document.component.html',
    styleUrls: ['./change-document.component.scss']
})
export class ChangeDocumentComponent implements OnInit {

    changeDocumentsForm: FormGroup;

    changeDocuments: any;
    page: any;

    constructor(_formBuilder: FormBuilder, private _service: ChangeDocumentService) { 
        this.changeDocumentsForm = _formBuilder.group({
            loanNumber: new FormControl(),
            riskModelId: new FormControl(),
            dateFrom: new FormControl({disabled: true}),
            dateTo: new FormControl({disabled: true})
        });
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    /**
     * searchChangeDocuments()
     */
    searchChangeDocuments(): void {
        console.log(this.changeDocumentsForm.value);
        const formValue = this.changeDocumentsForm.value;
        this._service.fetchChangeDocuments(formValue.loanNumber, formValue.riskModelId, formValue.dateFrom, formValue.dateTo, 0, 5).subscribe(data => {
            this.changeDocuments = data.content;
            this.page = data.pageable;
            this.page.totalElements = data.totalElements;
        });
    }
}

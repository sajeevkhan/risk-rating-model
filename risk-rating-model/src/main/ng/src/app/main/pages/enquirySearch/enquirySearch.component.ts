import { Component } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router } from '@angular/router';
import { LoanEnquiryService } from './enquiryApplication.service';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchComponent {

    enquirySearchForm: FormGroup;

    enquiryList: EnquiryApplicationModel[];

    constructor(_formBuilder: FormBuilder, public _service: LoanEnquiryService, private _router: Router) {

        this.enquirySearchForm = _formBuilder.group({
            enquiryNoFrom: [],
            enquiryNoTo: [],
            enquiryDateFrom: [],
            enquiryDateTo: [],
            projectName: [],
            projectLocation: [],
            loanClass: [],
            projectType: [],
            financingType: [],
            assistanceType: []
        });

        _service.selectedLoanApplicationId = undefined;
    }

    /**
     * searchEnquiries()
     */
    searchEnquiries(): void {
        this._service.searchLoanEnquiries(this.enquirySearchForm.value).subscribe((result) => {
            const enquiryApplications = new Array<EnquiryApplicationModel>();
            result.body.map(loanApplicationResourceModel => {
                enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
            });
            this.enquiryList = enquiryApplications;
        });
    }

    /**
     * 
     */
    fetchEvaluations(): void {
        this._router.navigate(['/evaluations', this._service.selectedLoanApplicationId.value]);
    }
}

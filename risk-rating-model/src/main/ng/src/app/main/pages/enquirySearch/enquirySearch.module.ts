import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule,MatIconModule, MatSortModule } from '@angular/material';
import { EnquirySearchListComponent } from './enquirySearchList/enquirySearchList.component';
import { EnquirySearchComponent } from './enquirySearch.component';
import { LoanEnquiryService } from './enquiryApplication.service';

const routes = [
    {
        path: 'projects',
        component: EnquirySearchComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatExpansionModule,
        MatInputModule,
        MatButtonModule,
        MatFormFieldModule,
        MatTableModule,
		MatIconModule,
        MatSortModule
    ],
    declarations: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ],
    providers: [
        LoanEnquiryService
    ],
    exports: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ]
})
export class EnquirySearchModule { 
}

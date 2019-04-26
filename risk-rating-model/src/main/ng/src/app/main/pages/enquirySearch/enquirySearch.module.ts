import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule,MatIconModule, MatSortModule, MatPaginatorModule, MatToolbarModule, MatSelectModule, MatProgressSpinnerModule } from '@angular/material';
import { EnquirySearchListComponent } from './enquirySearchList/enquirySearchList.component';
import { EnquirySearchComponent } from './enquirySearch.component';
import { LoanEnquiryService } from './enquiryApplication.service';
import { AssignProcessorsDialogComponent } from './assign-processors-dialog/assign-processors-dialog.component';

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
        MatPaginatorModule,
        MatTableModule,
        MatToolbarModule,
        MatIconModule,
        MatSelectModule,
        MatSortModule,
        MatProgressSpinnerModule
    ],
    declarations: [
        EnquirySearchComponent,
        EnquirySearchListComponent,
        AssignProcessorsDialogComponent
    ],
    providers: [
        LoanEnquiryService
    ],
    exports: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ],
    entryComponents: [
        AssignProcessorsDialogComponent
    ]
})
export class EnquirySearchModule { 
}

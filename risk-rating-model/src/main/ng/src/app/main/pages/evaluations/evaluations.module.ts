import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule, MatDialogModule, MatToolbarModule, MatIconModule, MatSelectModule, MatPaginatorModule } from '@angular/material';
import { EvaluationComponent } from './evaluations.component';
import { EvaluationService } from './evaluations.service';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';
import { EvaluationListComponent } from './evaluation-list/evaluation-list.component';
import { LoanEnquiryService } from '../enquirySearch/enquiryApplication.service';

const routes = [
    {
        path: 'evaluations/:projectId',
        component: EvaluationComponent,
        resolve: {
            routeResolvedData: EvaluationService
        }
    }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        FuseSharedModule,
        MatDialogModule,
        MatExpansionModule,
        MatIconModule,
        MatInputModule,
        MatButtonModule,
        MatFormFieldModule,
        MatPaginatorModule,
        MatTableModule,
        MatToolbarModule,
        MatSelectModule,
        MatSortModule
    ],
    declarations: [
        EvaluationComponent,
        NewEvaluationDialogComponent,
        EvaluationListComponent
    ],
    providers: [
        EvaluationService,
        LoanEnquiryService
    ],
    entryComponents: [
        NewEvaluationDialogComponent
    ]
})
export class EvaluationModule {
}

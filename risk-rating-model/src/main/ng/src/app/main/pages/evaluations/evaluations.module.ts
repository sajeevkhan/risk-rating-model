import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule, MatDialogModule, MatToolbarModule, MatIconModule, MatSelectModule } from '@angular/material';
import { EvaluationComponent } from './evaluations.component';
import { EvaluationService } from './evaluations.service';
import { NewEvaluationDialogComponent } from '../new-evaluation-dialog/new-evaluation-dialog.component';

const routes = [
    {
        path: 'evaluations/:projectId',
        component: EvaluationComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatDialogModule,
        MatExpansionModule,
        MatIconModule,
        MatInputModule,
        MatButtonModule,
        MatFormFieldModule,
        MatTableModule,
        MatToolbarModule,
        MatSelectModule,
        MatSortModule
    ],
    declarations: [
        EvaluationComponent,
        NewEvaluationDialogComponent
    ],
    providers: [
        EvaluationService
    ],
    entryComponents: [
        NewEvaluationDialogComponent
    ]
})
export class EvaluationModule {
}

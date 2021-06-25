import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangeDocumentListComponent } from './change-document-list/change-document-list.component';
import { ChangeDocumentComponent } from './change-document.component';
import { ChangeDocumentService } from './change-document.service';
import { RouterModule } from '@angular/router';
import { MatExpansionModule, MatInputModule, MatIconModule, MatButtonModule, MatTableModule, MatPaginatorModule, MatDatepickerModule, MAT_DATE_LOCALE, MatSortModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';
import { ResizableModule } from 'angular-resizable-element';
import { ChangeDocumentItemListComponent } from './change-document-item-list/change-document-item-list.component';

const routes = [
    {
        path: 'changeDocuments',
        component: ChangeDocumentComponent
    }
];

@NgModule({
    imports: [
        CommonModule,
        FuseSharedModule,
        MatButtonModule,
        MatDatepickerModule,
        MatExpansionModule,
        MatIconModule,
        MatInputModule,
        MatTableModule,
        MatSortModule,
        MatPaginatorModule,
        RouterModule.forChild(routes),
        ResizableModule
    ],
    declarations: [
        ChangeDocumentComponent,
        ChangeDocumentListComponent,
        ChangeDocumentItemListComponent
    ],
    providers: [
        ChangeDocumentService,
        {
            provide: MAT_DATE_LOCALE, useValue: 'en-GB'
        }
    ]
})
export class ChangeDocumentModule {
}

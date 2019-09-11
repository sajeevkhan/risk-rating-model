import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './inbox.component';
import { RouterModule } from '@angular/router';
import { InboxService } from './inbox.service';
import { MatButtonModule, MatTableModule,MatIconModule, MatProgressSpinnerModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';
import { InboxItemsComponent } from './inbox-items/inbox-items.component';

import {  MatSnackBar, MatSnackBarModule } from '@angular/material';
import { AuthenticationGuard } from 'app/authentication.guard';


const routes = [
    {
        path: 'inbox',
        component: InboxComponent,
        resolve: {
            routeResolvedData: InboxService
        },
        canActivate: [ 
            AuthenticationGuard
        ]
    }
];


@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        FuseSharedModule,
        MatButtonModule,
        MatTableModule,
		MatIconModule,
        MatSnackBarModule,
        MatProgressSpinnerModule
    ],
    declarations: [
        InboxComponent,
        InboxItemsComponent
    ]
})
export class InboxModule {
}

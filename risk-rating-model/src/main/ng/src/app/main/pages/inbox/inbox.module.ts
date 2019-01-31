import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './inbox.component';
import { RouterModule } from '@angular/router';
import { InboxService } from './inbox.service';
import { MatButtonModule, MatTableModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';
import { InboxItemsComponent } from './inbox-items/inbox-items.component';

const routes = [
    {
        path: 'inbox',
        component: InboxComponent,
        resolve: {
            routeResolvedData: InboxService
        }
    }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        FuseSharedModule,
        MatButtonModule,
        MatTableModule
    ],
    declarations: [
        InboxComponent,
        InboxItemsComponent
    ]
})
export class InboxModule {
}

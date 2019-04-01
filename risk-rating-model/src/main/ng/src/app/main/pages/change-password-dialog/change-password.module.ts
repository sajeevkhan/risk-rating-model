import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangePasswordDialogComponent } from './change-password-dialog.component';
import { MatIconModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatToolbarModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';

@NgModule({
    imports: [
        CommonModule,
        FuseSharedModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatToolbarModule,
        
    ],
    declarations: [
        ChangePasswordDialogComponent
    ],
    entryComponents: [
        ChangePasswordDialogComponent
    ]
})
export class ChangePasswordModule { 
}

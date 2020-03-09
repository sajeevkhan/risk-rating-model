import { Component, ViewEncapsulation, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EvaluationService } from '../evaluations/evaluations.service';
import { Router } from '@angular/router';
import { AppService } from 'app/app.service';
import { ChangePasswordService } from './change-password.service';

@Component({
    selector: 'app-change-password-dialog-componenet',
    templateUrl: './change-password-dialog.component.html',
    styleUrls: ['./change-password-dialog.component.scss'],
    encapsulation: ViewEncapsulation.None,
})
export class ChangePasswordDialogComponent {

    /**
     * Reactive form on the dialog.
     */
    changePasswordForm: FormGroup;

    constructor(public _dialogRef: MatDialogRef<ChangePasswordDialogComponent>, _formBuilder: FormBuilder, 
        private _appService: AppService, private _passwordService: ChangePasswordService, private _matSnackBar: MatSnackBar) {

        // Initialize the form.
        this.changePasswordForm = _formBuilder.group({
            newPassword: [''],
            confirmPassword: ['']
        });
    }

    /**
     * changePassword()
     */
    changePassword() {
        //console.log(this._appService.userDetails);
        const userResource = {
            'firstName': this._appService.userDetails.firstName,
            'lastName': this._appService.userDetails.lastName,
            'email': this._appService.userDetails.email,
            'mobile': '',
            'password': this.changePasswordForm.value.newPassword
        }
        this._matSnackBar.open('Attempting to change password.', 'Ok', { duration: 10000 });
        this._passwordService.changePassword(userResource).subscribe(() => {
            this._matSnackBar.open('Password changed successfully.', 'Ok', { duration: 7000 });
            this._dialogRef.close();
        });
    }
}

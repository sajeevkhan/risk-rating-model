import { Component, OnInit, ViewChild } from '@angular/core';
import { InboxService } from './inbox.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material';
import { Router } from '@angular/router';
import { InboxItemsComponent } from './inbox-items/inbox-items.component';


@Component({
    selector: 'app-inbox',
    templateUrl: './inbox.component.html',
    styleUrls: ['./inbox.component.scss']
})
export class InboxComponent implements OnInit {

    @ViewChild(InboxItemsComponent) inboxItemsComponent: InboxItemsComponent;

    savingTemplate: boolean;

    constructor(private _inboxService: InboxService,
        private _matSnackBar: MatSnackBar,
        private _router: Router) {
    }

    ngOnInit(): void {
    }

    /**
     * reviewEvaluation()
     */
    reviewEvaluation(): void {
        this._router.navigate(['riskModelTemplate/edit/' + this._inboxService.selectedItem.value.riskModelId]);
    }

    /**
     * rejectEvalaution()
     */
    rejectEvaluation(): void {
        console.log('REJECT SELECTED INBOX ITEM : ' + this._inboxService.selectedItem.value.riskModelId);
        this.savingTemplate = true;
        const id = this._inboxService.selectedItem.value.riskModelId;

        this._inboxService.rejectEvaluation(id).subscribe(response => {
            // Refresh the inbox and display an alert.
            this.inboxItemsComponent.refreshInboxItems();
            this._matSnackBar.open('Risk model is rejected and email notification was sent to requestor', 'Ok', { duration: 7000 });
            this.savingTemplate = false;
        });
    }

    /**
     * approveEvaluation()
     */
    approveEvaluation(): void {
        console.log('APPROVE SELECTED INBOX ITEM : ' + this._inboxService.selectedItem.value.riskModelId);
        this.savingTemplate = true
        const id = this._inboxService.selectedItem.value.riskModelId;

        this._inboxService.approveEvaluation(id).subscribe(response => {
            // Refresh the inbox and display an alert.
            this.inboxItemsComponent.refreshInboxItems();
            this._matSnackBar.open('Risk model is approved and email notification was sent to requestor', 'Ok', { duration: 7000 });
            this.savingTemplate = false;
        });
    }

    /**
     * 
     */
    displayAsPDF(): void {
        this.inboxItemsComponent.refreshInboxItems();
    }
}

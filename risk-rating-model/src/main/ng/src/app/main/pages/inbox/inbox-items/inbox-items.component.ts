import { Component, OnInit } from '@angular/core';
import { InboxService } from '../inbox.service';
import { ActivatedRoute } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';

@Component({
    selector: 'app-inbox-items',
    templateUrl: './inbox-items.component.html',
    styleUrls: ['./inbox-items.component.scss'],
    animations: fuseAnimations
})
export class InboxItemsComponent implements OnInit {

    inboxItems: any;

    displayedColumns = [
        'dateAsString', 'projectType', 'riskLevel', 'projectName', 'riskModelId', 'requestedBy'
    ];

    /**
     * 
     * @param _service: InboxService
     * @param _route: ActivatedRoute
     */
    constructor(private _service: InboxService, _route: ActivatedRoute) {
        // Fetch evaluations from route resolved data.
        _route.data.subscribe((data) => {
            this.inboxItems = data.routeResolvedData;
            console.log('inboxItems', this.inboxItems);
        });
    }

    /**
     * 
     */
    ngOnInit(): void {
    }
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatButtonModule, MatIconModule } from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import 'hammerjs';

import { FuseModule } from '@fuse/fuse.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from '@fuse/components';

import { fuseConfig } from 'app/fuse-config';

import { AppComponent } from 'app/app.component';
import { LayoutModule } from 'app/layout/layout.module';
import { RiskModelLandingModule } from './main/risk-model-landing/risk-model-landing.module';
import { RiskModelUIModule } from './main/pages/risk-model-ui/risk-model-ui.module';
import { EnquirySearchModule } from './main/pages/enquirySearch/enquirySearch.module';
import { EvaluationModule } from './main/pages/evaluations/evaluations.module';
import { InboxModule } from './main/pages/inbox/inbox.module';
import { AppService } from './app.service';
import { ChangeDocumentModule } from './main/pages/change-document/change-document.module';

const appRoutes: Routes = [
    {
        path      : '**',
        redirectTo: 'inbox'
    }
];

@NgModule({
    declarations: [
        AppComponent
    ],
    imports     : [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        RouterModule.forRoot(appRoutes),

        TranslateModule.forRoot(),

        // Material moment date module
        MatMomentDateModule,

        // Material
        MatButtonModule,
        MatIconModule,

        // Fuse modules
        FuseModule.forRoot(fuseConfig),
        FuseProgressBarModule,
        FuseSharedModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,

        // App modules
        LayoutModule,
        ChangeDocumentModule,
        EnquirySearchModule,
        EvaluationModule,
        InboxModule,
        RiskModelUIModule,
        RiskModelLandingModule,
    ],
    bootstrap   : [
        AppComponent
    ],
    providers: [
        AppService
    ]
})
export class AppModule
{
}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskReportComponent } from './risk-report.component';
import {RouterModule} from "@angular/router";
import {
    MatButtonModule, MatCheckboxModule, MatDialogModule, MatExpansionModule, MatFormFieldModule, MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule, MatToolbarModule
} from "@angular/material";
import {FuseSharedModule} from "../../../../@fuse/shared.module";
import {AuthenticationGuard} from "../../../authentication.guard";
import {CdkTableModule} from "@angular/cdk/table";
import {TranslateModule} from "@ngx-translate/core";



const routes = [
    {
        path: 'riskReport',
        component: RiskReportComponent,
        canActivate: [
            AuthenticationGuard
        ]
    }
];


@NgModule({
  imports: [
    CommonModule,
      RouterModule.forChild(routes),
      TranslateModule,
      FuseSharedModule,
      CommonModule,
      MatButtonModule,
      MatCheckboxModule,
      MatFormFieldModule,
      MatIconModule,
      MatToolbarModule,
      MatDialogModule,
      MatInputModule,
      MatSelectModule,
      MatTableModule,
      CdkTableModule,
      FuseSharedModule,
      MatExpansionModule,
      MatSortModule,
      MatPaginatorModule

  ],
  declarations: [RiskReportComponent]
})
export class RiskReportModule { }



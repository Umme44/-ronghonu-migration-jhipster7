import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';

import { CertificateDetail } from '../detail/detail.component';
import { CertificateUpdate } from '../update/update.component';
import { CertificateDelete } from '../delete/delete.component';
//import { CertificateRoutingResolveService } from '../route/certificate-routing-resolve.service';


const certificateRoute: Routes = [



];

@NgModule({
  imports: [RouterModule.forChild(certificateRoute)],
  exports: [RouterModule],
})
export class CertificateRoutingModule {}

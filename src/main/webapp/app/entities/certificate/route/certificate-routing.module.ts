import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';

import { CertificateComponent } from '../list/list.component';
import { CertificateDetail } from '../detail/detail.component';
import { CertificateUpdate } from '../update/update.component';
//import { CertificateRoutingResolveService } from '../route/certificate-routing-resolve.service';

const certificateRoute: Routes = [
  {
    path: '',
    component: CertificateComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CertificateDetail,
    resolve: {
      //certificate: CertificateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CertificateUpdate,
    resolve: {
      //certificate: CertificateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CertificateUpdate,
    resolve: {
      ///certificate: CertificateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/delete',  // Route for deleting a certificate
    component: CertificateDetail, // You may want to show the same detail component to confirm deletion
    resolve: {
      //certificate: CertificateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },

  {
    path: ':id/view',
    component: CertificateDetail,
  },
];

@NgModule({
  imports: [RouterModule.forChild(certificateRoute)],
  exports: [RouterModule],
})
export class CertificateRoutingModule {}

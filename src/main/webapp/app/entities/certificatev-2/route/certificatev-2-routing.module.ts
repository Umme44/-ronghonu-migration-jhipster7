import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { Certificatev2Component } from '../list/certificatev-2.component';
import { Certificatev2DetailComponent } from '../detail/certificatev-2-detail.component';
import { Certificatev2UpdateComponent } from '../update/certificatev-2-update.component';
import { Certificatev2RoutingResolveService } from './certificatev-2-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const certificatev2Route: Routes = [
  {
    path: '',
    component: Certificatev2Component,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Certificatev2DetailComponent,
    resolve: {
      certificatev2: Certificatev2RoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Certificatev2UpdateComponent,
    resolve: {
      certificatev2: Certificatev2RoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Certificatev2UpdateComponent,
    resolve: {
      certificatev2: Certificatev2RoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(certificatev2Route)],
  exports: [RouterModule],
})
export class Certificatev2RoutingModule {}

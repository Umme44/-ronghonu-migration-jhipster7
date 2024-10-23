import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';



import { ListComponent } from '../list/list.component';
import { DeleteComponent } from '../delete/delete.component';
import { UpdateComponent } from '../update/update.component';
import {DetailComponent} from '../detail/detail.component';

//import { CertificateRoutingResolveService } from '../route/certificate-routing-resolve.service';


const certificateRoute: Routes = [

  {
    path: 'new',
    component: ListComponent,
    resolve: {
      //certificateDetails: CertificateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },

  {
    path: 'id/delete',
    component: DeleteComponent,
    resolve: {

    },
    canActivate: [UserRouteAccessService],
  },

  {
    path: 'id/update',
    component: UpdateComponent,
    resolve: {

    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'id/view',
    component: DetailComponent,
    resolve: {

    },
    canActivate: [UserRouteAccessService],
  },





];

@NgModule({
  imports: [RouterModule.forChild(certificateRoute)],
  exports: [RouterModule],
})
export class CertificateRoutingModule {}

import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { Certificatev2Component } from './list/certificatev-2.component';
import { Certificatev2DetailComponent } from './detail/certificatev-2-detail.component';
import { Certificatev2UpdateComponent } from './update/certificatev-2-update.component';
import { Certificatev2DeleteDialogComponent } from './delete/certificatev-2-delete-dialog.component';
import { Certificatev2RoutingModule } from './route/certificatev-2-routing.module';

@NgModule({
  imports: [SharedModule, Certificatev2RoutingModule],
  declarations: [Certificatev2Component, Certificatev2DetailComponent, Certificatev2UpdateComponent, Certificatev2DeleteDialogComponent],
})
export class Certificatev2Module {}

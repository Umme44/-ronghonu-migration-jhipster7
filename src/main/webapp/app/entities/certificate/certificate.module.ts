import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import {BitsHrPayrollHeaderModule} from "../../layouts/header/header.module";
import { ListComponent } from './list/list.component';
import { DeleteComponent } from './delete/delete.component';
import { DetailComponent } from './detail/detail.component';
import { UpdateComponent } from './update/update.component';

//export let CertificateModule = undefined;


@NgModule({
  imports: [SharedModule, BitsHrPayrollHeaderModule],
  declarations: [
    ListComponent,
    DeleteComponent,
    DetailComponent,
    UpdateComponent,
  ],
})
export class CertificateModule {}

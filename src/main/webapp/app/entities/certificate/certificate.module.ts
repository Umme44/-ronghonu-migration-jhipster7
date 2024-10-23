import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import {BitsHrPayrollHeaderModule} from "../../layouts/header/header.module";
import { ListComponent } from "./list/list.component";
import { DeleteComponent } from './delete/delete.component';
import { DetailComponent } from './detail/detail.component';
import { CertificateRoutingModule } from "./route/certificate-routing.module";
import {
  BitsHrPayrollSimpleSelectEmployeeFormModule
} from "../../shared/simple-select-employee/simple-select-employee-form.module";
import { UpdateComponent } from './update/update.component';
import { BitsHrPayrollSelectEmployeeFormModule } from '../../shared/select-employee-form/select-employee-form.module';





@NgModule({
  imports: [SharedModule, BitsHrPayrollHeaderModule, CertificateRoutingModule, BitsHrPayrollSimpleSelectEmployeeFormModule,BitsHrPayrollSelectEmployeeFormModule],
  declarations: [
    DeleteComponent,
    DetailComponent,
    ListComponent,
    UpdateComponent,

  ]
})
export class CertificateModule {}

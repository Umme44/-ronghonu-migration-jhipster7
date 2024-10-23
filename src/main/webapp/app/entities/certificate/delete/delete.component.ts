import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ICertificateDetails } from '../certificate.model';
import { CertificateService } from '../service/certificate.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import {IEducationDetails} from "../../education-details/education-details.model";
import {EducationDetailsService} from "../../education-details/service/education-details.service";

@Component({
  selector: 'jhi-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent implements OnInit {
  certificateDetails?: ICertificateDetails;

  constructor(protected certificateService: CertificateService, protected activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }




}


export class CertificateDelete {
}

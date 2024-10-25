import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICertificatev2 } from '../certificatev-2.model';
import { Certificatev2Service } from '../service/certificatev-2.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './certificatev-2-delete-dialog.component.html',
})
export class Certificatev2DeleteDialogComponent {
  certificatev2?: ICertificatev2;

  constructor(protected certificatev2Service: Certificatev2Service, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.certificatev2Service.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}

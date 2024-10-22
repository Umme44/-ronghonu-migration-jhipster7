import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent implements OnInit {
  certificateDetails: any;

  constructor() { }

  ngOnInit(): void {
  }

  confirmDelete(param: string | ArrayBufferView | ArrayBuffer | number) {

  }

  cancel() {

  }
}

export class CertificateDelete {
}

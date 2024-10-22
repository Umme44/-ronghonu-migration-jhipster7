import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {
  editForm: any;

  constructor() { }

  ngOnInit(): void {
  }

  save() {

  }

  changeCertificate($event: any) {

  }
}

export class CertificateUpdate {
}

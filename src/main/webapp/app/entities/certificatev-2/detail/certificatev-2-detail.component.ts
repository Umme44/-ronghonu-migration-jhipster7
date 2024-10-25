import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICertificatev2 } from '../certificatev-2.model';

@Component({
  selector: 'jhi-certificatev-2-detail',
  templateUrl: './certificatev-2-detail.component.html',
})
export class Certificatev2DetailComponent implements OnInit {
  certificatev2: ICertificatev2 | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificatev2 }) => {
      this.certificatev2 = certificatev2;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

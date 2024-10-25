import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { Certificatev2FormService, Certificatev2FormGroup } from './certificatev-2-form.service';
import { ICertificatev2 } from '../certificatev-2.model';
import { Certificatev2Service } from '../service/certificatev-2.service';

@Component({
  selector: 'jhi-certificatev-2-update',
  templateUrl: './certificatev-2-update.component.html',
})
export class Certificatev2UpdateComponent implements OnInit {
  isSaving = false;
  certificatev2: ICertificatev2 | null = null;

  editForm: Certificatev2FormGroup = this.certificatev2FormService.createCertificatev2FormGroup();

  constructor(
    protected certificatev2Service: Certificatev2Service,
    protected certificatev2FormService: Certificatev2FormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificatev2 }) => {
      this.certificatev2 = certificatev2;
      if (certificatev2) {
        this.updateForm(certificatev2);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const certificatev2 = this.certificatev2FormService.getCertificatev2(this.editForm);
    if (certificatev2.id !== null) {
      this.subscribeToSaveResponse(this.certificatev2Service.update(certificatev2));
    } else {
      this.subscribeToSaveResponse(this.certificatev2Service.create(certificatev2 as any));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertificatev2>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(certificatev2: ICertificatev2): void {
    this.certificatev2 = certificatev2;
    this.certificatev2FormService.resetForm(this.editForm, certificatev2);
  }
}

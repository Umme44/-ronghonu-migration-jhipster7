jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { Certificatev2Service } from '../service/certificatev-2.service';

import { Certificatev2DeleteDialogComponent } from './certificatev-2-delete-dialog.component';

describe('Certificatev2 Management Delete Component', () => {
  let comp: Certificatev2DeleteDialogComponent;
  let fixture: ComponentFixture<Certificatev2DeleteDialogComponent>;
  let service: Certificatev2Service;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [Certificatev2DeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(Certificatev2DeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(Certificatev2DeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(Certificatev2Service);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});

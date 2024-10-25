import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { Certificatev2FormService } from './certificatev-2-form.service';
import { Certificatev2Service } from '../service/certificatev-2.service';
import { ICertificatev2 } from '../certificatev-2.model';

import { Certificatev2UpdateComponent } from './certificatev-2-update.component';

describe('Certificatev2 Management Update Component', () => {
  let comp: Certificatev2UpdateComponent;
  let fixture: ComponentFixture<Certificatev2UpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let certificatev2FormService: Certificatev2FormService;
  let certificatev2Service: Certificatev2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [Certificatev2UpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(Certificatev2UpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(Certificatev2UpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    certificatev2FormService = TestBed.inject(Certificatev2FormService);
    certificatev2Service = TestBed.inject(Certificatev2Service);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const certificatev2: ICertificatev2 = { id: 456 };

      activatedRoute.data = of({ certificatev2 });
      comp.ngOnInit();

      expect(comp.certificatev2).toEqual(certificatev2);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICertificatev2>>();
      const certificatev2 = { id: 123 };
      jest.spyOn(certificatev2FormService, 'getCertificatev2').mockReturnValue(certificatev2);
      jest.spyOn(certificatev2Service, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ certificatev2 });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: certificatev2 }));
      saveSubject.complete();

      // THEN
      expect(certificatev2FormService.getCertificatev2).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(certificatev2Service.update).toHaveBeenCalledWith(expect.objectContaining(certificatev2));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICertificatev2>>();
      const certificatev2 = { id: 123 };
      jest.spyOn(certificatev2FormService, 'getCertificatev2').mockReturnValue({ id: null });
      jest.spyOn(certificatev2Service, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ certificatev2: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: certificatev2 }));
      saveSubject.complete();

      // THEN
      expect(certificatev2FormService.getCertificatev2).toHaveBeenCalled();
      expect(certificatev2Service.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICertificatev2>>();
      const certificatev2 = { id: 123 };
      jest.spyOn(certificatev2Service, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ certificatev2 });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(certificatev2Service.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

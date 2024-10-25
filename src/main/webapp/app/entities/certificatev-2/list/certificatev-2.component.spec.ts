import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { Certificatev2Service } from '../service/certificatev-2.service';

import { Certificatev2Component } from './certificatev-2.component';

describe('Certificatev2 Management Component', () => {
  let comp: Certificatev2Component;
  let fixture: ComponentFixture<Certificatev2Component>;
  let service: Certificatev2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'certificatev-2', component: Certificatev2Component }]), HttpClientTestingModule],
      declarations: [Certificatev2Component],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(Certificatev2Component, '')
      .compileComponents();

    fixture = TestBed.createComponent(Certificatev2Component);
    comp = fixture.componentInstance;
    service = TestBed.inject(Certificatev2Service);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.certificatev2s?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to certificatev2Service', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCertificatev2Identifier');
      const id = comp.trackId(0, entity);
      expect(service.getCertificatev2Identifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});

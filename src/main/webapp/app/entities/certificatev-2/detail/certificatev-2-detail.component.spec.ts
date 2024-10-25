import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Certificatev2DetailComponent } from './certificatev-2-detail.component';

describe('Certificatev2 Management Detail Component', () => {
  let comp: Certificatev2DetailComponent;
  let fixture: ComponentFixture<Certificatev2DetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Certificatev2DetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ certificatev2: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(Certificatev2DetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(Certificatev2DetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load certificatev2 on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.certificatev2).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

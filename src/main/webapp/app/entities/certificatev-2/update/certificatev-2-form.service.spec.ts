import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../certificatev-2.test-samples';

import { Certificatev2FormService } from './certificatev-2-form.service';

describe('Certificatev2 Form Service', () => {
  let service: Certificatev2FormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Certificatev2FormService);
  });

  describe('Service methods', () => {
    describe('createCertificatev2FormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCertificatev2FormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            pin: expect.any(Object),
          })
        );
      });

      it('passing ICertificatev2 should create a new form with FormGroup', () => {
        const formGroup = service.createCertificatev2FormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            pin: expect.any(Object),
          })
        );
      });
    });

    describe('getCertificatev2', () => {
      it('should return NewCertificatev2 for default Certificatev2 initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCertificatev2FormGroup(sampleWithNewData);

        const certificatev2 = service.getCertificatev2(formGroup) as any;

        expect(certificatev2).toMatchObject(sampleWithNewData);
      });

      it('should return NewCertificatev2 for empty Certificatev2 initial value', () => {
        const formGroup = service.createCertificatev2FormGroup();

        const certificatev2 = service.getCertificatev2(formGroup) as any;

        expect(certificatev2).toMatchObject({});
      });

      it('should return ICertificatev2', () => {
        const formGroup = service.createCertificatev2FormGroup(sampleWithRequiredData);

        const certificatev2 = service.getCertificatev2(formGroup) as any;

        expect(certificatev2).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICertificatev2 should not enable id FormControl', () => {
        const formGroup = service.createCertificatev2FormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCertificatev2 should disable id FormControl', () => {
        const formGroup = service.createCertificatev2FormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

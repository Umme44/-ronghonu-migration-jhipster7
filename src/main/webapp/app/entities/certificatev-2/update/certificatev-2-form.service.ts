import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICertificatev2, NewCertificatev2 } from '../certificatev-2.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICertificatev2 for edit and NewCertificatev2FormGroupInput for create.
 */
type Certificatev2FormGroupInput = ICertificatev2 | PartialWithRequiredKeyOf<NewCertificatev2>;

type Certificatev2FormDefaults = Pick<NewCertificatev2, 'id'>;

type Certificatev2FormGroupContent = {
  id: FormControl<ICertificatev2['id'] | NewCertificatev2['id']>;
  pin: FormControl<ICertificatev2['pin']>;
};

export type Certificatev2FormGroup = FormGroup<Certificatev2FormGroupContent>;

@Injectable({ providedIn: 'root' })
export class Certificatev2FormService {
  createCertificatev2FormGroup(certificatev2: Certificatev2FormGroupInput = { id: null }): Certificatev2FormGroup {
    const certificatev2RawValue = {
      ...this.getFormDefaults(),
      ...certificatev2,
    };
    return new FormGroup<Certificatev2FormGroupContent>({
      id: new FormControl(
        { value: certificatev2RawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      pin: new FormControl(certificatev2RawValue.pin),
    });
  }

  getCertificatev2(form: Certificatev2FormGroup): ICertificatev2 | NewCertificatev2 {
    return form.getRawValue() as ICertificatev2 | NewCertificatev2;
  }

  resetForm(form: Certificatev2FormGroup, certificatev2: Certificatev2FormGroupInput): void {
    const certificatev2RawValue = { ...this.getFormDefaults(), ...certificatev2 };
    form.reset(
      {
        ...certificatev2RawValue,
        id: { value: certificatev2RawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): Certificatev2FormDefaults {
    return {
      id: null,
    };
  }
}

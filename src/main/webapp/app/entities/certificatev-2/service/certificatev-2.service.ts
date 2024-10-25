import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICertificatev2, NewCertificatev2 } from '../certificatev-2.model';

export type PartialUpdateCertificatev2 = Partial<ICertificatev2> & Pick<ICertificatev2, 'id'>;

export type EntityResponseType = HttpResponse<ICertificatev2>;
export type EntityArrayResponseType = HttpResponse<ICertificatev2[]>;

@Injectable({ providedIn: 'root' })
export class Certificatev2Service {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/employee-mgt/certificatev-2-s');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(certificatev2: NewCertificatev2): Observable<EntityResponseType> {
    return this.http.post<ICertificatev2>(this.resourceUrl, certificatev2, { observe: 'response' });
  }

  update(certificatev2: ICertificatev2): Observable<EntityResponseType> {
    return this.http.put<ICertificatev2>(`${this.resourceUrl}/${this.getCertificatev2Identifier(certificatev2)}`, certificatev2, {
      observe: 'response',
    });
  }

  partialUpdate(certificatev2: PartialUpdateCertificatev2): Observable<EntityResponseType> {
    return this.http.patch<ICertificatev2>(`${this.resourceUrl}/${this.getCertificatev2Identifier(certificatev2)}`, certificatev2, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICertificatev2>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertificatev2[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCertificatev2Identifier(certificatev2: Pick<ICertificatev2, 'id'>): number {
    return certificatev2.id;
  }

  compareCertificatev2(o1: Pick<ICertificatev2, 'id'> | null, o2: Pick<ICertificatev2, 'id'> | null): boolean {
    return o1 && o2 ? this.getCertificatev2Identifier(o1) === this.getCertificatev2Identifier(o2) : o1 === o2;
  }

  addCertificatev2ToCollectionIfMissing<Type extends Pick<ICertificatev2, 'id'>>(
    certificatev2Collection: Type[],
    ...certificatev2sToCheck: (Type | null | undefined)[]
  ): Type[] {
    const certificatev2s: Type[] = certificatev2sToCheck.filter(isPresent);
    if (certificatev2s.length > 0) {
      const certificatev2CollectionIdentifiers = certificatev2Collection.map(
        certificatev2Item => this.getCertificatev2Identifier(certificatev2Item)!
      );
      const certificatev2sToAdd = certificatev2s.filter(certificatev2Item => {
        const certificatev2Identifier = this.getCertificatev2Identifier(certificatev2Item);
        if (certificatev2CollectionIdentifiers.includes(certificatev2Identifier)) {
          return false;
        }
        certificatev2CollectionIdentifiers.push(certificatev2Identifier);
        return true;
      });
      return [...certificatev2sToAdd, ...certificatev2Collection];
    }
    return certificatev2Collection;
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICertificateDetails } from '../certificate.model';
export type PartialUpdateCertificateDetails = Partial<ICertificateDetails> & Pick<ICertificateDetails, 'id'>;
export type EntityResponseType = HttpResponse<ICertificateDetails>;
export type EntityArrayResponseType = HttpResponse<ICertificateDetails[]>;

@Injectable({
  providedIn: 'root'
})
export class CertificateService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('/api/employee-mgt/certificates');



  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) { }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICertificateDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  create(certificateDetails: ICertificateDetails): Observable<EntityResponseType> {
    return this.http.post<ICertificateDetails>(this.resourceUrl, certificateDetails, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  update(certificateDetails: ICertificateDetails): Observable<EntityResponseType> {
    return this.http.put<ICertificateDetails>(this.resourceUrl, certificateDetails, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertificateDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  partialUpdate(certificateDetails: PartialUpdateCertificateDetails): Observable<EntityResponseType> {
    return this.http.patch<ICertificateDetails>(
      `${this.resourceUrl}/${this.getCertificateDetailsIdentifier(certificateDetails)}`,
      certificateDetails,
      { observe: 'response' }
    );
  }

  getCertificateDetailsIdentifier(certificateDetails: Pick<ICertificateDetails, 'id'>): number {
    return certificateDetails.id;
  }

  addEducationDetailsToCollectionIfMissing<Type extends Pick<ICertificateDetails, 'id'>>(
    certificateDetailsCollection: Type[],
    ...certificateDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const certificateDetails: Type[] = certificateDetailsToCheck.filter(isPresent);
    if (certificateDetails.length > 0) {
      const certificateDetailsCollectionIdentifiers = certificateDetailsCollection.map(
        certificateDetailsItem => this.getCertificateDetailsIdentifier(certificateDetailsItem)!
      );
      const certificateDetailsToAdd = certificateDetails.filter(certificateDetailsItem => {
        const educationDetailsIdentifier = this.getCertificateDetailsIdentifier(certificateDetailsItem);
        if (certificateDetailsCollectionIdentifiers.includes(educationDetailsIdentifier)) {
          return false;
        }
        certificateDetailsCollectionIdentifiers.push(educationDetailsIdentifier);
        return true;
      });
      return [...certificateDetailsToAdd, ...certificateDetailsCollection];
    }
    return certificateDetailsCollection;
  }

  queryByCertificateId(certificateId: number, req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertificateDetails[]>(this.resourceUrl + '/get-by-certificate/' + certificateId, { params: options, observe: 'response' });
  }





}








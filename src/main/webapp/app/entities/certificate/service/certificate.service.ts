import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICertificateDetails } from '../certificate.model';
import { IReferences } from '../../references/references.model';
//import {ICertificateDetails} from "../../certificate/certificate.model";

export type EntityResponseType = HttpResponse<ICertificateDetails>;
export type EntityArrayResponseType = HttpResponse<ICertificateDetails[]>;

@Injectable({
  providedIn: 'root'
})
export class CertificateService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('/api/employee-mgt/certificates');



  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) { }

  create(educationDetails: ICertificateDetails): Observable<EntityResponseType> {
    return this.http.post<ICertificateDetails>(this.resourceUrl, educationDetails, { observe: 'response' });
  }
}








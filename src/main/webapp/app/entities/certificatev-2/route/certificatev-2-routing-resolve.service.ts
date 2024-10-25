import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICertificatev2 } from '../certificatev-2.model';
import { Certificatev2Service } from '../service/certificatev-2.service';

@Injectable({ providedIn: 'root' })
export class Certificatev2RoutingResolveService implements Resolve<ICertificatev2 | null> {
  constructor(protected service: Certificatev2Service, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICertificatev2 | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((certificatev2: HttpResponse<ICertificatev2>) => {
          if (certificatev2.body) {
            return of(certificatev2.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}

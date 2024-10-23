import { Component, OnInit } from '@angular/core';

import { HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ICertificateDetails } from '../certificate.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { ASC, DEFAULT_SORT_DATA, DESC, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { CertificateService, EntityArrayResponseType } from '../service/certificate.service';
import { ParseLinks } from 'app/core/util/parse-links.service';
import { CertificateDelete } from '../delete/delete.component';



@Component({
  selector: 'jhi-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {


  constructor() {}


  ngOnInit(): void {

    }
}











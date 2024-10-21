import { Component, OnInit } from '@angular/core';
import {CertificateDetail} from "../detail/detail.component";

@Component({
  selector: 'jhi-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  certificateDetail: CertificateDetail;



  constructor() {
    this.certificateDetail = new CertificateDetail();
  }

  ngOnInit(): void {
    // This is where you could load the certificate details, e.g., from a service.
    this.loadCertificateDetail();
  }

  // Method to load certificate details (could fetch from a service or API)
  loadCertificateDetail(): void {
    // Sample data (this could come from a service in a real scenario)
    this.certificateDetail = {
      pin: '123456',
      description: 'This is a certificate for course completion',
      enrollmentDate: new Date('2023-01-15'),
      completionDate: new Date('2023-06-15'),
      expiryDate: new Date('2024-06-15'),
      isExpired: false,
      materialsLearned: ['Course 1', 'Course 2'],
      imageUrl: 'https://example.com/certificate-image.png' // Sample image URL
    };
  }

  // You can also add a method to save details or update information
  saveCertificateDetail(): void {
    console.log('Certificate details saved:', this.certificateDetail);
    // Here you can integrate a service to save the details
  }
}

export class CertificateComponent {
  pin: string;
  description: string;
  enrollmentDate: Date;
  completionDate: Date;
  expiryDate: Date;
  isExpired: boolean;
  materialsLearned: string[];
  imageUrl: string;
}

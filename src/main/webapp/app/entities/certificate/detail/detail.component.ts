import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  certificateDetail: CertificateDetail; // Property to store the certificate detail

  constructor() {
    // Initialize the certificate detail object
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
  certificateDetailDetails: any;
  certificateDetails: any;
  saveCertificateDetail(): void {
    console.log('Certificate details saved:', this.certificateDetail);
    // Here you can integrate a service to save the details
  }

  previousState() {

  }
}

export class CertificateDetail {
  pin: string;
  description: string;
  enrollmentDate: Date;
  completionDate: Date;
  expiryDate: Date;
  isExpired: boolean;
  materialsLearned: string[];
  imageUrl: string;
}




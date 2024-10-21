export interface ICertificateDetails {



  pin: number;
  imageUrl: string;
  description: string;
  completionDate: string; // Use appropriate type if using a date object
  expiryDate: string; // Use appropriate type if using a date object
  enrollDate: string; // Use appropriate type if using a date object
  materialsLearned: string[];
  isExpired: boolean;
}

export type NewEducationDetails = Omit<ICertificateDetails, 'id'> & { id: null };

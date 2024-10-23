export interface ICertificateDetails {


  id:number;
  pin: number;
  imageUrl: string;
  description: string;
  completionDate: string; // Use appropriate type if using a date object
  expirationDate: string; // Use appropriate type if using a date object
  enrollmentDate: string; // Use appropriate type if using a date object
  materialsLearned: string[];
  isExpired: boolean;
}

export type NewEducationDetails = Omit<ICertificateDetails, 'id'> & { id: null };

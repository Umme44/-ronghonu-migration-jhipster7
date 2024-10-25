export interface ICertificatev2 {
  id: number;
  pin?: string | null;
}

export type NewCertificatev2 = Omit<ICertificatev2, 'id'> & { id: null };

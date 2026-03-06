export interface CountryId {
  id: number;
}

export interface Region {
  name: string;
  country: CountryId;
}
import { Country } from "./country.model";

export class Company {
    id?: number;
    name!: string;
    country!: Country;
}

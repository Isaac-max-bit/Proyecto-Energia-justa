/* export interface Energy{
    id:number;
    entity:string;
    code:string;
    dataYear:number;
    hydroTwh:number;
    solarTwh:number;
    windTwh:number;
    otherRenewablesTwh:number;
    renewablesSharePercent:number;
    solarCapacityGw:number;
} */
export interface Energy {
    id?: number;         // Agregado para el track del @for
    entity: string;
    region?: string;     // Agregado si lo necesitas específicamente como 'region'
    code?: string;
    year: number;
    windProduction: number;
    solarProduction: number;
    hydroProduction: number;
}

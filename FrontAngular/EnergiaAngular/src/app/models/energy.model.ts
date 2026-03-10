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
    entity: string;
    code: string;
    year: number;
    windProduction: number;
    solarProduction: number;
    hydroProduction: number;
}
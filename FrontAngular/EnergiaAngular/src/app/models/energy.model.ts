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

    export class Energy{
  
    id?:number;
    energyType?:string;
    region?:string;
    production?:number;
    consumption?:number;
    capacity?:number;
    yearData?:number;
}
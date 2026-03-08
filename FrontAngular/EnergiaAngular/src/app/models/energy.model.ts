export interface Energy{
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
}
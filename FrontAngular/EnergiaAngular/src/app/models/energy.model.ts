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
// src/app/models/energy.model.ts
export interface EnergyData {
    // Datos del modelo principal
    id: number;
    entity: string;
    code: string;
    year: number;
    hydroTwh: number;
    solarTwh: number;
    windTwh: number;
    otherRenewablesTwh: number;

    // Nombres que vienen de EnergyPorcentajeService
    porcentaje_renovable?: number;

    // Nombres que vienen de EnergyCapacityService
    capacidad_solar_global?: number;
    anio?: number;
}
import { CarModel } from "./CarModel";

export interface RentalModel{

    pickupLocation: string;
    dropoffLocation: string;
    pickupDate: string;
    dropoffDate: string;
    modelId:number;
    userId:number;
    car:CarModel;
}
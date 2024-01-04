import { CarModel } from "./CarModel";

export interface CarFilterValues  {
    brand: string;
    model: string;
    minPrice: number ;
    maxPrice: number ;
    carList:CarModel[]
  };

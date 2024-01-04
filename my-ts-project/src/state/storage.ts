import { RentalModel } from "../models/RentalModel";
import { UserModel } from "../models/UserModel";

export function storeAuthState(auth:UserModel) {
  localStorage.setItem("auth", JSON.stringify(auth));
}

export function loadAuthState() {
  const defaultState = { id: 0 };
  const authStateInStorage = localStorage.getItem("auth");
  if (!authStateInStorage) return defaultState;
  try {
    return JSON.parse(authStateInStorage);
  } catch {
    return defaultState;
  }
}


export function storeRentalState(rental:RentalModel) {
  localStorage.setItem("rental", JSON.stringify(rental));
}

export function loadRentalState() {
  const defaultState = { userId:0 };
  const rentalStateInStorage = localStorage.getItem("rental");
  if (!rentalStateInStorage) return defaultState;
  try {
    return JSON.parse(rentalStateInStorage);
  } catch {
    return defaultState;
  }
}
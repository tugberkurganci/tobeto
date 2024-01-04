import { configureStore, createSlice } from "@reduxjs/toolkit";
import { loadAuthState, loadRentalState, storeAuthState, storeRentalState } from "./storage";
import { act } from "react-dom/test-utils";


const authSlice =createSlice({
    name:'auth',
    initialState: loadAuthState(),
    reducers:{
        
        loginSuccess:(state,action)=>{

            state.id=action.payload.id;
            state.firstName=action.payload.firstName;
            state.email=action.payload.email;
            state.image=action.payload.image;
            
        },

        logoutSuccess:(state)=>{

            state.id=0;
            delete state.firstName
            delete state.email
            delete state.image
        },

    }
})

const rentalSlice =createSlice({
    name:'rental',
    initialState: loadRentalState(),
    reducers:{
        
        loadRental:(state,action)=>{

            state.userId=action.payload.userId;
            state.pickupLocation=action.payload.pickupLocation;
            state.dropoffLocation=action.payload.dropoffLocation;
            state.pickupDate=action.payload.pickupDate;
            state.dropoffDate=action.payload.dropoffDate;
            state.modelId=action.payload.modelId;
            state.car=action.payload.car
        },

        deleteRental:(state)=>{

            state.userId=0;
            delete state.pickupLocation;
            delete state.dropoffLocation;
            delete state.pickupDate;
            delete state.dropoffDate;
            delete state.modelId;
            delete state.car;
            
        },

    }
})

export const store = configureStore({

    reducer :{
        auth:authSlice.reducer,
        rental:rentalSlice.reducer
    }
});

store.subscribe(()=>{
    storeAuthState(store.getState().auth)
    storeRentalState(store.getState().rental)
})




export const{ loginSuccess,logoutSuccess}=authSlice.actions;
export const{ loadRental,deleteRental}=rentalSlice.actions;




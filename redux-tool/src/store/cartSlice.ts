import { configureStore, createSlice } from "@reduxjs/toolkit";

const initialCartState = {
  cartItems: [],
};

export const cartSlice = createSlice({
  name: "cart",
  initialState: initialCartState,
  reducers: {
    addToCart: (state:any, action) => {
      return { ...state, cartItems: [...state.cartItems, action.payload] };
    },
    removeFromCart: (state, action) => {
      return {
        ...state,
        cartItems: state.cartItems.filter((item:any) => item.id !== action.payload.id),
      };
    },
    clearCart: (state) => {
      return { ...state, cartItems: [] };
    },

  },
});



export const { addToCart, removeFromCart, clearCart } = cartSlice.actions;


  
  
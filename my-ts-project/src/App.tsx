import { useState } from "react";
import Rnavbar from "./components/RNavbar";
import Footer from "./components/Footer";
import HomePage from "./pages/HomePage";
import {  Route, Routes } from "react-router-dom";
import CarList from "./pages/CarList";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import RentalConfirmation from "./pages/RentalConfirmation";


function App() {
  return (
    <>
      <div>
        <Rnavbar />
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/carlist" element={<CarList/>} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/process-rental" element={<RentalConfirmation/>} />
        </Routes>
        <Footer/>
      </div >
    </>
  );
}

export default App;

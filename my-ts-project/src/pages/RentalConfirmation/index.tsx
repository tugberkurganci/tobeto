import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PaymentForm from '../../components/PaymentForm'; // Ödeme formunu içeren bir bileşen
import { useSelector } from 'react-redux';

import axios, { AxiosResponse } from 'axios';



type Props = {}

const RentalConfirmation = (props: Props) => {
    const navigate = useNavigate();
  const [paymentSuccessful, setPaymentSuccessful] = useState<boolean | null>(null);
  const rentalState=useSelector((store:any) => store.rental);
  
  

  const handlePaymentSuccess = () => {
    setPaymentSuccessful(true);
  };

  const handlePaymentFailure = () => {
    setPaymentSuccessful(false);
  };

  const handleRentalConfirmation = async () => {
    if (paymentSuccessful && rentalState.userId!==0) {
        try {
            const response: AxiosResponse = await axios.post("/api/v1/rentals", rentalState);
            console.log(response.data);
            navigate('/success-page');
            
          } catch (error) {
            console.error("Axios POST isteği başarısız:", error);
          }
  };}

  return (
    <div className="container mt-5">
    <div className="card">
      <div className="card-body">
        <h2 className="card-title">Rental Confirmation</h2>
        <div className="mb-3">
          <p className="mb-1">Pickup Location: {rentalState.pickupLocation}</p>
          <p className="mb-1">Dropoff Location: {rentalState.dropoffLocation}</p>
          <p className="mb-1">Pickup Date: {rentalState.pickupDate}</p>
          <p className="mb-1">Dropoff Date: {rentalState.dropoffDate}</p>
             <>
                <p className="mb-1">Car Brand: {rentalState.car?.brand}</p>
                <p className="mb-1">Car Model: {rentalState.car?.model}</p>
                <p className="mb-1">Car Total Price: ${rentalState.car?.price}</p>
                {/* Resim bilgisini kullanarak ekleme */}
                {rentalState.car?.image && (
                  <img src={rentalState.car.image} alt="Car Image" className="img-fluid" />
                )}
              </>
          
        </div>
        <PaymentForm onSuccess={handlePaymentSuccess} onFailure={handlePaymentFailure} />
        {paymentSuccessful === null ? (
          <p>Ödeme bekleniyor...</p>
        ) : paymentSuccessful ? (
          <>
            <p className="text-success">Ödeme başarılı!</p>
            <button className="btn btn-primary" onClick={handleRentalConfirmation}>
              Kiralama Onayla
            </button>
          </>
        ) : (
          <p className="text-danger">Ödeme başarısız. Lütfen tekrar deneyin.</p>
        )}
      </div>
    </div>
  </div>
  )
};

export default RentalConfirmation;
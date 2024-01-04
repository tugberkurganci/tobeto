import React from "react";
import { CarModel } from "../../models/CarModel";
import { CarSearchFormValues } from "../../models/CarSearchFormValues";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { loadRental } from "../../state/redux";

type Props = { car: CarModel ,rentalRequest?: CarSearchFormValues};

const CarCard = (props: Props) => {
  const { car ,rentalRequest } = props;
  const authState=useSelector((store:any) => store.auth);
  const navigate = useNavigate();
  const dispatch=useDispatch();

 
  const handleRentButtonClick = async (modelId: number, rentalRequest: CarSearchFormValues) => {
    
    dispatch(loadRental({ modelId, ...rentalRequest ,userId:authState.id,car:car})) 
    if(authState.id===0){
       navigate("/login");
    }
    
    navigate("/process-rental");
       
     
  };

  return (
<div key={car.id} className="col-md-8 mb-4">
  <div className="card h-100">
    <img
      src={
        "https://cdnuploads.aa.com.tr/uploads/sirkethaberleri/Contents/2019/10/10/thumbs_b_c_f623398d1ad588ccfdf7c299f4db10bc.jpg"
      }
      className="card-img-top"
      alt={`${car.brand} ${car.model}`}
    />
    <div className="card-body">
      <h1 className="card-title">
        {car.brand} {car.model}
      </h1>
      <p className="card-text">Fiyat: ${car.price}</p>
    </div>
    <div className="mb-2" style={{ position: 'relative' }}>
      <button
        className="btn btn-primary"
        onClick={() => {if(rentalRequest){handleRentButtonClick(car.id,rentalRequest)}} }
        style={{ position: 'absolute', bottom: 0, right: 1 }}
      >
        Kirala
      </button>
    </div>
  </div>
</div>


  );
};

export default CarCard;

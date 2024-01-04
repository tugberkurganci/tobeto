import React from "react";
import { Link } from "react-router-dom";
import CarSearchForm from "../../components/CarSearchForm";

type Props = {};

const HomePage = (props: Props) => {
  const handleSearch = () => {
    // Burada arama işlemlerini gerçekleştirebilirsiniz
    console.log("Araçları İncele butonuna tıklandı");
  };
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-lg-6">
          <h1>Araba Kiralama Hizmetleri</h1>
          <p className="lead">
            Hayalinizdeki arabayı kiralayın ve keyifli bir sürüş deneyimi
            yaşayın.
          </p>
          <CarSearchForm />
        </div>
        <div className="col-lg-6">
          <img
            src="https://cdnuploads.aa.com.tr/uploads/sirkethaberleri/Contents/2019/10/10/thumbs_b_c_f623398d1ad588ccfdf7c299f4db10bc.jpg"
            alt="Araba Resmi"
            className="img-fluid"
          />
        </div>
      </div>
    </div>
  );
};

export default HomePage;

import React, { useEffect, useState } from "react";
import CarFilterForm from "../../components/CarFilterForm";
import { CarModel } from "../../models/CarModel";
import { CarFilterValues } from "../../models/CarFilterValues";
import axios from "axios";
import { useLocation } from "react-router-dom";
import CarCard from "../../components/CarCard";

type CarListProps = {};

const CarList: React.FC<CarListProps> = () => {
  const location = useLocation();
  const response = location.state?.rentableCarList;
  const rentalRequest = location.state?.rentalInfo;

  const carList: CarModel[] = response;

  const [filteredCarList, setFilteredCarList] = useState<CarModel[]>(carList);
  const [sortedCarList, setSortedCarList] =useState<CarModel[]>(filteredCarList);
  const [sortType, setSortType] = useState<string>(""); // Sıralama tipini tutan state

  const handleFilter = async (values: CarFilterValues) => {
    try {
      values = { ...values, carList: carList };
      console.log(values);
      const response = await axios.post("/api/v1/cars/filter", values);
      console.log(response);
      setFilteredCarList(response.data);
    } catch (error: any) {
      console.error("Filtreleme hatası:", error.message);
      // Hata durumunda kullanıcıya bilgi verilebilir
    }
  };

  const sortFilter = async (value: {
    carList: CarModel[];
    sortType: string;
  }) => {
    try {
      const response = await axios.post("/api/v1/cars/sort", value);
      console.log(response);
      setSortedCarList(response.data);
    } catch (error: any) {
      console.error("Sıralama hatası:", error.message);
      // Hata durumunda kullanıcıya bilgi verilebilir
    }
  };

  // useEffect içindeki kod sadece filteredCarList değiştiğinde çalışacak
  useEffect(() => {
    if (sortType !== "") {
      sortFilter({ carList: filteredCarList, sortType });
    } else {
      setSortedCarList(filteredCarList);
    }
  }, [filteredCarList, sortType]);

  const handleSortTypeChange = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    // Seçenek değiştiğinde sıralama tipini güncelle
    setSortType(event.target.value);
  };

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-3">
          <h4>Filtrele</h4>
          <CarFilterForm onFilter={handleFilter} />
        </div>

        <div className="col-md-9 position-relative">
          <h2 className="mb-4">Araç Listesi</h2>

          {/* Sağ üstteki sıralama tipi seçeneği */}
          <div className="position-fixed  end-0 col-md-1">
            <label htmlFor="sortType" className="form-label">
              Sırala:
            </label>
            <select
              id="sortType"
              name="sortType"
              className="form-select"
              value={sortType}
              onChange={handleSortTypeChange}
            >
              <option value="" className="text-muted">
                Seçiniz
              </option>
              <option value="price-asc">Artan Fiyat</option>
              <option value="price-desc">Azalan Fiyat</option>
            </select>
          </div>

          <div className="row">
            {sortedCarList?.map((car) => (
              <CarCard car={car} rentalRequest={rentalRequest} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CarList;

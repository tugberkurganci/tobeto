import React from 'react';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import FormikInput from '../FormikInput';
import { CarFilterValues } from '../../models/CarFilterValues';

type CarFilterFormProps = {
  onFilter: (values: CarFilterValues) => void;
};

const CarFilterForm: React.FC<CarFilterFormProps> = ({ onFilter }) => {
  const initialValues: CarFilterValues = {
    brand: '',
    model: '',
    minPrice: 0,
    maxPrice: 0,
    carList:[]
  };

  const validationSchema = Yup.object({
    brand: Yup.string(),
    model: Yup.string(),
    minPrice: Yup.number(),
    maxPrice: Yup.number(),
  });

  const handleFilterSubmit = (values: CarFilterValues) => {
    onFilter(values);
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={handleFilterSubmit}
    >
      <Form>
        <FormikInput label="Marka" name="brand" />
        <FormikInput label="Model" name="model" />
        <FormikInput label="Min Fiyat" name="minPrice" type="number" />
        <FormikInput label="Max Fiyat" name="maxPrice" type="number" />

        <button type="submit" className="btn btn-primary">
          Filtrele
        </button>
      </Form>
    </Formik>
  );
};

export default CarFilterForm;


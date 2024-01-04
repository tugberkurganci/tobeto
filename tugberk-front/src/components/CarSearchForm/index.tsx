import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage, FormikHelpers } from "formik";
import * as Yup from "yup";
import axios from "axios";
import FormikInput from "../FormikInput";
import { useNavigate } from "react-router-dom";
import { CarSearchFormValues } from "../../models/CarSearchFormValues";

type CarSearchFormProps = {};


const CarSearchForm: React.FC<CarSearchFormProps> = () => {
  const navigate = useNavigate();
  const [submissionError, setSubmissionError] = useState<string | null>(null);

  const initialValues: CarSearchFormValues = {
    pickupLocation: "",
    dropoffLocation: "",
    pickupDate: "",
    dropoffDate: "",
  };

  const validationSchema = Yup.object({
    pickupLocation: Yup.string().required("Alış Yeri boş bırakılamaz"),
    pickupDate: Yup.string().required("Alış Tarihi boş bırakılamaz"),
    dropoffLocation: Yup.string(),
    dropoffDate: Yup.string().required("Bırakma Tarihi boş bırakılamaz"),
  });

  const handleOnSubmit = async (values: CarSearchFormValues, { setErrors}:FormikHelpers<CarSearchFormValues> ) => {
    setSubmissionError(null)
    try {
      // Axios ile backende istek yapma
      const response = await axios.post("/api/v1/cars/rentable", values);
      console.log(response)

      // Başarılı bir şekilde tamamlandıysa CarList sayfasına yönlendirme
      if (response.data) {
        navigate("/carList", { state: { rentableCarList: response.data , rentalInfo:values } });
      } 
    } catch (error:any) {

      console.log(error);
        
      // Axios response'undan gelen hataları Formik hatalarına çevirme
      const formikErrors: Record<string, string> = {};
      if (error.response?.data?.validationErrors) {

        const validationErrors: Record<string, string> = error.response.data.validationErrors;

        Object.entries(validationErrors).forEach(([field, message]) => {
          console.log(`Field: ${field}, Message: ${message}`);
          formikErrors[field] = message;
        });


        setErrors(formikErrors);
        
        
      }else{
        
        console.log(error.message)
        setSubmissionError(error.message);}
      // Hataları set etme
      
    }
  };

  const [dropoffLocationVisible, setDropoffLocationVisible] = useState(false);

  const handleToggleDropoffLocation = () => {
    setDropoffLocationVisible(!dropoffLocationVisible);
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={handleOnSubmit}
    >
      <Form>
        <div className="mb-4">
          <h3>Araç Kiralama</h3>
          <FormikInput label="Alış Yeri" name="pickupLocation" />
          {dropoffLocationVisible && (
            <FormikInput label="Bırakış Yeri" name="dropoffLocation" />
          )}

          <div className="mb-3">
            <button
              type="button"
              className="btn btn-link"
              onClick={handleToggleDropoffLocation}
            >
              Farklı yerde bırakmak istiyorum
            </button>
          </div>
          <FormikInput label="Alış Tarihi" name="pickupDate" type="date" />
          <FormikInput label="Bırakış Tarihi" name="dropoffDate" type="date" />
          <button type="submit" className="btn btn-primary">
            Araçları İncele
          </button>
          {submissionError && <div className="text-danger">{submissionError}</div>}
        </div>
      </Form>
    </Formik>
  );
};

export default CarSearchForm;

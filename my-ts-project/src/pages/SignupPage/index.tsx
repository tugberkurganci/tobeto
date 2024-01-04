import React from 'react';
import { Formik, Form, FormikHelpers } from 'formik';
import * as Yup from 'yup';
import FormikInput from '../../components/FormikInput';
import axios from 'axios';

type SignupFormValues = {
  email: string;
  username: string;
  password: string;
  confirmPassword: string;
};

const SignupPage: React.FC = () => {
  const initialValues: SignupFormValues = {
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
  };

  const validationSchema = Yup.object({
    email: Yup.string().email('Invalid email format').required('Email is required'),
    username: Yup.string().required('Username is required'),
    password: Yup.string().required('Password is required'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password'), undefined], 'Passwords must match')
      .required('Confirm Password is required'),
  });

  const handleSignupSubmit = async (
    values: SignupFormValues,
    { setErrors }: FormikHelpers<SignupFormValues>
  ) => {
    try {
      const response = await axios.post('/api/v1/signup', values);

      // Handle the response or redirect to another page if needed

    } catch (error:any) {
      if (error.response && error.response.data && error.response.data.validationErrors) {
        const validationErrors: Record<string, string> = error.response.data.validationErrors;
        const formikErrors: Record<string, string> = {};

        Object.entries(validationErrors).forEach(([field, message]) => {
          console.log(`Field: ${field}, Message: ${message}`);
          formikErrors[field] = message;
        });

        setErrors(formikErrors);
      } else {
        console.error('Signup failed:', error);
      }
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title text-center mb-4">Sign Up</h5>
              <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSignupSubmit}
              >
                <Form>
                  <FormikInput label="Email" name="email" type="email" />
                  <FormikInput label="Username" name="username" />
                  <FormikInput label="Password" name="password" type="password" />
                  <FormikInput
                    label="Confirm Password"
                    name="confirmPassword"
                    type="password"
                  />

                  <button type="submit" className="btn btn-primary btn-block">
                    Sign Up
                  </button>
                </Form>
              </Formik>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SignupPage;

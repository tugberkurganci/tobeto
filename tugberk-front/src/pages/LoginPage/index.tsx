import React from 'react';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import FormikInput from '../../components/FormikInput';
import { useDispatch } from "react-redux";
import { loginSuccess } from '../../state/redux';
import axios from 'axios';
import { Link } from 'react-router-dom';


type Props = {}


function LoginPage({}: Props) {

    
const dispatch = useDispatch();
const initialValues = {
  username: '',
  password: '',
};

const validationSchema = Yup.object({
  username: Yup.string().required('Username is required'),
  password: Yup.string().required('Password is required'),
});

const handleLoginSubmit = async (values: { username: string; password: string }) => {
    try {
     
      const response = await axios.post('/api/v1/login', values); 
  
      const user = response.data;
  
      dispatch(loginSuccess(user));
  
    } catch (error) {
    
      console.error('Login failed:', error);
     
    }
  };
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title text-center mb-4">Login</h5>
              <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleLoginSubmit}
              >
                <Form>
                  <FormikInput label="Username" name="username"  />
                  <FormikInput label="Password" name="password" type="password"  />

                  <button type="submit" className="btn btn-primary btn-block">
                    Login
                  </button>
                  <p className="mt-3 text-center">
                    Don't have an account?{' '}
                    <Link to="/signup" className="btn btn-link">
                      Sign Up
                    </Link>
                    </p>
                </Form>
              </Formik>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default LoginPage

import React from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { Navbar, Nav, Button, NavDropdown, Container } from "react-bootstrap";
import { logoutSuccess } from '../../state/redux';

type Props = {}

const Rnavbar = (props: Props) => {

  const navigate = useNavigate();
  const authState=useSelector((store:any) => store.auth);
  const dispatch=useDispatch();

  
  const handleLogin = () => {
    navigate("/login");
  };

  const handleLogout = () => {
    dispatch(logoutSuccess())
    navigate("/");
    
  };

  const handleSignup=()=>{

    navigate("/signup");
  }

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
    <Container>
      <Navbar.Brand as={Link} to="/">
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcQT26-hE7xX5EEX5-xtJfEDIuaBURb8ighA&usqp=CAU" // Replace with the path to your logo image
          alt="Logo"
          height="50"
          className="d-inline-block align-top"
        />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="navbar-nav" />
      <Navbar.Collapse id="navbar-nav">
        <Nav className="me-auto">
          <Nav.Link as={Link} to="/">
            Ana Sayfa
          </Nav.Link>
        </Nav>
        <Nav>
          {authState.id !== 0 ? (
            <NavDropdown
              title={
                <div className="d-flex align-items-center">
                  <span className="ms-2">{authState.firstName}</span>
                  <img
                    src={authState.image}
                    style={{
                      width: "60px",
                      height: "60px",
                      borderRadius: "50%",
                    }}
                    alt="User Avatar"
                    className="dropdown-avatar"
                  />
                </div>
              }
              id="userDropdown"
            >
              <NavDropdown.Item onClick={() => handleLogout()}>
                Çıkış Yap
              </NavDropdown.Item>
            </NavDropdown>
          ) : (<div>
            <Button variant="outline-success" onClick={handleLogin}>
              Giriş Yap
            </Button>{"   "}
            <Button variant="outline-success" onClick={handleSignup}>
            Sign Up
          </Button>
          </div>
          )}
        </Nav>
      </Navbar.Collapse>
    </Container>
  </Navbar>
  )
}

export default Rnavbar
import './App.css';
import Header from './Header/Header';
import Home from './Home/Home';
import {BrowserRouter as Router, Switch, Route, Routes} from "react-router-dom";
import Login from './Login/Login';
import Signup from './Signup/Signup'
import Dashboard from './Dashboard/Dashboard';
import ProductForm from './ProductForm/ProductForm';
import { useStateValue } from './StateManager/StateProvider';
import { API_URL } from './EnviormentVariables';
import Profile from './Profile/Profile';


function App() {
  return (
    <Router>
        <div className="App">

          <Routes>

            <Route exact path="/login" element={<Login/>} />
            
            <Route exact path="/signup" element={<Signup/>} />

            <Route exact path="/" element={<><Header/><Home/></>} />

            <Route exact path="/dashboard" element={<Dashboard/>} />

            <Route exact path="/dashboard/product" element={<ProductForm/>} />
            
            <Route exact path="/profile" element={<Profile/>} />  

          </Routes>

        </div>
      
    </Router>

  );
}

export default App;

const GetUser = async (state) => {  
  let return_obj = state.user;
  if(state.token != null /*&& state.user === null*/) {
    const url = `${API_URL}/api/getuser/`;
    await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Token ${state.token}`
        }
    })
    .then(resp => resp.json())
    .then(res => {
      return_obj = res;
    })
    .catch(errors => console.log(errors));
  }
  return return_obj;
}

export { GetUser };

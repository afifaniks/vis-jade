import React,{useState, useEffect} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { API_URL } from '../EnviormentVariables';
import { useStateValue } from '../StateManager/StateProvider';
import './Signup.css';

function Signup() {
    var Image_Count = 1;
    const history = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cpassword, setCpassword] = useState('');
    const [name, setName] = useState('');
    const [phonenumber, setPhonenumber] = useState('');
    const [address, setAddress] = useState('');
    const [dob, setDob] = useState('');
    const [height, setHeight] = useState('');
    const [gender, setGender] = useState('');
    const [eyecolor, setEyecolor] = useState('');
    const [bloodgroup, setBloodgroup] = useState('');
    const [massage, setMassage] = useState('');
    let status = null;

    const [state, dispatch] = useStateValue();

    //useEffect(() => console.log(status), [status])
    
    const setUser = (res) => {
        if(!res.token)
            setMassage(res.non_field_errors[0]);
        else {
            setMassage('');
            dispatch({
                type: 'SET_USER',
                token: res.token
            });
            localStorage.setItem('token', res.token);
        }
    }
    
    const handleResponse = res => {
        if(status === 201) {
            setMassage('');
            const url = `${API_URL}/auth/`;
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    'username' : email,
                    'password':  password
                })
            })
            .then(resp => resp.json())
            .then(res => setUser(res))
            .catch(errors => console.log(errors));

        } else 
            setMassage(res.massage);
    }
    
    const signup = (event, email, name, password, phonenumber, address, dob, height, gender, eyecolor, bloodgroup) => {
        event.preventDefault();


        const url = `${API_URL}/signup/`;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                'email': email, 
                'name' : name,
                'password': password,
                'phone' : phonenumber,
                'address' : address,
                'dob': dob,
                'height': height,
                'gender': gender,
                'eyecolor': eyecolor,
                'bloodgroup': bloodgroup
            })
        })
        .then(resp => {
            status = resp.status;
            return resp.json();
        })
        .then(res => handleResponse(res))
        .catch(errors => console.log(errors));
        
    }

    return (
        <div className="signup">
            
            <Link to='/'>
                <img className="signup__logo"
                src="http://pngimg.com/uploads/hummingbird/hummingbird_PNG66.png" 
                alt="img" />
            </Link>

            <div className="signup__container" id="init-form">
                <h1>Create Your VIS Account</h1>
                <h4>{massage}</h4>
                <form>

                    <h5>E-mail</h5>
                    <input className="signup__input"
                        placeholder="example@domain.com"
                        type='text' 
                        value={email} 
                        onChange={e => setEmail(e.target.value)}
                    />

                    <h5>Name</h5>
                    <input className="signup__input"
                        placeholder="First-name"
                        type='text' 
                        value={name} 
                        onChange={e => setName(e.target.value)}
                    />

                    <h5>Phone</h5>
                    <input className="signup__input"
                        placeholder="Phone Number"
                        type='text' 
                        value={phonenumber} 
                        onChange={e => setPhonenumber(e.target.value)}
                    />

                    <h5>Address</h5>
                    <input className="signup__input"
                        placeholder="Address"
                        type='text' 
                        value={address} 
                        onChange={e => setAddress(e.target.value)}
                    />

                    <h5>Date of Birth</h5>
                    <input className="signup__input"
                        placeholder="1990-11-23"
                        type='text' 
                        value={dob} 
                        onChange={e => setDob(e.target.value)}
                    />   

                    <h5>Height</h5>
                    <input className="signup__input"
                        placeholder="170"
                        type='double' 
                        value={height} 
                        onChange={e => setHeight(e.target.value)}
                    />                    
                    <h5>gender</h5>
                    <input className="signup__input"
                        placeholder="Male, Female, Other"
                        type='text' 
                        value={gender} 
                        onChange={e => setGender(e.target.value)}
                    />                    
                    <h5>Eye Color</h5>
                    <input className="signup__input"
                        placeholder="Black"
                        type='text' 
                        value={eyecolor} 
                        onChange={e => setEyecolor(e.target.value)}
                    />                    
                    <h5>Blood Group</h5>
                    <input className="signup__input"
                        placeholder="B-"
                        type='text' 
                        value={bloodgroup} 
                        onChange={e => setBloodgroup(e.target.value)}
                    />                    

                    <h5>Password</h5>
                    <input className="signup__input"
                        placeholder="password"
                        type='password' 
                        value={password} 
                        onChange={e => setPassword(e.target.value)}
                    />

                    <h5>Confirm password</h5>
                    <input className="signup__input"
                        placeholder="Confirm password"
                        type='password' 
                        value={cpassword} 
                        onChange={e => setCpassword(e.target.value)}
                    />
                    {(password === cpassword) ? null : <span>Password is not the same! <br/></span>}

                    
                    <button 
                        disabled={!(phonenumber && email && password && cpassword && (password === cpassword) )}
                        type='submit' 
                        className="signup__signInButton" 
                        onClick={(event) =>  signup(event, email, name, password, phonenumber, address, dob, height, gender, eyecolor, bloodgroup) }>
                            Signup
                    </button>
                </form>

                <p>
                    After signing-up you agree to VIS's conditions of use and Privacy Notice.
                </p>

                <Link to="/login">
                    <p>Already have an account?</p>
                </Link>

            </div>

        </div>
    )
}

export default Signup

import React,{useState, useEffect} from 'react'
import './Login.css'
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useStateValue } from '../StateManager/StateProvider';
import { API_URL } from '../EnviormentVariables';
import { GetUser } from '../App';

function Login(props) {

    const history = useNavigate();
    const location = useLocation();
    const [email,setEmail] = useState('');
    const [password,setPassword] = useState('');
    const [Massage, setMassage] = useState(location?.state?.massage ? location.state.massage : '');

    const [state, dispatch] = useStateValue();

    // useEffect( () => {
    //     console.log(location);
    //  } )

    const handleResponse = async (res) => {
        if(!res.token){
            setMassage(res.non_field_errors[0]);
        }
        else {
            setMassage('');
            dispatch({
                type: 'SET_USER',
                token: res.token
            });
            localStorage.setItem('token', res.token);
            let user = null;
            user = await GetUser({...state, token: res.token});
            user.role == 'company' ? history.push("/dashboard") : history.push("/");
        }
    }
    
    const signIn = (event, email, password) => {
        event.preventDefault();
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
        .then(res => handleResponse(res))
        .catch(errors => console.log(errors));
    }

    return (
        <div className="login">
            <Link to='/'>
                <img className="login__logo"
                src="http://pngimg.com/uploads/hummingbird/hummingbird_PNG66.png" 
                alt="img" />
            </Link>

            <div className="login__container">
                <h1>Login To Your VIS Account</h1>
                <h4>{Massage}</h4>
                <form>
                    <input 
                        placeholder="e-mail"
                        type='text' 
                        value={email} 
                        onChange={e => setEmail(e.target.value)}
                    />

                    <input 
                        placeholder="password"
                        type='password' 
                        value={password} 
                        onChange={e => setPassword(e.target.value)}
                    />

                    <button
                        disabled={!(email && password)} 
                        type='submit' 
                        className="login__signInButton" 
                        onClick={(event) => signIn(event, email, password)}>Login
                    </button>

                </form>

                <Link to='/'>
                    <p className="p">
                        Forgot password?
                    </p>
                </Link>

                <Link to='/signup'>
                    Create your VIS account
                </Link>

            </div>

        </div>
    )
}

export default Login;


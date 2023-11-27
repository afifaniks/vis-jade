import React, { useEffect, useState } from 'react';
import { GetUser } from '../App';
import { useNavigate } from 'react-router-dom';
import './Profile.css';
import { useStateValue } from '../StateManager/StateProvider';
import { API_URL } from '../EnviormentVariables';
 
function Profile() {
    const history = useNavigate();
    const [state, dispatch] = useStateValue();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cpassword, setCpassword] = useState('');
    const [username, setUsername] = useState('');
    const [User, SetUser] = useState('nullll');
    let user;
    let status = null;

    useEffect( async () => {
        user = await GetUser(state);
        await dispatch({
            type: 'SET_USER_INFO',
            user: user
        });

        SetUser(user);

        // if(user === null)
        //     history.push({
        //         pathname: "/login",
        //         state: {massage: 'You have to login to your acount first!'}
        //     })
        // else {
        //     setUsername(user.role == 'company' ? user.title : user.name)
        //     setEmail(user.user.email)
        // }
        
    }, [] )

    // useEffect(() => console.log('userEffect: ', User, user), [User])
    
    const handleResponse = res => {
        console.log(res)
        if(status === 200) {
            res.role === 'company' ? history.push("/dashboard") : history.push("/");
            alert('Profile successfully updated!')
        } else {
            alert('Something went wrong try again!')
        }
    }

    const updateUser = (event) => {
        event.preventDefault()         
        
        const url = `${API_URL}/api/profileupdate/`;
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Token ${state.token}`
            },
            body: JSON.stringify({
                'username' : username,
                'email': email, 
                'password': password,
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
        <div>
            <form>

                <h5>Username</h5>
                <input 
                    placeholder="Username"
                    type='text' 
                    value={username} 
                    onChange={e => setUsername(e.target.value)}
                />

                <h5>E-mail</h5>
                <input 
                    placeholder="example@domain.com"
                    type='text' 
                    value={email} 
                    onChange={e => setEmail(e.target.value)}
                />

                <h5>Password</h5>
                <input 
                    placeholder="password"
                    type='password' 
                    value={password} 
                    onChange={e => setPassword(e.target.value)}
                />

                <h5>Confirm password</h5>
                <input 
                    placeholder="Confirm password"
                    type='password' 
                    value={cpassword} 
                    onChange={e => setCpassword(e.target.value)}
                />
                {(password === cpassword) ? null : <p>Password is not the same!</p>}
                <br />
                <button 
                    disabled={!(username && email && password && cpassword && (password === cpassword) )}
                    type='submit' 
                    onClick={(event) => updateUser(event, username, email, password)} >
                        Update
                </button>

            </form>
        </div>
    )
}

export default Profile

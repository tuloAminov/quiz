import React from 'react';
import ReactDOM from 'react-dom/client';
import Registration from "./js/Registration";
import MainPage from "./js/MainPage";
import UserRoom from "./js/UserRoom";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import Quiz from "./js/Quiz";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Registration />} />
            <Route path="main" element={<MainPage />} />
            <Route path="userRoom" element={<UserRoom />}/>
            <Route path="quiz" element={<Quiz/>}/>
        </Routes>
    </BrowserRouter>,
    <MainPage/>,
    <Quiz />,
    <Registration />
);



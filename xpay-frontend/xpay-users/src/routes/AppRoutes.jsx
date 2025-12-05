import React from "react";
import { Route, Routes } from "react-router-dom"
import LoginPage from "../components/login/LoginPage"
import HomePage from "../components/home/home.page"
import { ROUTES } from "../constants/global.consts";
import WelcomePage from "../components/welcome/WelcomePage";

const AppRoutes = () => {
    return (
        <Routes>
            <Route path={ROUTES.ROOT} element={<WelcomePage />} />
            <Route path={ROUTES.LOGIN} element={<LoginPage />} />
            <Route path={ROUTES.HOME} element={<HomePage />} />
        </Routes>
    );
};

export default AppRoutes;
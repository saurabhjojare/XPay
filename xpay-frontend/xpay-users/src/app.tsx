import "./app.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./components/login/login.page";
import HomePage from "./components/home/home.page";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/home" element={<HomePage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;

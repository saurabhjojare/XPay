import "./app.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./components/login/login.page";
import HomePage from "./components/home/home.page";
import PageLayout from "./components/common/PageLayout";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<HomePage />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;

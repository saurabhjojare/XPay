import "./app.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./components/login/login.page";
import HomePage from "./components/home/home.page";
import { CssBaseline, ThemeProvider, useMediaQuery } from "@mui/material";
import { getAppTheme } from "./components/common/theme";

function App() {
  const prefersDarkMode = useMediaQuery("(prefers-color-scheme: dark)");
  const theme = getAppTheme(prefersDarkMode);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/home" element={<HomePage />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;

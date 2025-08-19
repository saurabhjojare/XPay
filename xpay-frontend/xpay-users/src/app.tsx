import "./app.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./components/login/login.page";
import HomePage from "./components/home/home.page";
import PageLayout from "./components/common/PageLayout";
import { createTheme, CssBaseline, ThemeProvider, useMediaQuery } from "@mui/material";

function App() {
  const prefersDarkMode = useMediaQuery("(prefers-color-scheme: dark)");

  const theme = createTheme({
    palette: {
      mode: prefersDarkMode ? "dark" : "light",
      ...(prefersDarkMode
        ? {
          // Dark Mode
          background: {
            default: "#000000",
            paper: "#000000"
          },
          text: {
            primary: "#ffffff",
            secondary: "#ffffff"
          },
        }
        : {
          // Light Mode
          background: {
            default: "#ffffff",
            paper: "#ffffff",
          },
          text: {
            primary: "#000000",
            secondary: "#000000",
          },
        }
      ),
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;

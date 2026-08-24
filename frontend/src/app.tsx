import { BrowserRouter, Route, Routes } from "react-router-dom";
import { CssBaseline, ThemeProvider, useMediaQuery } from "@mui/material";
import { getAppTheme } from "./components/common/appTheme";
import AppRoutes from "./routes/AppRoutes";
import "./app.css";

function App() {
  const prefersDarkMode = useMediaQuery("(prefers-color-scheme: dark)");
  const theme = getAppTheme(prefersDarkMode);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter>
        <AppRoutes/>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;

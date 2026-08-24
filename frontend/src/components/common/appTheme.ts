import { createTheme } from "@mui/material";

export const getAppTheme = (prefersDarkMode: boolean) => {
    return createTheme({
        palette: {
            mode: prefersDarkMode ? "dark" : "light",
            background: {
                default: prefersDarkMode ? "#212121" : "#f5f5f5",
                paper: prefersDarkMode ? "#000000" : "#fafafa",
            }
        }
    });
};

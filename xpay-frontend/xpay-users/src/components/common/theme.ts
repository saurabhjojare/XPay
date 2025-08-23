import { createTheme } from "@mui/material";

export const getAppTheme = (prefersDarkMode: boolean) => {
    return createTheme({
        palette: {
            mode: prefersDarkMode ? "dark" : "light",
            ...(prefersDarkMode
                ? {
                    background: {
                        default: "#212121",
                        paper: "#000000",
                    },
                    text: {
                        primary: "#ffffff",
                        secondary: "#ffffff",
                    },
                }
                : {
                    background: {
                        default: "#f5f5f5",
                        paper: "#fafafa",
                    },
                    text: {
                        primary: "#000000",
                        secondary: "#000000",
                    },
                }),
        },
    });
};

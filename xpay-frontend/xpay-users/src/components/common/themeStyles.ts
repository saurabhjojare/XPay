import { colors, Theme } from "@mui/material";

export const ButtonBlackAndWhite1 = (theme: Theme) => ({
    backgroundColor:
        theme.palette.mode === 'dark'
            ? theme.palette.common.white
            : theme.palette.common.black,
    color:
        theme.palette.mode === "dark"
            ? theme.palette.common.black
            : theme.palette.common.white,
    "&:hover": {
        backgroundColor:
            theme.palette.mode === "dark"
                ? theme.palette.grey[200]
                : theme.palette.grey[900],
    },
});

export const BoxBackgroundStyle1 = (theme: Theme) => ({
    backgroundColor:
        theme.palette.mode === "dark"
            ? theme.palette.common.black
            : theme.palette.background.paper,
});


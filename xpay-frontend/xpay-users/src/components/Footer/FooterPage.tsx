import React from "react";
import { AppBar, Toolbar, Typography, Box } from "@mui/material";

const Footer = () => {
    return (
        <AppBar
            position="static"
            elevation={0}
            color="inherit"
            sx={{
                top: "auto",
                bottom: 0,
            }}
        >
            <Toolbar sx={{ minHeight: "auto", py: 0 }}>
                <Box sx={{ width: "100%", textAlign: "center" }}>
                    <Typography
                        variant="body1"
                        sx={{
                            fontWeight: 400,
                            fontSize: "0.9rem",
                        }}
                    >
                        © {new Date().getFullYear()} Galaxon. All rights reserved.
                    </Typography>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Footer;

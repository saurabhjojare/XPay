import React from "react";
import { AppBar, Toolbar, Typography, Box } from "@mui/material";

const Header = () => {
    return (
        <AppBar position="static" sx={{ background: "#000" }}>
            <Toolbar sx={{ minHeight: "auto", py: 0 }}>
                <Box sx={{ width: "100%", textAlign: "center" }}>
                    <Typography
                        variant="h5"
                        sx={{
                            fontWeight: 500
                        }}
                    >
                        XPay
                    </Typography>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Header;

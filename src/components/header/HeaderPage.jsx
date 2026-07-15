import React from "react";
import { AppBar, Toolbar, Typography, Box } from "@mui/material";

const Header = () => {
    return (
        <AppBar position="fixed" color="inherit" sx={{ top: 0 }}>
            <Toolbar variant="dense">
                <Box width="100%" display="flex" justifyContent="center">
                    <Typography variant="h6">
                        XPay
                    </Typography>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Header;

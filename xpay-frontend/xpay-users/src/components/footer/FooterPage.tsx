import { AppBar, Toolbar, Typography, Box } from "@mui/material";

const Footer = () => {
    return (
        <AppBar position="fixed" color="inherit" sx={{ top: "auto", bottom: 0 }}>
            <Toolbar variant="dense">
                <Box width="100%" display="flex" justifyContent="center">
                    <Typography variant="body2">
                        © {new Date().getFullYear()} Galaxon. All rights reserved.
                    </Typography>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Footer;

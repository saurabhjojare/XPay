import { AppBar, Toolbar, Typography, Box } from "@mui/material";

const Header = () => {
    return (
        <AppBar position="static" color="inherit">
            <Toolbar sx={{ minHeight: "auto", py: 0 }}>
                <Box sx={{ width: "100%", textAlign: "center" }}>
                    <Typography
                        variant="h5"
                        sx={{
                            fontWeight: 300
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

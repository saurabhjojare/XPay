import { AppBar, Toolbar, Typography, Box, Paper } from "@mui/material";

const Footer = () => {
    return (
        <Paper elevation={3}>
            <AppBar
                position="fixed"
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
        </Paper>
    );
};

export default Footer;

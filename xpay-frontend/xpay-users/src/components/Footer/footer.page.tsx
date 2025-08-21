import { AppBar, Toolbar, Typography, Box, Paper, Container } from "@mui/material";

const Footer = () => {
    return (
        <Paper elevation={3}>
            <AppBar position="fixed" color="inherit" sx={{ top: "auto", bottom: 0 }}>
                <Toolbar variant="dense">
                    <Container maxWidth="lg">
                        <Box display="flex" justifyContent="center" width="100%">
                            <Typography variant="body2" fontWeight={400}>
                                © {new Date().getFullYear()} Galaxon. All rights reserved.
                            </Typography>
                        </Box>
                    </Container>
                </Toolbar>
            </AppBar>
        </Paper>
    );
};

export default Footer;

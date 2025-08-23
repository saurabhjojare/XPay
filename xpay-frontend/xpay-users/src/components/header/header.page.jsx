import { AppBar, Toolbar, Typography, Box, Paper, Container } from "@mui/material";

const Header = () => {
    return (
        <Paper elevation={3}>
            <AppBar position="fixed" color="inherit" sx={{ top: 0, bottom: "auto" }}>
                <Toolbar variant="dense">
                    <Container maxWidth="lg">
                        <Box display="flex" justifyContent="center" width="100%">
                            <Typography variant="body2" fontWeight={400}>
                                XPay
                            </Typography>
                        </Box>
                    </Container>
                </Toolbar>
            </AppBar>
        </Paper>
    );
};

export default Header;

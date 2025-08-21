import { AppBar, Toolbar, Typography, Box, Container } from "@mui/material";

const Header = () => {
    return (
        <AppBar position="static" color="inherit">
            <Toolbar variant="dense">
                <Container maxWidth="lg">
                    <Box display="flex" justifyContent="center" width="100%">
                        <Typography variant="h6" fontWeight={300}>
                            XPay
                        </Typography>
                    </Box>
                </Container>
            </Toolbar>
        </AppBar>
    );
};

export default Header;

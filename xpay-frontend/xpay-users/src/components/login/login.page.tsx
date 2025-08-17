import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Snackbar,
  Alert,
} from "@mui/material";
import useLogin from "./login.use";
import PageLayout from "../common/PageLayout";

const LoginPage: React.FC = () => {
  const {
    email,
    password,
    setEmail,
    setPassword,
    handleSubmit,
    errorMessage,
    setErrorMessage,
  } = useLogin();

  return (
    <>
      <PageLayout>
        <Box display="flex" justifyContent="center" alignItems="center" flex={1}>
          <Paper elevation={3} sx={{ width: { xs: "100%", sm: "400px" }, p: 4 }}>
            <Typography variant="h5" gutterBottom align="center">
              Login
            </Typography>
            <form onSubmit={handleSubmit}>
              <TextField
                label="Email"
                type="email"
                fullWidth
                margin="normal"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />

              <TextField
                label="Password"
                type="password"
                fullWidth
                margin="normal"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />

              <Button
                type="submit"
                variant="contained"
                size="large"
                fullWidth
                sx={{
                  mt: 2,
                  backgroundColor: "black",
                  color: "white"
                }}
              >
                Login
              </Button>
            </form>
          </Paper>
        </Box>
      </PageLayout>


      <Snackbar
        open={!!errorMessage}
        autoHideDuration={3000}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        onClose={() => setErrorMessage("")}
      >
        <Alert
          onClose={() => setErrorMessage("")}
          severity="error"
          sx={{ width: "100%" }}
        >
          {errorMessage}
        </Alert>
      </Snackbar>
    </>
  );
};

export default LoginPage;

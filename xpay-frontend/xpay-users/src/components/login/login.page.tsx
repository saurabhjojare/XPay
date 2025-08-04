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
      <Box
        display={"flex"}
        justifyContent={"center"}
        alignItems={"center"}
        minHeight={"100vh"}
        px={2}
        bgcolor={"#f5f5f5"}
      >
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
              color="primary"
              size="large"
              fullWidth
              sx={{ mt: 2 }}
            >
              Login
            </Button>
          </form>
        </Paper>
      </Box>

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

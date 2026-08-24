import React from "react";
import { Snackbar, Alert } from "@mui/material";
import { SnackBarProps } from "../../interfaces/Snackbar.types";

const SnackbarAlert: React.FC<SnackBarProps> = ({
    open,
    message,
    severity = "info",
    duration = 3000,
    onClose,
}) => {
    return (
        <Snackbar open={open} autoHideDuration={duration}
            anchorOrigin={{ vertical: "top", horizontal: "right" }} onClose={onClose}>
            <Alert onClose={onClose} severity={severity} sx={{ width: "100%" }}>
                {message}
            </Alert>
        </Snackbar>
    )
}

export default SnackbarAlert;
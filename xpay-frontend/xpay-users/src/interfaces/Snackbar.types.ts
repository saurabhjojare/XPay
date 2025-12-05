import { AlertColor } from "@mui/material";

export interface SnackBarProps {
    open: boolean;
    message: string;
    severity?: AlertColor;
    duration?: number;
    onClose: () => void;
}
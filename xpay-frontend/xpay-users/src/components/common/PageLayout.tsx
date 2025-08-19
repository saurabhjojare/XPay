import { Box, useTheme } from "@mui/material";
import React from "react";
import Header from "../header/header.page";
import Footer from "../Footer/FooterPage";

interface PageLayoutProps {
    children: React.ReactNode;
    header?: React.ReactNode;
    footer?: React.ReactNode;
    centerContent?: boolean;
}

const PageLayout: React.FC<PageLayoutProps> = ({ children, header, footer, centerContent = false }) => {
    const theme = useTheme();

    return (
        <Box
            display="flex"
            flexDirection="column"
            minHeight="100vh"
            bgcolor={theme.palette.background.default}
            color={theme.palette.text.primary}
        >
            {header ?? <Header />}

            <Box
                component="main"
                flex={1}
                display="flex"
                flexDirection="column"
                justifyContent={centerContent ? "center" : "flex-start"}
                alignItems={centerContent ? "center" : "stretch"}
                bgcolor={theme.palette.background.default}
                color={theme.palette.text.primary}
            >
                {children}
            </Box>

            {footer ?? <Footer />}
        </Box>
    );
};

export default PageLayout;

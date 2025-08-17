import { Box } from "@mui/material";
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
    return (
        <Box display="flex" flexDirection="column" minHeight="100vh" >
            <Header />

            <Box component="main" flex={1} display="flex" flexDirection="column">


                {children}
            </Box>

            <Footer></Footer>
        </Box>
    );
};

export default PageLayout;
